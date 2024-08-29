package com.example.pro2.Model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "warranty")
public class warranty {
    @Id
    private int warranty_id;
    private String warrantyName;
    private Date warrantyDate;
    private String warrantyDescription;
    private String warrantyPrice;

    public int getWarranty_id() {
        return warranty_id;
    }

    public void setWarranty_id(int warranty_id) {
        this.warranty_id = warranty_id;
    }

    public String getWarrantyName() {
        return warrantyName;
    }

    public void setWarrantyName(String warrantyName) {
        this.warrantyName = warrantyName;
    }

    public Date getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(Date warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public String getWarrantyDescription() {
        return warrantyDescription;
    }

    public void setWarrantyDescription(String warrantyDescription) {
        this.warrantyDescription = warrantyDescription;
    }

    public String getWarrantyPrice() {
        return warrantyPrice;
    }

    public void setWarrantyPrice(String warrantyPrice) {
        this.warrantyPrice = warrantyPrice;
    }
}
