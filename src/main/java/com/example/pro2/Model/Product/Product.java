


package com.example.pro2.Model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "products")
public class Product {
    @Id
    private int ProductId;
    private String ProductName;
    private String ProductDescription;
    private Double ProductPrice;
    private String ProductImage;
    private String Categorie_id;
    private int ProductQuantity;
    private int Brand_id;
    private int warranty_id;

    public int getWarranty_id() {
        return warranty_id;
    }

    public void setWarranty_id(int warranty_id) {
        this.warranty_id = warranty_id;
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

    public String getCategorie_id() {
        return Categorie_id;
    }

    public void setCategorie_id(String categorie_id) {
        this.Categorie_id = categorie_id;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }

    public int getBrand_id() {
        return Brand_id;
    }

    public void setBrands_id(int brand_id) {
        this.Brand_id = brand_id;
    }

    public void setId(int id) {
        this.ProductId = id;
    }

    public int getId() {
        return ProductId;
    }
}
