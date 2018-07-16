package com.java.carProject.controller;

import com.java.carProject.entity.Suppliers;
import com.java.carProject.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SuppliersController {

    @Autowired
    SuppliersService suppliersService;

    @GetMapping("/suppliers/local")
    public String getLocalSuppliers(Model model){
        List<Suppliers> localSuppliersList = suppliersService.getLocalSuppliers();
        model.addAttribute("localSuppliersList",localSuppliersList);
        return "localSuppliers";
    }

    @GetMapping("/suppliers/importers")
    public String getImporterSuppliers(Model model){
        List<Suppliers> importerSuppliersList = suppliersService.getImporterSuppliers();
        model.addAttribute("importerSuppliersList",importerSuppliersList);
        return "importerSuppliers";
    }
}
