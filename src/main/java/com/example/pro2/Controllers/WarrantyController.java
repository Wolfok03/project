package com.example.pro2.Controllers;

import com.example.pro2.Model.warranies.warranty;
import com.example.pro2.Model.warranies.warrantyA;
import com.example.pro2.services.WarrantyRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/warranties")
public class WarrantyController {
    @Autowired
    private WarrantyRespository repo;

    @GetMapping({"", "/"})
    public String showWarantyList(Model model) {
        List<warranty> warranties = repo.findAll();
        model.addAttribute("warranties",warranties);
        return "warranties/WarrantyList";
    }

    @GetMapping("/create")
    public String showCreatePage (Model model) {
        warrantyA warrantyA = new warrantyA();
        model.addAttribute("warrantyA", warrantyA);
        return "warranties/CreateWarranty";
    }

    @PostMapping("/create")
    public String createWarranty(
            @Valid @ModelAttribute warrantyA warrantyA

    ) {



        warranty warranty = new warranty();
        warranty.setWarrantyName (warrantyA.getWarrantyName());
        warranty.setWarrantyDate (warrantyA.getWarrantyDate());
        warranty.setWarrantyDescription (warrantyA.getWarrantyDescription());
        warranty.setWarrantyPrice (warrantyA.getWarrantyPrice());


        ;
        repo.save(warranty);
        return "redirect:/warranties";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        try {
            warranty warranty = repo.findById(id).get();
            model.addAttribute("warranty", warranty);
            warrantyA warrantyA = new warrantyA();
            warrantyA.setWarrantyName (warranty.getWarrantyName());
            warrantyA.setWarrantyDate (warranty.getWarrantyDate());
            warrantyA.setWarrantyDescription (warranty.getWarrantyDescription());
            warrantyA.setWarrantyPrice (warranty.getWarrantyPrice());
            model.addAttribute("warrantyA", warrantyA);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/warranties";
        }
        return "warranties/EditWarranty";
    }



    @PostMapping("/edit")
    public String updateWarranty (
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute warrantyA warrantyA,
            BindingResult result
    ) {
        try {
            warranty warranty = repo.findById(id).get();
            model.addAttribute("warranty", warranty);
            if (result.hasErrors()) {
                return "warranties/EditWarranty";
            }



            warranty.setWarrantyName (warrantyA.getWarrantyName());
            warranty.setWarrantyDate (warrantyA.getWarrantyDate());
            warranty.setWarrantyDescription (warrantyA.getWarrantyDescription());
            warranty.setWarrantyPrice (warrantyA.getWarrantyPrice());
            repo.save(warranty);


        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/warranties";
    }

    @GetMapping("/delete")
    public String deleteProduct (
            @RequestParam int id
    ) {
        try {
            warranty warranty = repo.findById(id).get();


            //Xóa product
            repo.delete (warranty);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/warranties";
    }
}

