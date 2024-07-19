package com.ethbackend.ethbackend.Services;

import com.ethbackend.ethbackend.Model.User;
import com.ethbackend.ethbackend.Model.UserRole;
import com.ethbackend.ethbackend.Repos.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Inject your user repository or any data access layer here

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<User> user = userRepository.findByUsername(username);

        String password = user.get().getPassword(); // Replace with actual retrieval logic
        List<UserRole> role = user.get().getRoles().stream().toList(); // Replace with actual retrieval logic

        return new org.springframework.security.core.userdetails.User(username, password, getAuthorities(user.get().getRoles().stream().toList()));
    }




    private Collection<GrantedAuthority> getAuthorities(List<UserRole> user) {
       return user.stream().map(
               userRoles -> new SimpleGrantedAuthority(userRoles.getRole())).collect(Collectors.toList()
       );
    }
}