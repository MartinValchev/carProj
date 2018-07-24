package com.java.carProject.controller;

import com.java.carProject.entity.Parts;
import com.java.carProject.repository.PartsRepository;
import com.java.carProject.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;

@Controller
public class PartsController {
    @Autowired
    PartsRepository partsRepository;

    @Autowired
    PartsService partsService;

    @PostMapping("/parts")
    public String addPart(@ModelAttribute Parts parts){
        if(parts.getQuantity() ==0){
            parts.setQuantity(1);
        }
        Parts savedParts =partsRepository.save(parts);
        if(savedParts !=null){
        }else{
            // redirect to error page
        }
        return "";
    }

    @GetMapping("/parts/{id}")
    public String getPartsById(@PathVariable Long id, Model model){
        Parts parts = partsService.getPartsById(id);
             model.addAttribute("parts",parts);
        return "partsById";
    }

}
