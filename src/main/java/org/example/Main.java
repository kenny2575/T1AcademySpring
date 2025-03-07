package org.example;


import org.example.service.UserService;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan
public class Main {
    public static void main(String[] args) {
       AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

       var userService = context.getBean(UserService.class);

        userService.createUser("Alice");
        userService.createUser("Bob");

        // Получение всех пользователей
        List<User> users = userService.getAllUsers();
        System.out.println("Users: " + users);

        // Получение пользователя по ID
        if (!users.isEmpty()) {
            User user = userService.getUser(users.get(0).id());
            System.out.println("Found user: " + user);

            // Удаление пользователя
            userService.deleteUser(users.get(0).id());
            System.out.println("Users after deletion: " + userService.getAllUsers());
        }
        context.close();
    }

}



