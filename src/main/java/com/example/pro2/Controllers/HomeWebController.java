package com.example.pro2.Controllers;


import com.example.pro2.Model.Product.Product;
import com.example.pro2.Model.Product.ProductA;
import com.example.pro2.Model.brands.brand;
import com.example.pro2.Model.categories.categorie;
import com.example.pro2.services.BrandsRespository;
import com.example.pro2.services.CategoriesRespository;
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
    @Autowired
    private CategoriesRespository ca;
    @Autowired
    private BrandsRespository ba;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products",products);
        return "website/home";
    }



    @GetMapping("/giohang")
    public String showEditPage(
            Model model,
            @RequestParam int id

    ) {

        List<brand> brands = ba.findAll();
        model.addAttribute("brands", brands);
        List<categorie> categories = ca.findAll();
        model.addAttribute("categories", categories);
        try {
            Product product = repo.findById(id).get();
            brand A = new brand();
            categorie B = new categorie();
            B = product.getCategorie();
            A = product.getBrand();
            model.addAttribute("product", product);
            ProductA productA = new ProductA();
            productA.setProductName(product.getProductName());
            productA.setBrand_id(A.getBrand_id());
            productA.setCategorie_id(B.getCategorie_id());
            productA.setProductPrice(product.getProductPrice());
            productA.setProductDescription(product.getProductDescription());
            productA.setProductQuantity(product.getProductQuantity());
            productA.setProductImage(product.getImageFileName());
            model.addAttribute("productA", productA);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        return "website/giohang";
    }


    @PostMapping("/edit")
    public String updateProduct (
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductA ProductA,
            BindingResult result
    ) {
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);
            if (result.hasErrors()) {
                return "products/EditProduct";
            }

            if (!ProductA.getImageFile().isEmpty()) {
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
                MultipartFile image = ProductA.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }
            ProductsController PC = new ProductsController();

//            Product product = new Product();
            int brandId = ProductA.getBrand_id();
            brand brand = PC.findBybrandId(brandId);
            product.setBrand(brand);

            int categorieId = ProductA.getBrand_id();
            categorie categorie = PC.findBycategorieId(categorieId);
            product.setCategorie(categorie);

            product.setProductName (ProductA.getProductName());
            product.setProductPrice (ProductA.getProductPrice());
            product.setProductDescription (ProductA.getProductDescription());
            product.setProductQuantity(ProductA.getProductQuantity());
//            product.setImageFileName (storageFileName.toString());
            repo.save(product);


        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/website";
    }
}
