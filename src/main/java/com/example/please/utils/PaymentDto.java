package com.example.please.utils;

import com.example.please.entity.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto {
    private Integer payment_id;
    private LocalDateTime timestamp;
    private Integer src_acc_num;
    private Integer dest_acc_num;
    private Double amount;
    private Payer payer;
    private Recipient recipient;

    public static class Builder{
        private PaymentDto paymentDto;

        public Builder(){
            paymentDto = new PaymentDto();
        }

        public Builder withPaymentId(Integer paymentId){
            paymentDto.payment_id = paymentId;
            return this;
        }
        public Builder withTime(LocalDateTime localDateTime){
            paymentDto.timestamp = localDateTime;
            return this;
        }
        public Builder withSourceAcc(Integer sourceAcc){
            paymentDto.src_acc_num = sourceAcc;
            return this;
        }
        public Builder withDescAcc(Integer descAcc){
            paymentDto.dest_acc_num = descAcc;
            return this;
        }
        public Builder withAmount(Double amount){
            paymentDto.amount= amount;
            return this;
        }
        public Builder withPayer(Payer payer){
            paymentDto.payer= payer;
            return this;
        }
        public Builder withRecipient(Recipient recipient){
            paymentDto.recipient= recipient;
            return this;
        }
        public PaymentDto build(){
            return paymentDto;
        }
    }

}
