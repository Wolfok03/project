package com.example.pro2.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pro2.Model.cart.cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartsRespository extends JpaRepository<cart, Integer> {
    Boolean existsByuserIdAndProductId(Integer userId, Integer productId);
    List<cart> findAllByUserId(Integer userId);

}
