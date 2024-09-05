//package com.example.pro2.Controllers;
//
//import com.example.pro2.models.user;
//import com.example.pro2.models.userA;
//import com.example.pro2.services.CategoriesRespository;
//import com.example.pro2.services.ProductsRespository;
//import com.example.pro2.services.UserResponsitory;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//    @Autowired
//    private UserResponsitory repo;
//    private CategoriesRespository categoriesRespository;
//
//    @GetMapping({"", "/"})
//    public String showProductList(Model model) {
//        List<user> users = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
//        model.addAttribute("users",users);
//        return "users/UserList";
//    }
//
//    @GetMapping("/create")
//    public String showCreatePage (Model model) {
//
//        userA userA = new userA();
//        model.addAttribute("userA", userA);
//        return "users/CreateUser";
//    }
//
//
//    @PostMapping("/create")
//    public String createUser(
//            @Valid @ModelAttribute userA userA,
//            BindingResult result
//    ) {
//
//
//
//
//
//        user user = new user();
//        user.setTen (userA.getTen());
//        user.setDiaChi (userA.getDiaChi());
//        user.setEmail (userA.getEmail());
//        user.setGioiTinh (userA.getGioiTinh());
//        user.setSdt (userA.getSdt());
//        user.setPassword (userA.getPassword());
//        user.setUsername (userA.getUsername());
//        user.setNgaySinh (userA.getNgaySinh());
//
//        ;
//        repo.save(user);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/edit")
//    public String showEditPage(
//            Model model,
//            @RequestParam int id
//    ) {
//        try {
//            user user = repo.findById(id).get();
//            model.addAttribute("user", user);
//            userA userA = new userA();
//            userA.setTen (user.getTen());
//            userA.setDiaChi (user.getDiaChi());
//            userA.setEmail (user.getEmail());
//            userA.setGioiTinh (user.getGioiTinh());
//            userA.setSdt (user.getSdt());
//            userA.setPassword (user.getPassword());
//            userA.setUsername (user.getUsername());
//            userA.setNgaySinh (user.getNgaySinh());
//
//            model.addAttribute("userA", userA);
//        } catch (Exception ex) {
//            System.out.println("Exception: " + ex.getMessage());
//            return "redirect:/users";
//        }
//        return "users/EditUser";
//    }
//
//
//
//    @PostMapping("/edit")
//    public String updateUser (
//            Model model,
//            @RequestParam int id,
//            @Valid @ModelAttribute userA userA
//
//    ) {
//        try {
//            user user = repo.findById(id).get();
//            model.addAttribute("user", user);
//
//
//            user.setTen (userA.getTen());
//            user.setDiaChi (userA.getDiaChi());
//            user.setEmail (userA.getEmail());
//            user.setGioiTinh (userA.getGioiTinh());
//            user.setSdt (userA.getSdt());
//            user.setPassword (userA.getPassword());
//            user.setUsername (userA.getUsername());
//            user.setNgaySinh (userA.getNgaySinh());
//
//            repo.save(user);
//
//
//        }
//        catch (Exception ex) {
//            System.out.println("Exception: " + ex.getMessage());
//        }
//        return "redirect:/users";
//    }
//
//    @GetMapping("/delete")
//    public String deleteProduct (
//            @RequestParam int id
//    ) {
//        try {
//            user user = repo.findById(id).get();
//
//
//            //Xóa product
//            repo.delete (user);
//        }
//        catch (Exception ex) {
//            System.out.println("Exception: " + ex.getMessage());
//        }
//        return "redirect:/users";
//    }
//}
