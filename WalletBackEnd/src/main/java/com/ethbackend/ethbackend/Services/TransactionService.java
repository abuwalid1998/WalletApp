package com.ethbackend.ethbackend.Services;


import com.ethbackend.ethbackend.Model.Transaction;
import com.ethbackend.ethbackend.Repos.TransactionRepository;
import com.ethbackend.ethbackend.dto.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;



    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(Long id) {
        return transactionRepository.findAll();
    }




    }



