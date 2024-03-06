package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentTest {
    private Order order;
    private Map<String, String> voucherDataValid;
    private Map<String, String> voucherDataInvalid;
    private String dummyId;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");

        this.voucherDataValid = new HashMap<>();
        this.voucherDataValid.put("voucherCode", "ESHOP1234ABC5678");

        this.voucherDataInvalid = new HashMap<>();
        this.voucherDataValid.put("voucherCode", "doireallywannabedoingthisfortherestofmylife");

        this.dummyId = "136522556-012a-4c07-b546-54eb1396d79b";
    }

    @Test
    void testValidPaymentMethod() {
        Payment payment = new Payment(this.dummyId, "VOUCHER_CODE", this.voucherDataValid);
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
    }

    @Test
    void testInvalidPaymentMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.dummyId, "12hoursadayinfrontofascreendude", this.voucherDataValid);
        });
    }

    @Test
    void testEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.dummyId, "VOUCHER_CODE", new HashMap<>());
        });
    }
}
