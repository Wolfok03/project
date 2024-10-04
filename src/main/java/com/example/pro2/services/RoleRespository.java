package com.example.pro2.services;
import com.example.pro2.Model.users.roles.role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRespository extends JpaRepository<role, Integer> {

}
