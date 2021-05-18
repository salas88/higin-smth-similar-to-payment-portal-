package com.example.please.service.inter;

import com.example.please.entity.Payment;
import com.example.please.utils.PaymentDto;

import java.util.List;
import java.util.Optional;

public interface InterPaymentService {
    Optional<Payment> findById(int theId);
    boolean savePayment(Payment payment);
    List<PaymentDto> findAll(Payment payment);
}
