package com.example.pro2.Controllers;

import com.example.pro2.services.RoleUserRespository;
import com.example.pro2.services.UserRespository;

import com.example.pro2.Model.users.user;
import com.example.pro2.Model.users.userA;
import com.example.pro2.Model.users.roles.role;
import com.example.pro2.Model.users.roles.roleA;
import com.example.pro2.Model.users.role_user;
import com.example.pro2.Model.users.role_userA;

import com.example.pro2.services.RoleRespository;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.Valid;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.xml.crypto.Data;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRespository A;

    @Autowired
    private RoleRespository role;

    @Autowired
    private RoleUserRespository roleuser;

    @Autowired
    private UserRespository us;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<user> users = A.findAll();
        model.addAttribute("users", users);
        return "users/UserList";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {

        userA userA = new userA();
        model.addAttribute("userA", userA);
        return "users/CreateUser";
    }




    private static final String SALT = "gfgfgdrthgfg";

    public static String toSHA256(String str) {
        try {
            // Concatenate string and salt
            byte[] data = str.getBytes("UTF-8");
            byte[] saltedData = new byte[data.length + SALT.length()];
            System.arraycopy(data, 0, saltedData, 0, data.length);
            System.arraycopy(SALT.getBytes("UTF-8"), 0, saltedData, data.length, SALT.length());

            // Hash the salted data using SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(saltedData);

            // Encode the hash in Base64
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/create")
    public String createUser(
            @Valid @ModelAttribute userA userA

    ) {

//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(userA.getPassword());

        user user = new user();
        user.setTen(userA.getTen());
        user.setDiaChi(userA.getDiaChi());
        user.setEmail(userA.getEmail());
        user.setGioiTinh(userA.getGioiTinh());
        user.setSdt(userA.getSdt());
        String a = toSHA256(userA.getPassword());
        user.setPassword(a);
        user.setUsername(userA.getUsername());
        user.setNgaySinh(userA.getNgaySinh());


        ;
        A.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        try {
            user user = A.findById(id).get();
            model.addAttribute("user", user);
            userA userA = new userA();
            userA.setTen(user.getTen());
            userA.setDiaChi(user.getDiaChi());
            userA.setEmail(user.getEmail());
            userA.setGioiTinh(user.getGioiTinh());
            userA.setSdt(user.getSdt());
            userA.setPassword(user.getPassword());
            userA.setUsername(user.getUsername());
            userA.setNgaySinh(user.getNgaySinh());

            model.addAttribute("userA", userA);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/users";
        }
        return "users/EditUser";
    }


    @PostMapping("/edit")
    public String updateUser(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute userA userA

    ) {
        try {
            user user = A.findById(id).get();
            model.addAttribute("user", user);


            user.setTen(userA.getTen());
            user.setDiaChi(userA.getDiaChi());
            user.setEmail(userA.getEmail());
            user.setGioiTinh(userA.getGioiTinh());
            user.setSdt(userA.getSdt());
            user.setPassword(userA.getPassword());
            user.setUsername(userA.getUsername());
            user.setNgaySinh(userA.getNgaySinh());

            A.save(user);


        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam int id
    ) {
        try {
            user user = A.findById(id).get();


            //Xóa product
            A.delete(user);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/roles")
    public String listRole(Model model) {
        List<role> roles = role.findAll();
        model.addAttribute("roles", roles);
        return "users/roles/RoleList";
    }

    @GetMapping("/roles/create")
    public String showCreatePageRole(Model model) {

        roleA roleA = new roleA();
        model.addAttribute("roleA", roleA);
        return "users/roles/CreateRole";
    }


    @PostMapping("/roles/create")
    public String createRole(
            @Valid @ModelAttribute roleA roleA

    ) {


        role role1 = new role();
        role1.setName(roleA.getName());

        role.save(role1);
        return "redirect:/users/roles";
    }

    @GetMapping("/roles/edit")
    public String showEditPageRole(
            Model model,
            @RequestParam int id
    ) {
        try {
            role role1 = role.findById(id).get();
            model.addAttribute("role", role1);
            roleA roleA = new roleA();
            roleA.setName(role1.getName());

            model.addAttribute("roleA", roleA);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/users/roles";
        }
        return "users/roles/EditRole";
    }


    @PostMapping("/roles/edit")
    public String updateRole(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute roleA roleA

    ) {
        try {
            role role1 = role.findById(id).get();
            model.addAttribute("role", role1);

            role1.setName((roleA.getName()));


            role.save(role1);


        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/users/roles";
    }

    @GetMapping("/roles/delete")
    public String deleteRole(
            @RequestParam int id
    ) {
        try {
            role role1 = role.findById(id).get();

            role.delete(role1);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/users/roles";
    }

    @GetMapping("/role_user")
    public String listRoleUser(Model model) {
        List<role_user> role_users = roleuser.findAll();
        model.addAttribute("roleuser", role_users);
        return "users/role_user/RoleUserList";
    }


    @GetMapping("/role_user/create")
    public String showEditPage1(
            Model model,
            @RequestParam int id
    ) {
        try {
            List<role> role1 = role.findAll();
            model.addAttribute("role", role1);
            user user = us.findById(id).get();
            role_userA roleUser = new role_userA();
            model.addAttribute("roleuserA", roleUser);
            roleUser.setUserid(user.getTen());


        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/users";
        }
        return "users/role_user/CreateRoleUser";
    }


    @PostMapping("/role_user/create")
    public String updateUser1(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute role_userA roleUserA

    ) {
        try {
            role_user roleuser1 = new role_user();
            model.addAttribute("roleuser", roleuser1);

//            String username = roleUserA.getUserid();
//            user user = A.findByUsername(username);
            user user = us.findById(id).get();

            int roleid = roleUserA.getRoleid();
            role role1 = role.findById(roleid).get();

            roleuser1.setUser(user);
            roleuser1.setRole(role1);


            roleuser.save(roleuser1);


        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/users";
    }

}
