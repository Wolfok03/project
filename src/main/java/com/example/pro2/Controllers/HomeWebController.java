package com.example.pro2.Controllers;


import com.example.pro2.models.ProductDto;
import com.example.pro2.models.product;
import com.example.pro2.services.ProductsRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/website")
public class HomeWebController {
    @Autowired
    private ProductsRespository repo;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products",products);
        return "website/home";
    }



    @GetMapping("/giohang")
    public String showEditPage(
            Model model,
            @RequestParam int id

    ) {
        List<product> products = repo.findAll();
        model.addAttribute("products",products);
        try {
            product product = repo.findById(id).get();
            model.addAttribute("product", product);
            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            model.addAttribute("productDto", productDto);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        return "website/giohang";
    }



    @PostMapping("/giohang")
    public String updateProduct (
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ) {
        try {
            product product = repo.findById(id).get();
            model.addAttribute("product", product);
            if (result.hasErrors()) {
                return "products/EditProduct";
            }

            if (!productDto.getImageFile().isEmpty()) {
                //Xóa ảnh cũ
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
                try {
                    Files.delete(oldImagePath);
                }
                catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
                //Thêm ảnh mới
                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }

            product.setName (productDto.getName());
            product.setBrand (productDto.getBrand());
            product.setCategory (productDto.getCategory());
            product.setPrice (productDto.getPrice());
            product.setDescription (productDto.getDescription());
            repo.save(product);


        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }
}
