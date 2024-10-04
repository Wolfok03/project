package com.example.pro2.Model.users.roles;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "role")
public class role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Role_id;
    private String name;

    public int getRole_id() {
        return Role_id;
    }

    public void setRole_id(int role_id) {
        Role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
