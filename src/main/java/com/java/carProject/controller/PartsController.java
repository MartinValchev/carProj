package com.java.carProject.controller;

import com.java.carProject.entity.Customers;
import com.java.carProject.entity.Parts;
import com.java.carProject.entity.Suppliers;
import com.java.carProject.repository.PartsRepository;
import com.java.carProject.repository.SupplierRepository;
import com.java.carProject.service.PartsService;
import com.java.carProject.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class PartsController {
    @Autowired
    PartsRepository partsRepository;

    @Autowired
    PartsService partsService;
    @Autowired
    SuppliersService suppliersService;
    @Autowired
    SupplierRepository supplierRepository;

    @PostMapping("/addParts")
    public RedirectView addPart(@ModelAttribute Parts parts){
        String redirectPage ="";
        if(parts.getQuantity() ==0){
            parts.setQuantity(1);
        }
        Parts savedParts =partsRepository.save(parts);
        if(savedParts !=null){
            redirectPage = "/parts/" + savedParts.getId();
        }else{
            // redirect to error page
        }
        return new RedirectView(redirectPage);
    }
    @GetMapping("/addParts")
    public String addParts(Model model){
        List<Suppliers> suppliersList =  suppliersService.getAllSuppliers();
        model.addAttribute("parts", new Parts());
        model.addAttribute("suppliersList",suppliersList);
        return "AddPart";
    }


    @GetMapping("/parts/{id}")
    public String getPartsById(@PathVariable Long id, Model model){
        Parts parts = partsService.getPartsById(id);
             model.addAttribute("parts",parts);
        return "partsById";
    }
    @GetMapping("/deleteParts")
    public String getDeletePart(Model model){
        List<Parts> allParts =  partsService.getAllPartsList();
        model.addAttribute("partsList",allParts);
        return "deleteParts";
    }

}
