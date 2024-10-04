package com.example.pro2.Model.Product.colors;

import jakarta.persistence.*;

@Entity
@Table(name = "color")
public class color {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Color_id;
    private String Color_Name;
    private Double price;

    public int getColor_id() {
        return Color_id;
    }

    public void setColor_id(int color_id) {
        Color_id = color_id;
    }

    public String getColor_Name() {
        return Color_Name;
    }

    public void setColor_Name(String color_Name) {
        Color_Name = color_Name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
