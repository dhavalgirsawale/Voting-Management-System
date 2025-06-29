package com.example.votingapp.service;

import com.example.votingapp.model.User;
import com.example.votingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User authenticateUser(String userId, String password) {
        return userRepository.findByUserIdAndPassword(userId, password);
    }
    
    public User authenticateAdmin(String userId, String password) {
        return userRepository.findByUserIdAndPasswordAndIsAdmin(userId, password, true);
    }
    
    public void registerUser(User user) {
        userRepository.save(user);
    }
}