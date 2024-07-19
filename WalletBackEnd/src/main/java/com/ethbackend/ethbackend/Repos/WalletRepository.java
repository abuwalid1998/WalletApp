package com.ethbackend.ethbackend.Repos;

import com.ethbackend.ethbackend.Model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}