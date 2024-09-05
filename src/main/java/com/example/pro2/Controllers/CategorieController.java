package com.example.pro2.Controllers;

import com.example.pro2.Model.categories.categorie;
import com.example.pro2.Model.categories.categorieA;

import com.example.pro2.services.CategoriesRespository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    private CategoriesRespository repo;

    @GetMapping({"", "/"})
    public String showCategorieList(Model model) {
        List<categorie> categories = repo.findAll();
        model.addAttribute("categories",categories);
        return "categories/CategorieList";
    }

    @GetMapping("/create")
    public String showCreatePage (Model model) {
        categorieA categorieA = new categorieA();
        model.addAttribute("categorieA", categorieA);
        return "categories/CreateCategorie";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute categorieA categorieA

    ) {


        categorie categorie = new categorie();
        categorie.setCategorie_name (categorieA.getName());

        repo.save(categorie);
        return "redirect:/categories";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        try {
            categorie categorie = repo.findById(id).get();
            model.addAttribute("categorie", categorie);
            categorieA categorieA = new categorieA();
            categorieA.setName(categorie.getCategorie_name());

            model.addAttribute("categorieA", categorieA);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/categories";
        }
        return "categories/EditCategorie";
    }



    @PostMapping("/edit")
    public String updateProduct (
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute categorieA categorieA,
            BindingResult result
    ) {
        try {
            categorie categorie = repo.findById(id).get();
            model.addAttribute("categorie", categorie);
            if (result.hasErrors()) {
                return "categories/EditCategorie";
            }



            categorie.setCategorie_name (categorieA.getName());

            repo.save(categorie);


        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete")
    public String deleteProduct (
            @RequestParam int id
    ) {
        try {
            categorie categorie = repo.findById(id).get();


            //Xóa product
            repo.delete (categorie);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/categories";
    }
}
