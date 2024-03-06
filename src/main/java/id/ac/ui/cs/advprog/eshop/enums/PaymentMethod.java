package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentMethod {
    VOUCHER_CODE("VOUCHER_CODE");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
