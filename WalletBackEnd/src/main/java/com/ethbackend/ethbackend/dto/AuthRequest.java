package com.ethbackend.ethbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@AllArgsConstructor
public class AuthRequest {

    private String username;
    private String password;
}
