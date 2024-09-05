package com.example.pro2.Model.brands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class brandA {
    @NotEmpty(message = "The name is required")
    private String Brand_Name;

    @NotEmpty(message = "The name is required")
    private MultipartFile Brand_Image;
    @Min(0)

    @Size(min = 10, message = "The description should be at least 10 characters")
    @Size(max = 2000, message = "The description cannot exceed 2000 characters")
    private String Brand_Description;
//    private MultipartFile imageFile;


    public MultipartFile getBrand_Image() {
        return Brand_Image;
    }

    public void setBrand_Image(MultipartFile brand_Image) {
        Brand_Image = brand_Image;
    }

    public String getBrand_Name() {
        return Brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        Brand_Name = brand_Name;
    }



    public String getBrand_Description() {
        return Brand_Description;
    }

    public void setBrand_Description(String brand_Description) {
        Brand_Description = brand_Description;
    }
}
