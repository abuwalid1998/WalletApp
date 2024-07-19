package com.ethbackend.ethbackend.Services;

import com.ethbackend.ethbackend.Model.User;
import com.ethbackend.ethbackend.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;


    public User getUser(String username) {
        System.out.println(username+"2");
        User user = userRepository.findByUsername(username).get();
        System.out.println(username+ "3");
        return user;
    }

}
