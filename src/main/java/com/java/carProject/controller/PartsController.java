package com.java.carProject.controller;

import com.java.carProject.entity.Customers;
import com.java.carProject.entity.Parts;
import com.java.carProject.entity.Suppliers;
import com.java.carProject.model.PartsBindingModel;
import com.java.carProject.repository.PartsRepository;
import com.java.carProject.repository.SupplierRepository;
import com.java.carProject.service.PartsService;
import com.java.carProject.service.SuppliersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
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
    public String addPart(@Valid @ModelAttribute PartsBindingModel partsBindingModel, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            List<Suppliers> suppliersList =  suppliersService.getAllSuppliers();
            model.addAttribute("suppliersList",suppliersList);
            return "AddPart";

        }else {
            ModelMapper modelMapper= new ModelMapper();
            Parts parts =  modelMapper.map(partsBindingModel, Parts.class);
            if (parts.getQuantity() == 0) {
                parts.setQuantity(1);
            }
            Parts savedParts = partsRepository.save(parts);
            if (savedParts != null) {
                return "redirect:/parts/" + savedParts.getId();

            }
            return "";
        }
    }
    @GetMapping("/addParts")
    public String addParts(PartsBindingModel partsBindingModel, Model model){
        List<Suppliers> suppliersList =  suppliersService.getAllSuppliers();
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
        List<String> strArr = new ArrayList<>();
        model.addAttribute("partIds", strArr);
        model.addAttribute("partsList",allParts);
        return "deleteParts";
    }
    @PostMapping("/deleteParts")
    public RedirectView deleteParts(@RequestParam("partIds")  List<String>  partIds){
        partsService.deleteParts(partIds);

        return new RedirectView("/deleteParts");
    }
    @GetMapping("/editParts/{id}")
    public String getEditPart(@PathVariable("id")Long id, Model model){
        Parts parts =  partsRepository.getPartsById(id);
        model.addAttribute("parts",parts);
        return "editParts";
    }
    @PostMapping("/editParts")
    public RedirectView editParts(@ModelAttribute("parts") Parts parts){
        partsRepository.save(parts);
        return new RedirectView("/parts/" +parts.getId() );
    }

}
