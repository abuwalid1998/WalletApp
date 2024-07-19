package com.ethbackend.ethbackend.Controller;


import com.ethbackend.ethbackend.Model.Transaction;
import com.ethbackend.ethbackend.Services.TransactionService;
import com.ethbackend.ethbackend.dto.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/get-all")
    public List<Transaction> getTransactions(@RequestHeader Long id) {
        return transactionService.getTransactions(id);
    }



}