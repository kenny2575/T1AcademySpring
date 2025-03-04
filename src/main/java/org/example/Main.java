package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.enums.ProductType;
import org.example.model.User;
import org.example.service.ProductService;
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
    CommandLineRunner commandLineRunner (UserService userService, ProductService productService) {
        return args -> {
            User user1 = userService.createUser(new User("Alice"));
            User user2 = userService.createUser(new User("Bob"));

            productService.createProduct(user1.getId(), ProductType.CARD);
            productService.createProduct(user1.getId(), ProductType.ACCOUNT);
            productService.createProduct(user2.getId(), ProductType.CARD);

            System.out.println("Products for Alice: " + productService.getProductsByUserId(user1.getId()));
            System.out.println("Products for Bob: " + productService.getProductsByUserId(user2.getId()));

        };
    }

}



