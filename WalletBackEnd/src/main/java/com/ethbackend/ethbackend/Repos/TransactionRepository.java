package com.ethbackend.ethbackend.Repos;

import com.ethbackend.ethbackend.Model.Transaction;
import com.ethbackend.ethbackend.Model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}