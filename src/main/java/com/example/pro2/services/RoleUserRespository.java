package com.example.pro2.services;

import com.example.pro2.Model.users.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  RoleUserRespository extends JpaRepository<role_user, Integer> {
    role_user findByUser(user user);
}
