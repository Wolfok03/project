package com.example.pro2.Model.warranies;

import jakarta.persistence.*;

@Entity
@Table(name = "warranty")
public class warranty {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Warranty_id;
    private String WarrantyName;
    private int WarrantyDate;
    private double WarrantyPrice;
    private String WarrantyDescription;

    public int getWarranty_id() {
        return Warranty_id;
    }

    public void setWarranty_id(int warranty_id) {
        Warranty_id = warranty_id;
    }

    public String getWarrantyName() {
        return WarrantyName;
    }

    public void setWarrantyName(String warrantyName) {
        WarrantyName = warrantyName;
    }

    public int getWarrantyDate() {
        return WarrantyDate;
    }

    public void setWarrantyDate(int warrantyDate) {
        WarrantyDate = warrantyDate;
    }

    public String getWarrantyDescription() {
        return WarrantyDescription;
    }

    public void setWarrantyDescription(String warrantyDescription) {
        WarrantyDescription = warrantyDescription;
    }

    public double getWarrantyPrice() {
        return WarrantyPrice;
    }

    public void setWarrantyPrice(double warrantyPrice) {
        WarrantyPrice = warrantyPrice;
    }
}
