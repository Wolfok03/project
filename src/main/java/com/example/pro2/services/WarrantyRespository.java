package com.example.pro2.services;

import com.example.pro2.Model.warranies.warranty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyRespository extends JpaRepository<warranty, Integer>{
}
