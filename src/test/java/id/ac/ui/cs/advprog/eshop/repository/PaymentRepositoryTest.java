package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository = new PaymentRepository();
    private List<Payment> payments = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    @BeforeEach
    void setUp(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");

        Map<String, String> voucherDataValid = new HashMap<>();
        voucherDataValid.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("anfdskjlfnad", "VOUCHER_CODE", voucherDataValid, order);
        this.payments.add(payment);
    }

    @Test
    void testAddPaymentValid() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        Payment savedPayment = paymentRepository.getPaymentById(payment.getId());
        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getMethod(), savedPayment.getMethod());
        assertSame(payment.getPaymentData(), savedPayment.getPaymentData());
        assertEquals(payment.getStatus(), savedPayment.getStatus());
    }

    @Test
    void testAddPaymentDuplicatedId(){
        Payment payment1 = payments.get(0);
        paymentRepository.save(payment1);
        Payment payment2 = new Payment(payment1.getId(), payment1.getMethod(), payment1.getPaymentData(), payment1.getOrder());
        assertThrows(IllegalArgumentException.class, ()-> paymentRepository.save(payment2));
    }

    @Test
    void testFindByIdIfIdFound(){
        Payment payment = paymentRepository.save(payments.get(0));
        Payment saved = paymentRepository.getPaymentById(payment.getId());
        assertEquals(payment.getId(), saved.getId());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        assertNull(paymentRepository.getPaymentById("bvbsadhbqwdnhbfsfdbdnh"));
    }

    @Test
    void testGetAllPayments(){
        paymentRepository.save(payments.get(0));
        List<Payment> result = paymentRepository.getAllPayments();
        assertEquals(1, result.size());
    }
}
