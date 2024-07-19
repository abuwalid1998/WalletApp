package com.ethbackend.ethbackend.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ChargeResponse {
    private String message;
    private double amount;
}
