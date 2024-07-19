package com.ethbackend.ethbackend.Model;

import jakarta.persistence.*;



import java.util.UUID;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(mappedBy = "mainWallet")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usd_account_id", referencedColumnName = "id")
    private SubWallet usdAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ethereum_account_id", referencedColumnName = "id")
    private SubWallet ethereumAccount;
}
