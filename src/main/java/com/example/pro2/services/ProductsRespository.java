package com.example.pro2.services;

import com.example.pro2.Model.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRespository extends JpaRepository<product, Integer> {
}
