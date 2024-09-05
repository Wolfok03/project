package com.example.pro2.Controllers;

import com.example.pro2.Model.brands.brand;
import com.example.pro2.Model.brands.brandA;

import com.example.pro2.services.BrandsRespository;

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
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private BrandsRespository repo;

    @GetMapping({"", "/"})
    public String showBrandList(Model model) {
        List<brand> brands = repo.findAll();
        model.addAttribute("brands",brands);
        return "brands/brandList";
    }

//    @GetMapping("/brandList")
//    public String showBrandList(Model model) {
//        List<brand> brands = repo.findAll();
//        model.addAttribute("brands",brands);
//        return "brands/brandList";
//    }


    @GetMapping("/create")
    public String showCreatePage (Model model) {
        brandA brandA = new brandA();
        model.addAttribute("brandA", brandA);
        return "brands/CreateBrand";
    }

    @PostMapping("/create")
    public String createBrand(
            @Valid @ModelAttribute brandA brandA,
            BindingResult result
    ) {

        if (brandA.getBrand_Image().isEmpty()) {
            result.addError(new FieldError("brandA", "imageFile", "The image file is"));
        }
        if (result.hasErrors()) {
            return "products/CreateProduct";
        }


        // save image file
        MultipartFile image = brandA.getBrand_Image();
        Date createdAt = new Date();
        String storageFileName =  "_" + image.getOriginalFilename();
        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        brand brand = new brand();
        brand.setBrand_Name (brandA.getBrand_Name());
        brand.setBrand_Image (brandA.getBrand_Image().toString());
        brand.setBrand_Description (brandA.getBrand_Description());

        ;
        repo.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        try {
            brand brand = repo.findById(id).get();
            model.addAttribute("brand", brand);
            brandA brandA = new brandA();
            brandA.setBrand_Name(brand.getBrand_Name());
//            brandA.setBrand_Image(brand.getBrand_Image());
            brandA.setBrand_Description(brand.getBrand_Description());
            model.addAttribute("brandA", brandA);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/brands";
        }
        return "brands/EditBrand";
    }



    @PostMapping("/edit")
    public String updateBrand (
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute brandA brandA,
            BindingResult result
    ) {
        try {
            brand brand = repo.findById(id).get();
            model.addAttribute("brand", brand);
            if (result.hasErrors()) {
                return "brands/EditBrand";
            }

            if (!brandA.getBrand_Image().isEmpty()) {
                //Xóa ảnh cũ
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + brand.getBrand_Image());
                try {
                    Files.delete(oldImagePath);
                }
                catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
                //Thêm ảnh mới
                MultipartFile image = brandA.getBrand_Image();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                brand.setBrand_Image(storageFileName);
            }

            brand.setBrand_Name (brandA.getBrand_Name());
            brand.setBrand_Image (brandA.getBrand_Image().toString());
            brand.setBrand_Description (brandA.getBrand_Description());
            repo.save(brand);


        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/brands";
    }

    @GetMapping("/delete")
    public String deleteBrand (
            @RequestParam int id
    ) {
        try {
            brand brand = repo.findById(id).get();

            Path imagePath = Paths.get("public/images/" + brand.getBrand_Image());
            try {
                Files.delete(imagePath);
            }
            catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
            repo.delete (brand);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/brands";
    }
}
