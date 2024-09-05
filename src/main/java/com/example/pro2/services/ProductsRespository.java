package com.example.pro2.services;

import com.example.pro2.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRespository extends JpaRepository<Product, Integer> {
}
