package com.ethbackend.ethbackend.Controller;


import com.ethbackend.ethbackend.Model.User;
import com.ethbackend.ethbackend.Services.UserServices;
import com.ethbackend.ethbackend.Utils.EthereumPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class InformationController {

    @Autowired
    EthereumPriceService priceService;

    @Autowired
    UserServices userServices;

    @GetMapping("/get-price")
    public Double getPrice() {
        return priceService.getEthereumPriceInUsd().block();
    }

    @GetMapping("/get-user-info")
    public User getUser(@RequestParam String username) {
        System.out.println(username);
        return userServices.getUser(username);
    }
    @GetMapping("/get-eth-wallet")
    public String getEthereumWallet(@RequestParam String username) {
        System.out.println(username);
        return userServices.getUser(username).getMainWallet().getEthereumAccount().toString();
    }

    @GetMapping("/get-usd-wallet")
    public String getUsdWallet(@RequestParam String username) {
        System.out.println(username);
        return userServices.getUser(username).getMainWallet().getUsdAccount().toString();
    }


}
