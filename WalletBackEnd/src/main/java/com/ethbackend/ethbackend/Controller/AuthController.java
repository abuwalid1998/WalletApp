package com.ethbackend.ethbackend.Controller;


import com.ethbackend.ethbackend.Model.SubWallet;
import com.ethbackend.ethbackend.Model.User;
import com.ethbackend.ethbackend.Model.UserRole;
import com.ethbackend.ethbackend.Model.Wallet;
import com.ethbackend.ethbackend.Repos.UserRepository;
import com.ethbackend.ethbackend.Repos.UserRoleRepository;
import com.ethbackend.ethbackend.Repos.WalletRepository;
import com.ethbackend.ethbackend.Services.WalletService;
import com.ethbackend.ethbackend.Utils.JWTGenerator;
import com.ethbackend.ethbackend.dto.AuthResponseDTO;
import com.ethbackend.ethbackend.dto.LoginDto;
import com.ethbackend.ethbackend.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {


    private JWTGenerator JWTGenerator;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private UserRoleRepository rolesReop;
    private PasswordEncoder passwordEncoder;

    public AuthController(com.ethbackend.ethbackend.Utils.JWTGenerator jwtGenerator, UserRoleRepository rolesReop, PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager) {
        JWTGenerator = jwtGenerator;
        this.rolesReop = rolesReop;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;

    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDto user) {
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("{\"error\": \"User exists\"}");
            }

            User user1 = new User();
            user1.setUsername(user.getUsername());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));

            // Retrieve roles safely
            Optional<UserRole> userRoleOptional = rolesReop.findByRole("USER");
            if (!userRoleOptional.isPresent()) {
                return ResponseEntity.badRequest().body("{\"error\": \"Role 'USER' not found\"}");
            }
            Set<UserRole> userRoles = new HashSet<>(Collections.singletonList(userRoleOptional.get()));
            user1.setRoles(userRoles);

            // Create and set up the main wallet with sub-accounts
            Wallet wallet = new Wallet();

            SubWallet usdAccount = new SubWallet();
            usdAccount.setAmount(0.0);

            SubWallet ethereumAccount = new SubWallet();
            ethereumAccount.setAmount(0.0);

            wallet.setUsdAccount(usdAccount);
            wallet.setEthereumAccount(ethereumAccount);

            user1.setMainWallet(wallet);

            userRepository.save(user1);

            // Return JSON response for successful registration
            return ResponseEntity.ok().body("{\"message\": \"Register successful\"}");

        } catch (Exception e) {
            e.printStackTrace();
            // Return JSON response for failed registration
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Register failed\"}");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto user) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = JWTGenerator.generateJWT(authentication);

            AuthResponseDTO dto = new AuthResponseDTO(token);

            return ResponseEntity.ok().body(dto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}