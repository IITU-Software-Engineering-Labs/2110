package com.example.productmanager.controller;

import com.example.productmanager.entity.Product;
import com.example.productmanager.entity.User;
import com.example.productmanager.service.ProductService;
import com.example.productmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    /**
     * Регистрация нового пользователя.
     *
     * @param user объект User с данными пользователя
     * @return объект User или сообщение об ошибке
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Авторизация пользователя.
     *
     * @param loginRequest запрос с полями username и password
     * @return объект User или сообщение об ошибке
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        try {
            User user = userService.login(username, password);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Получение всех продуктов, принадлежащих пользователю.
     *
     * @param userId ID пользователя
     * @return список продуктов
     */
    @GetMapping("/{userId}/products")
    public ResponseEntity<?> getUserProducts(@PathVariable Long userId) {
        try {
            List<Product> products = productService.getProductsByUser(userId);
            return ResponseEntity.ok(products);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
