package com.example.please.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "source_acc_id")
    private Integer sourceAccId;
    @Column(name = "dest_acc_id")
    private Integer destAccId;
    private Double amount;
    private String reason;
    @CreationTimestamp
    private LocalDateTime createDateTime;


    public Payment(Integer sourceAccId, Integer destAccId, Double amount, String reason) {
        this.sourceAccId = sourceAccId;
        this.destAccId = destAccId;
        this.amount = amount;
        this.reason = reason;
    }



}
