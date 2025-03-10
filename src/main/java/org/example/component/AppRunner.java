package org.example.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        var users = userService.getAllUsers();
        var user = users.stream().filter(x -> "Bob".equals(x.getUsername())).findFirst();

        user.ifPresent(
                us -> log.info("Products for {} [{}] : {}", us.getUsername(), us.getId(), productService.getProductsByUserId(us.getId()))
        );

        var product = productService.getProductById(105L);
        product.ifPresent(pr -> log.info("Product: {}", pr));

    }
}