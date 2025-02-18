package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(String username) {
        userDao.save(new User(null, username));
    }

    public User getUser(Long id) {
        return userDao.findById(id).orElseThrow();
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
