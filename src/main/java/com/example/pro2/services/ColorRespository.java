package com.example.pro2.services;

import com.example.pro2.Model.Product.colors.color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRespository extends JpaRepository<color, Integer> {
}
