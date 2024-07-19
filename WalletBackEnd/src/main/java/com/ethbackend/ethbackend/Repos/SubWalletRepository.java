package com.ethbackend.ethbackend.Repos;

import com.ethbackend.ethbackend.Model.SubWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubWalletRepository extends JpaRepository<SubWallet, UUID> {
}