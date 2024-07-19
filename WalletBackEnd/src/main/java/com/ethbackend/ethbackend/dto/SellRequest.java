package com.ethbackend.ethbackend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SellRequest {
    private UUID walletId;
    private double amount;
}