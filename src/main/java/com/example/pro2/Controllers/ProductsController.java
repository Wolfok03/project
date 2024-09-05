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
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsRespository repo;
    @Autowired
    private CategoriesRespository ca;
    @Autowired
    private BrandsRespository ba;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);



        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage (Model model) {
        List<brand> brands = ba.findAll();
        List<categorie> categories = ca.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
//        categorie categorie = new categorie();
//        model.addAttribute("categorie", categorie);


//        brand brand = new brand();
//        model.addAttribute("brand", brand);

        ProductA ProductA = new ProductA();
        model.addAttribute("ProductA", ProductA);
        return "products/CreateProduct";
    }

    public brand findBybrandId(int id) {
        List<brand> brands = ba.findAll();

        for (brand brand : brands) {
            if (brand.getBrand_id() == id) {
                return brand; // Trả về thương hiệu nếu tìm thấy
            }
        }
        return null; // Không tìm thấy thương hiệu nào
    }

//    public brand findBybrandName(band name) {
//        List<brand> brands = ba.findAll();
//
//        for (brand brand : brands) {
//            if (brand.getBrand_Name() == name) {
//                return brand; // Trả về thương hiệu nếu tìm thấy
//            }
//        }
//        return null; // Không tìm thấy thương hiệu nào
//    }

    public categorie findBycategorieId(int id) {
        List<categorie> categories = ca.findAll();

        for (categorie categorie : categories) {
            if (categorie.getCategorie_id() == id) {
                return categorie; // Trả về thương hiệu nếu tìm thấy
            }
        }
        return null; // Không tìm thấy thương hiệu nào
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute ProductA ProductA,
            BindingResult result
    ) {
        if (ProductA.getImageFile().isEmpty()) {
            result.addError(new FieldError("ProductA", "imageFile", "The image file is"));
        }
        if (result.hasErrors()) {
            return "products/CreateProduct";
        }


        // luu anh
        MultipartFile image = ProductA .getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
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


        Product product = new Product();
        int brandId = ProductA.getBrand_id();
        brand brand = findBybrandId(brandId);
        product.setBrand(brand);

        int categorieId = ProductA.getBrand_id();
        categorie categorie = findBycategorieId(categorieId);
        product.setCategorie(categorie);

        product.setProductName (ProductA.getProductName());
        product.setProductPrice (ProductA.getProductPrice());
        product.setProductDescription (ProductA.getProductDescription());
        product.setProductQuantity(ProductA.getProductQuantity());
        product.setCreatedAt (createdAt);
        product.setImageFileName (storageFileName);
        ;
        repo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id

    ) {

        List<brand> brands = ba.findAll();
        List<categorie> categories = ca.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
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
        return "products/EditProduct";
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

    //            Product product = new Product();
                int brandId = ProductA.getBrand_id();
                brand brand = findBybrandId(brandId);
                product.setBrand(brand);

                int categorieId = ProductA.getBrand_id();
                categorie categorie = findBycategorieId(categorieId);
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
            return "redirect:/products";
        }

    @GetMapping("/delete")
    public String deleteProduct (
            @RequestParam int id
    ) {
        try {
            Product product = repo.findById(id).get();

            Path imagePath = Paths.get("public/images/" + product.getImageFileName());
            try {
                Files.delete(imagePath);
            }
            catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
            //Xóa product
            repo.delete (product);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }
}
