package com.example.pro2.Model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands")
public class brand {
    @Id
    private int Brand_id;
    private String Brand_Name;

    public int getBrand_id() {
        return Brand_id;
    }

    public void setBrand_id(int brand_id) {
        Brand_id = brand_id;
    }

    public String getBrand_Name() {
        return Brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        Brand_Name = brand_Name;
    }
}
