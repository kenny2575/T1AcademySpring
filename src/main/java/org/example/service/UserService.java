package org.example.service;


import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getUser(Long id) {
        return userRepository.getReferenceById(id);
    }
}
