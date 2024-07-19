package com.ethbackend.ethbackend.Controller;


import com.ethbackend.ethbackend.Model.ChargeResponse;
import com.ethbackend.ethbackend.Model.Wallet;
import com.ethbackend.ethbackend.Services.WalletService;
import com.ethbackend.ethbackend.dto.BuyRequest;
import com.ethbackend.ethbackend.dto.ChargeRequest;
import com.ethbackend.ethbackend.dto.SellRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/test")
    public String test() {
        return "Authorized";
    }

    @PostMapping("/charge")
    public ChargeResponse chargeWallet(@RequestBody ChargeRequest chargeRequest) {
        return walletService.chargeWallet(chargeRequest.getWalletId(), chargeRequest.getAmount());
    }

    @PostMapping("/buy")
    public ChargeResponse buyEthereum(@RequestBody BuyRequest buyRequest) {
        return walletService.buyEthereum(buyRequest.getWalletId(), buyRequest.getAmount());
    }

    @PostMapping("/sell")
    public ChargeResponse sellEthereum(@RequestBody SellRequest sellRequest) {
        return walletService.sellEthereum(sellRequest.getWalletId(), sellRequest.getAmount());
    }
}
