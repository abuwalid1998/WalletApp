package com.ethbackend.ethbackend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChargeRequest {
    private UUID walletId;
    private double amount;
}