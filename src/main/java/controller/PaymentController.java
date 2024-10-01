package controller;
import exception.InsufficientFundException;
import jakarta.transaction.Transactional;
import model.Payment;
import model.request.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.PaymentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Payment addPayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO){
        Payment payment=new Payment();
        payment.setName(paymentRequestDTO.getName());
        payment.setType(paymentRequestDTO.getType());
        return paymentService.addPayment(payment);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public Payment updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentRequestDTO paymentRequestDTO){
        Payment payment=paymentService.getPaymentById(id);
        if (payment==null){
            throw new InsufficientFundException("Odeme için bakiye yetersiz.");
        }
        payment.setName(paymentRequestDTO.getName());
        payment.setType(paymentRequestDTO.getType());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setNote(paymentRequestDTO.getNote());
        return paymentService.updatePayment(payment);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Payment> getAllPayment(){
        return paymentService.getAllPayment();
    }

}
