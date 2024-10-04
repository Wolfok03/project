package com.example.pro2.Model.cart;
import com.example.pro2.Model.users.user;
import com.example.pro2.Model.Product.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Cart_id;
    private int Cart_quantity;
    private double Cart_sum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public int getCart_id() {
        return Cart_id;
    }

    public void setCart_id(int cart_id) {
        Cart_id = cart_id;
    }

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

    public com.example.pro2.Model.users.user getUser() {
        return user;
    }

    public void setUser(com.example.pro2.Model.users.user user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
