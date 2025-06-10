package com.example.OnlineQuiz.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.OnlineQuiz.repository.UserRepository;
import com.example.OnlineQuiz.entity.*;
import com.example.OnlineQuiz.exceptions.ResourceNotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()) {
            throw new ResourceNotFoundException("Unimplemented method 'loadUserByUsername'");
        }
        return new UserPrincipal(user.get());
    }

    
}
