package com.example.pro2.Model.brands;

import jakarta.persistence.*;

@Entity
@Table(name = "brands")
public class brand {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Brand_id;
    private String Brand_Name;
    private String Brand_Description;
    private String Brand_Image;

    public String getBrand_Description() {
        return Brand_Description;
    }

    public void setBrand_Description(String brand_Description) {
        Brand_Description = brand_Description;
    }

    public String getBrand_Image() {
        return Brand_Image;
    }

    public void setBrand_Image(String brand_Image) {
        Brand_Image = brand_Image;
    }

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
