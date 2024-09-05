package com.example.pro2.Model.categories;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class categorie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Categorie_id;
    private String Categorie_name;

    public int getCategorie_id() {
        return Categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        Categorie_id = categorie_id;
    }

    public String getCategorie_name() {
        return Categorie_name;
    }

    public void setCategorie_name(String categorie_name) {
        Categorie_name = categorie_name;
    }
}
