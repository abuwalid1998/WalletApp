package com.ethbackend.ethbackend.Repos;


import com.ethbackend.ethbackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


}