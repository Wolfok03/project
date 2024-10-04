package com.example.pro2.Controllers;

import com.example.pro2.Model.categories.categorieA;
import com.example.pro2.Model.users.*;
import com.example.pro2.Model.users.userA;
import com.example.pro2.Model.cart.cart;
import com.example.pro2.Model.cart.cartA;
import com.example.pro2.Model.bills.*;

import com.example.pro2.Model.Product.Product;
import com.example.pro2.Model.Product.ProductA;
import com.example.pro2.Model.brands.brand;
import com.example.pro2.Model.categories.categorie;
import com.example.pro2.services.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
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
    @Autowired
    private UserRespository us;
    @Autowired
    private CartsRespository cartsRespository;
    @Autowired
    private BillsRespository billsRespository;
    @Autowired
    private RoleUserRespository roleuser;
    String username;

    @GetMapping({"", "/"})
    public String showProductList(Model model, HttpSession session) {
        user user = (user) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);

        }

        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "website/home";
    }

    @GetMapping("/homenotlogin")
    public String showHomeNottLogin(Model model, HttpSession session) {
        user user = (user) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);

        }

        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "website/homenotlogin";
    }

    @GetMapping("/manage")
    public String showProductManage(Model model) {
        return "website/manage";
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

    //thong tin nguoi dung
    @GetMapping("/edit")
    public String showUser(
            Model model,
            HttpSession session
    ) {
        try {
            user user = (user) session.getAttribute("user");

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
        return "website/thongtintaikhoan";
    }


    @PostMapping("/edit")
    public String updateUser(
            Model model,
            @Valid @ModelAttribute userA userA,
            HttpSession session


    ) {
        try {
            user user = (user) session.getAttribute("user");

            user.setTen(userA.getTen());
            user.setDiaChi(userA.getDiaChi());
            user.setEmail(userA.getEmail());
            user.setGioiTinh(userA.getGioiTinh());
            user.setSdt(userA.getSdt());
            user.setPassword(userA.getPassword());
            user.setUsername(userA.getUsername());
            user.setNgaySinh(userA.getNgaySinh());

            us.save(user);


        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/users";
    }

    //ddang ky
    @GetMapping("/dangki")
    public String showCreatePage(Model model) {

        userA userA = new userA();
        model.addAttribute("userA", userA);
        return "website/dangki";
    }


    @PostMapping("/dangki")
    public String createUser(
            @Valid @ModelAttribute userA userA

    ) {


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

        us.save(user);
        return "redirect:/website/dangnhap";
    }

    //check đăng nhâp
    @GetMapping("/dangnhap")
    public String checkLogin(Model model) {
        userA userA1 = new userA();
        model.addAttribute("userA", userA1);
        return "website/dangnhap";
    }


    @PostMapping("/dangnhap")
    public String checkLogin(
            @Valid @ModelAttribute userA userA,
            Model model,
            HttpSession session

    ) {

        String pass = toSHA256(userA.getPassword());
        user user = us.findByUsernameAndPassword(userA.getUsername(), pass);


        if (user != null) {
            session.setAttribute("user", user);
            role_user roleUser = roleuser.findByUser(user);
            if (roleUser == null) {
                return "redirect:/website";
            } else if (roleUser.getRole().getRole_id() == 1) {
                return "redirect:/website/manage";
            }


        } else {
            model.addAttribute("error", "error-message");
            return "redirect:/website/dangnhap";
        }
        return "redirect:/website";
    }


    Double tt = 0.0;

    //run list item cart
    @GetMapping("/listcart")
    public String showCartList(Model model, HttpSession session) {
        user user = (user) session.getAttribute("user");
        List<cart> carts = cartsRespository.findAllByUserId(user.getId());
        cartA cartA = new cartA();
        billA billA = new billA();
        model.addAttribute("billA", billA);

        if (carts == null) {
            tt = 0.0;

        } else {

            for (cart cart : carts) {
                tt += cart.getCart_sum();
            }
        }

        cartA.setTong(tt);
        model.addAttribute("cartA", cartA);
        model.addAttribute("carts", carts);

        return "website/cart";
    }

//    @GetMapping("/bill")
//    public String showBill(Model model, HttpSession session) {
//        user user = (user) session.getAttribute("user");
//
//
//        return "website/cart";
//
//    }

//    @GetMapping("/bill")
//    public String checkBill(
//            Model model,
//            HttpSession session
//    ) {
//        user user = (user) session.getAttribute("user");
//        billA billA = new billA();
//        model.addAttribute("billA", billA);
//        return "website/cart";
//    }

    @PostMapping("/bill")
    public String thanhToan(@RequestParam("totalPrice") double totalPrice,
                            @RequestParam("address") String address,
                            @RequestParam("note") String note,
                            @RequestParam("phuongthucthanhtoan") String phuongthucthanhtoan,
                            HttpSession session,
                            Model model) {


        bill bill = new bill();
        Date createdAt = new Date();
        user user = (user) session.getAttribute("user");
        bill.setCreateAt(createdAt);
        bill.setStatus("Đang xử lí");
        bill.setTotalPayment(totalPrice);
        bill.setUser(user);
        bill.setNote(note);
        bill.setPhuongthucthanhtoan(phuongthucthanhtoan);
        bill.setAddress(address);
        billsRespository.save(bill);
        model.addAttribute("message", "Thanh toán thành công!");
//        return "success"; // Trả về view thành công
        return "redirect:/website/listcart";
    }

//    @PostMapping("/bill")
//    public String bill(
//            @Valid @ModelAttribute billA billA,
//            HttpSession session
//
//
//    ) {
//        bill bill = new bill();
//        Date createdAt = new Date();
//        user user = (user) session.getAttribute("user");
//        bill.setCreateAt(createdAt);
//        bill.setStatus("Đang xử lí");
////        bill.setPhuongthucthanhtoan(billA.getPhuongthucthanhtoan());
////        bill.setNote(billA.getNote());
////        bill.setAddress(billA.getAddress());
//        bill.setTotalPayment(1000.0);
//        bill.setUser(user);
//
//        bill.setPhuongthucthanhtoan("111");
//        bill.setNote("fdjbksb");
//        bill.setAddress("fdbsjhbd");
//        billsRespository.save(bill);
//        return "redirect:/website/listcart";
//    }

    // thêm gio hang
    @PostMapping("/addcart")
    public String addCart(
            @Valid @ModelAttribute cartA cartA,
            HttpSession session,
            @RequestParam int id,
            RedirectAttributes redirectAttributes

    ) {
        user user = (user) session.getAttribute("user");
        Product product = repo.findById(id).get();

        if (!cartsRespository.existsByuserIdAndProductId(user.getId(), product.getId())) {
            cart cart = new cart();
            cart.setCart_quantity(1);
            cart.setUser(user);
            cart.setProduct(product);
            double sum = product.getProductPrice();
            cart.setCart_sum(sum);
            cartsRespository.save(cart);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm sản phẩm vào giỏ hàng thành công!");
        } else {

        }

        return "redirect:/website";
    }

//    // Luu thanh doi
//    @PostMapping("/edit")
//    public String editCart(
//            @Valid @ModelAttribute cartA cartA,
//            HttpSession session,
//            @RequestParam int id,
//            RedirectAttributes redirectAttributes
//
//    ) {
//
//
//            cart cart = cartsRespository.findById(id).get();
//            cart.setCart_quantity(cartA.getCart_quantity());
//
//            double sum = cart.getProduct().getProductPrice()*cartA.getCart_quantity();
//            cart.setCart_sum(sum);
//            cartsRespository.save(cart);
////            redirectAttributes.addFlashAttribute("successMessage", "Thêm sản phẩm vào giỏ hàng thành công!");
//
//
//        return "redirect:/website/listcart";
//    }

    // Xóa sản pham trong gio hang
    @GetMapping("/delete")
    public String deleteItemCart(
            @RequestParam int id
    ) {
        try {
            cart cart = cartsRespository.findById(id).get();


            //Xóa product
            cartsRespository.delete(cart);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/website/listcart";
    }


    @GetMapping("/chitietsanpham")
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
            return "redirect:/website";
        }
        return "chitietsanpham";
    }


    @PostMapping("/chitietsanpham")
    public String updateProduct(
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
                } catch (Exception ex) {
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

            product.setProductName(ProductA.getProductName());
            product.setProductPrice(ProductA.getProductPrice());
            product.setProductDescription(ProductA.getProductDescription());
            product.setProductQuantity(ProductA.getProductQuantity());
//            product.setImageFileName (storageFileName.toString());
            repo.save(product);


        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/website";
    }

    // bill

}
