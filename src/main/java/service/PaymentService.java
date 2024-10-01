package service;

import model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
    }

    public Payment addPayment(Payment payment){
        paymentRepository.save(payment);
        return payment;
    }

    public void deletePayment(Long id){
        paymentRepository.deleteById(id);
    }

    public Payment updatePayment(Payment payment){
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public List<Payment> getAllPayment(){
        return paymentRepository.findAll();
    }
}
