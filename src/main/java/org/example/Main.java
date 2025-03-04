package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (UserService userService) {
        return args -> {
            var user1 = new User(null, "user1");
            var user2 = new User(null, "user2");
            userService.createUser(user1);
            userService.createUser(user2);
            userService.getAllUsers().forEach(x -> log.info(x.toString()));

            var newUser = userService.getUser(42L);

            log.info("Name = {}",  newUser.getId());
        };
    }

}



