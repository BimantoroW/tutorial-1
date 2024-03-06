package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;

        if (order == null) {
            throw new IllegalArgumentException();
        }
        this.order = order;

        setMethod(method);

        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;
        if (this.isPaymentDataValid(method, paymentData)) {
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    private void setMethod(String method) {
        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
            if (status.equals(PaymentStatus.SUCCESS.getValue())) {
                this.order.setStatus("SUCCESS");
            } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
                this.order.setStatus("FAILED");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isPaymentDataValid(String method, Map<String, String> paymentData) {
        if (method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            return isVoucherCodeValid(paymentData);
        } else if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            return isBankTransferValid(paymentData);
        }
        return false;
    }

    private boolean isVoucherCodeValid(Map<String, String> data) {
        if (data.size() != 1) {
            return false;
        }
        String code = data.get("voucherCode");
        if (code == null) {
            return false;
        }
        boolean isCorrectLength = code.length() == 16;
        boolean startsWithEshop = code.startsWith("ESHOP");
        boolean digitIsCorrectLength = code.chars().filter(Character::isDigit).count() == 8;
        if (!isCorrectLength || !startsWithEshop || !digitIsCorrectLength) {
            return false;
        }
        return true;
    }

    private boolean isBankTransferValid(Map<String , String> data) {
        if (data.size() != 2) {
            return false;
        }
        String bankName = data.get("bankName");
        String referenceCode = data.get("referenceCode");
        System.out.println(bankName);
        System.out.println(referenceCode);
        return bankName != null && !bankName.isEmpty() && referenceCode != null && !referenceCode.isEmpty();
    }
}
