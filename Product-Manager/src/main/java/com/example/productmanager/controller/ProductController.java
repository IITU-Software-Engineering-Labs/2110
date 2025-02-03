package com.example.productmanager.controller;

import com.example.productmanager.entity.Product;
import com.example.productmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Создание продукта для указанного пользователя.
     *
     * @param userId ID пользователя
     * @param product объект Product с данными продукта
     * @return созданный объект Product или сообщение об ошибке
     */
    @PostMapping("/{userId}/create")
    public ResponseEntity<?> createProduct(@PathVariable Long userId, @RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(userId, product);
            return ResponseEntity.ok(createdProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Получение всех продуктов.
     *
     * @return список всех продуктов
     */
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Получение информации о продукте по ID.
     *
     * @param productId ID продукта
     * @return объект Product или сообщение об ошибке
     */
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Обновление продукта.
     *
     * @param productId ID продукта
     * @param updatedProduct обновленные данные продукта
     * @return обновленный объект Product или сообщение об ошибке
     */
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct
    ) {
        try {
            Product product = productService.updateProduct(productId, updatedProduct);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Удаление продукта.
     *
     * @param productId ID продукта
     * @return сообщение об успешном удалении или об ошибке
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}

