package com.ethbackend.ethbackend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;



@Getter
@Setter
@Entity
@Table(name = "sub_wallets")
public class SubWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @OneToOne(mappedBy = "usdAccount")
    private Wallet usdWallet;

    @OneToOne(mappedBy = "ethereumAccount")
    private Wallet ethereumWallet;
}
