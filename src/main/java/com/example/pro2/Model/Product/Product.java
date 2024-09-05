


package com.example.pro2.Model.Product;
import com.example.pro2.Model.brands.brand;
import com.example.pro2.Model.categories.categorie;
import com.example.pro2.Model.warranies.warranty;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String ProductName;
    private String ProductDescription;
    private Double ProductPrice;
    private String imageFileName;
    private Date createdAt;
    private int ProductQuantity;
    @ManyToOne()
    @JoinColumn(name = "Brand_id")
    private brand brand;

    @ManyToOne()
    @JoinColumn(name = "Categorie_id")
    private categorie categorie;





    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
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



    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }


    public com.example.pro2.Model.categories.categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(com.example.pro2.Model.categories.categorie categorie) {
        this.categorie = categorie;
    }

    public com.example.pro2.Model.brands.brand getBrand() {
        return brand;
    }

    public void setBrand(com.example.pro2.Model.brands.brand brand) {
        this.brand = brand;
    }
}
