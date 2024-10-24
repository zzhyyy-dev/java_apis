package com.example.vritual.service;


import com.example.vritual.entities.User;
import com.example.vritual.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        // Para simplicidade, não estamos criptografando a senha
        return userRepository.save(user);
    }
}
