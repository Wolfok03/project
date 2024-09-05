package com.example.pro2.services;

import com.example.pro2.Model.brands.brand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRespository extends JpaRepository<brand, Integer> {
}
