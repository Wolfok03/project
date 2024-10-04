package com.example.pro2.Model.users;
import com.example.pro2.Model.users.roles.role;

import jakarta.persistence.*;

@Entity
@Table(name = "role_user")
public class role_user {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int role_user_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private role role;

    public int getRole_user_id() {
        return role_user_id;
    }

    public void setRole_user_id(int role_user_id) {
        this.role_user_id = role_user_id;
    }

    public com.example.pro2.Model.users.user getUser() {
        return user;
    }

    public void setUser(com.example.pro2.Model.users.user user) {
        this.user = user;
    }

    public com.example.pro2.Model.users.roles.role getRole() {
        return role;
    }

    public void setRole(com.example.pro2.Model.users.roles.role role) {
        this.role = role;
    }
}
