package com.example.pro2.Model.warranies;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class warrantyA {
    @NotEmpty(message = "Nhập tên bảo hành")
    private String WarrantyName;
    @NotEmpty(message = "Nhap thoi han bao hanh")
    private int WarrantyDate;
    @NotEmpty(message = "The name is required")
    private String category;
    @Min(0)
    private double WarrantyPrice;
    @Size(min = 10, message = "The description should be at least 10 characters")
    @Size(max = 2000, message = "The description cannot exceed 2000 characters")
    private String WarrantyDescription;

    public String getWarrantyName() {
        return WarrantyName;
    }

    public void setWarrantyName(String warrantyName) {
        WarrantyName = warrantyName;
    }

    public String getWarrantyDescription() {
        return WarrantyDescription;
    }

    public void setWarrantyDescription(String warrantyDescription) {
        WarrantyDescription = warrantyDescription;
    }

    public int getWarrantyDate() {
        return WarrantyDate;
    }

    public void setWarrantyDate(int warrantyDate) {
        WarrantyDate = warrantyDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getWarrantyPrice() {
        return WarrantyPrice;
    }

    public void setWarrantyPrice(double warrantyPrice) {
        WarrantyPrice = warrantyPrice;
    }
}
