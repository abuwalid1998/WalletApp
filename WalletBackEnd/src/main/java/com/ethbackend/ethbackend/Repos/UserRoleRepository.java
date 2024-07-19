package com.ethbackend.ethbackend.Repos;

import com.ethbackend.ethbackend.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByRole(String user);
}
