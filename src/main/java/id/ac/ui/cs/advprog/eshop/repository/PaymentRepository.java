package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment payment) {
        if (this.contains(payment)) {
            throw new IllegalArgumentException();
        }
        payments.add(payment);
        return payment;
    }

    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        return payment;
    }

    public Payment getPaymentById(String id) {
        for (Payment p : payments) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    public boolean contains(Payment payment) {
        for (Payment p : payments) {
            if (payment.getId().equals(p.getId())) {
                return true;
            }
        }
        return false;
    }
}
