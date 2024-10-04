package com.example.pro2.services;
import com.example.pro2.Model.users.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<user, Integer>{
    user  findByUsernameAndPassword(String username, String password);
    user findByUsername(String username);

}
