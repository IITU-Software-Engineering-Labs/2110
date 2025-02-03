package com.example.productmanager.repository;

import com.example.productmanager.entity.Product;
import com.example.productmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
}
