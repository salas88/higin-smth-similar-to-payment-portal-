package com.example.please.repo;

import com.example.please.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    List<Payment> findBySourceAccIdAndDestAccIdAndPayerIdAndRecipientId(
            int sourceAccId, int descAccId, int payerId, int recipientId);
}
