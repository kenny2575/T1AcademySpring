package org.example.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        var user1 = new User(null, "user1");
        var user2 = new User(null, "user2");
        userService.createUser(user1);
        userService.createUser(user2);
        userService.getAllUsers().forEach(x -> log.info(x.toString()));
    }
}