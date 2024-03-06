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

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        setMethod(method);
        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;
        if (this.isPaymentDataValid(paymentData)) {
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
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isPaymentDataValid(Map<String, String> paymentData) {
        // Override this in every subclass please please please please please please please don't forget okay bye
        return true;
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
}
