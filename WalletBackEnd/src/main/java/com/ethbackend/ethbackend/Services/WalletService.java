package com.ethbackend.ethbackend.Services;



import com.ethbackend.ethbackend.Model.ChargeResponse;
import com.ethbackend.ethbackend.Model.Transaction;
import com.ethbackend.ethbackend.Model.Wallet;
import com.ethbackend.ethbackend.Repos.WalletRepository;
import com.ethbackend.ethbackend.Utils.EthereumPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final EthereumPriceService ethereumPriceService;

    final TransactionService transactionService;

    public WalletService(WalletRepository walletRepository, EthereumPriceService ethereumPriceService, TransactionService transactionService) {
        this.walletRepository = walletRepository;
        this.ethereumPriceService = ethereumPriceService;
        this.transactionService = transactionService;
    }


    public ChargeResponse chargeWallet(UUID walletId, double amount) {

        try {
            Wallet wallet = walletRepository.findById(walletId).get();
            wallet.getUsdAccount().setAmount(amount);
            walletRepository.save(wallet);
            return  new ChargeResponse("Your Wallet has been charged Successfully" , amount);
        }catch (Exception e){
            return  new ChargeResponse("Operation Fails " , amount);
        }


    }

    public ChargeResponse buyEthereum(UUID walletId, double amount) {
        Double pricenow = ethereumPriceService.getEthereumPriceInUsd().block();
        System.out.println(pricenow);
        double price = pricenow.doubleValue() * amount;
        Wallet wallet = walletRepository.findById(walletId).get();
        if (wallet.getUsdAccount().getAmount() < pricenow) {
            return new ChargeResponse("You Dont Have Enough USD",amount);
        }else {
            double numberofeth = amount * pricenow.doubleValue() ;
            System.out.println("******************** " + numberofeth);
            wallet.getEthereumAccount().setAmount(wallet.getEthereumAccount().getAmount() + amount);
            double accountbalance = wallet.getUsdAccount().getAmount();
            wallet.getUsdAccount().setAmount(accountbalance - price);
            walletRepository.save(wallet);

            System.out.println("Im Here");
            // Create and save transaction log
            Transaction transaction = new Transaction();
            transaction.setUser(wallet.getUser());
            transaction.setFromWallet(wallet.getUsdAccount().getId().toString());
            transaction.setToWallet(wallet.getEthereumAccount().getId().toString());
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setAmount(amount);
            transaction.setCurrency("USD");

            transactionService.saveTransaction(transaction);

            return new ChargeResponse("Operation Successfully ",amount);
        }
    }

    public ChargeResponse sellEthereum(UUID walletId, double amount) {
        Double pricenow = ethereumPriceService.getEthereumPriceInUsd().block();
        double price = pricenow * amount;

        Wallet wallet = walletRepository.findById(walletId).orElse(null);
        if (wallet == null) {
            return new ChargeResponse("Wallet not found", amount);
        }

        // Check if the wallet has enough Ethereum
        double ethereumBalance = wallet.getEthereumAccount().getAmount();
        if (ethereumBalance < amount) {
            return new ChargeResponse("Insufficient Ethereum balance", amount);
        }

        // Deduct Ethereum from the wallet
        wallet.getEthereumAccount().setAmount(ethereumBalance - amount);

        // Add USD to the wallet
        double usdBalance = wallet.getUsdAccount().getAmount();
        wallet.getUsdAccount().setAmount(usdBalance + price);

        // Save the updated wallet
        walletRepository.save(wallet);

        // Create and save transaction log
        Transaction transaction = new Transaction();
        transaction.setUser(wallet.getUser());
        transaction.setFromWallet(wallet.getEthereumAccount().getId().toString());
        transaction.setToWallet(wallet.getUsdAccount().getId().toString());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setCurrency("ETH");

        transactionService.saveTransaction(transaction);

        return new ChargeResponse("Ethereum sold successfully", amount);
    }

}
