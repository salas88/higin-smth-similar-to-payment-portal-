package com.example.please.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Enumeration;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_type")
    private AccountType accountType;
    private Double balance;

    @ManyToOne(cascade = CascadeType.ALL,
                            fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    public Account(AccountType accountType, Double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }
}
