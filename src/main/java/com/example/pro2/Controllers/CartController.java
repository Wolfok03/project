//package com.example.pro2.Controllers;
//
//import com.example.pro2.Model.Product.Product;
//import com.example.pro2.Model.cart.cart;
//import com.example.pro2.Model.cart.cartA;
//
//import com.example.pro2.services.CartsRespository;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/carts")
//public class CartController {
//    @JoinColumn
//    @Autowired
//    private CartsRespository repo;
//
//    @GetMapping({"", "/"})
//    public String showProductList(Model model) {
//        List<cart> carts = repo.findAll();
//        model.addAttribute("carts", carts);
//
//        return "carts/CartList";
//    }
//
//
//}
