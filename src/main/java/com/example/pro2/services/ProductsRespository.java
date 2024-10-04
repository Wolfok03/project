package com.example.pro2.services;

import com.example.pro2.Model.Product.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRespository extends JpaRepository<Product, Integer> {

}
