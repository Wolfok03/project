package com.example.pro2.Model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class categorie {
    @Id
    private int Categorie_id;
    private String Categorie_name;

    public int getCategorie_id() {
        return Categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.Categorie_id = categorie_id;
    }

    public String getCategorie_name() {
        return Categorie_name;
    }

    public void setCategorie_name(String categorie_name) {
        this.Categorie_name = categorie_name;
    }
}
