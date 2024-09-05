package com.example.pro2.Model.Product;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class ProductA {
    private String ProductName;
    private String ProductDescription;
    private Double ProductPrice;
    private String ProductImage;
    private int Brand_id;
    private int Categorie_id;

    private int ProductQuantity;

    private MultipartFile imageFile;

    public int getBrand_id() {
        return Brand_id;
    }

    public void setBrand_id(int brand_id) {
        Brand_id = brand_id;
    }

    public int getCategorie_id() {
        return Categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        Categorie_id = categorie_id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public Double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(Double productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }



    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }



    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
