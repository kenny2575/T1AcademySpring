package org.example;


import org.example.model.User;
import org.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (UserService userService) {
        return args -> {
            userService.createUser(new User(null, "user1"));
            userService.createUser(new User(null, "user2"));
            userService.getAllUsers().forEach(System.out::println);
        };
    }

}



