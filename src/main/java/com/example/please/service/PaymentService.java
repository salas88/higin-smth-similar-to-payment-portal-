package com.example.please.service;

import com.example.please.entity.Account;
import com.example.please.entity.Payment;
import com.example.please.error.UnprocessableEntityException;
import com.example.please.repo.AccountRepo;
import com.example.please.repo.PaymentRepo;
import com.example.please.service.inter.InterPaymentService;
import com.example.please.utils.Payer;
import com.example.please.utils.PaymentDto;
import com.example.please.utils.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService implements InterPaymentService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public boolean savePayment(Payment payment) {

        Account accountSource = accountService.findById(payment.getSourceAccId()).get();
        Account accountDest = accountService.findById(payment.getDestAccId()).get();
        Double accountSourceBalance = accountSource.getBalance();
        Double accountDestBalance = accountDest.getBalance();
        Double amount = payment.getAmount();

            if(accountSourceBalance >= amount){
                accountSource.setBalance( (accountSourceBalance-amount) );
                accountDest.setBalance( (accountDestBalance+amount) );
                payment.setPayerId(accountSource.getClient().getId());
                payment.setRecipientId(accountDest.getClient().getId());
                accountService.saveAccount(accountSource);
                accountService.saveAccount(accountDest);
                paymentRepo.save(payment);
                return true;
            } else {
                throw new UnprocessableEntityException("недостаточно средствв на счете");
            }
    }


    @Override
    public Optional<Payment> findById(int theId) {
        return paymentRepo.findById(theId);
    }


    @Override
    public List<PaymentDto> findAll(Payment payment) {

        Integer sourceAccId = payment.getSourceAccId();
        Integer destAccId = payment.getDestAccId();
        Integer payerId = payment.getPayerId();
        Integer recipientId = payment.getRecipientId();
        List<Payment> payments = paymentRepo.findBySourceAccIdAndDestAccIdAndPayerIdAndRecipientId(
                sourceAccId, destAccId, payerId, recipientId);

        return payments.stream().map(el -> {

            Optional<Account> sourceAcc = accountService.findById(sourceAccId);
            Optional<Account> descAcc = accountService.findById(destAccId);

            return new PaymentDto.Builder()
                    .withPaymentId(el.getId())
                    .withTime(el.getCreateDateTime())
                    .withSourceAcc(el.getSourceAccId())
                    .withDescAcc(el.getDestAccId())
                    .withAmount(el.getAmount())
                    .withPayer(new Payer(
                            sourceAcc.get().getClient().getFirstName(),
                            sourceAcc.get().getClient().getLastName()
                    ))
                    .withRecipient(new Recipient(
                            descAcc.get().getClient().getFirstName(),
                            descAcc.get().getClient().getLastName()
                    )).build();

        }).collect(Collectors.toList());
    }
}
