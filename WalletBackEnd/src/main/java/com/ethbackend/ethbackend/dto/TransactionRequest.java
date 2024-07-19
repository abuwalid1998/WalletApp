package com.ethbackend.ethbackend.dto;


import lombok.Data;

import java.util.Date;

@Data
public class TransactionRequest {

    private String from;

    private String to;

    private double amount;

    private String description;

    private Date date;

}
