package com.example.please.entity;

import com.sun.istack.Nullable;
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
    @Nullable
    private Double amount;
    @Nullable
    private String reason;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @Nullable
    private Integer payerId;
    @Nullable
    private Integer recipientId;
}
