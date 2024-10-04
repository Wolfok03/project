package com.example.pro2.Model.cart;

import com.example.pro2.Model.Product.Product;
import com.example.pro2.Model.users.user;
import jakarta.persistence.*;


public class cartA {

    private int Cart_quantity;
    private double Cart_sum;


    private int user;

    private int product;

    private double tong;

    public int getCart_quantity() {
        return Cart_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        Cart_quantity = cart_quantity;
    }

    public double getCart_sum() {
        return Cart_sum;
    }

    public void setCart_sum(double cart_sum) {
        Cart_sum = cart_sum;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public double getTong() {
        return tong;
    }

    public void setTong(double tong) {
        this.tong = tong;
    }
}
