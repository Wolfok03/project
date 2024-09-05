package com.example.pro2.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pro2.Model.categories.categorie;

public interface CategoriesRespository extends JpaRepository<categorie, Integer>{
}
