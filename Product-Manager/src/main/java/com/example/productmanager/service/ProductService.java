package com.example.productmanager.service;


import com.example.productmanager.entity.Product;
import com.example.productmanager.entity.User;
import com.example.productmanager.repository.ProductRepository;
import com.example.productmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product createProduct(Long userId, Product product) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        product.setUser(user);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product product = getProductById(productId);

        if (updatedProduct.getName() != null) {
            product.setName(updatedProduct.getName());
        }
        if (updatedProduct.getDescription() != null) {
            product.setDescription(updatedProduct.getDescription());
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }
    public List<Product> getProductsByUser(Long userId) {
        // Проверяем, существует ли пользователь с указанным ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));

        // Получаем все продукты, принадлежащие этому пользователю
        return productRepository.findByUser(user);
    }
}

