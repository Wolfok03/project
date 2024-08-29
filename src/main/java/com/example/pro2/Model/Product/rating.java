package com.example.pro2.Model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratings")
public class rating {
    @Id
    private int Rating_id;
    private int ProductID;
    private int Rating;
    private String Comment;

    public int getRating_id() {
        return Rating_id;
    }

    public void setRating_id(int rating_id) {
        Rating_id = rating_id;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
