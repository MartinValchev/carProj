package com.java.carProject.controller;

import com.java.carProject.entity.Cars;
import com.java.carProject.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CarsController {

    @Autowired
    CarsService carsService;

    @GetMapping("/cars/{make}")
    public String getCarsByMake(@PathVariable String make, Model model){
        List<Cars> carsByMakeList = carsService.getCarsByMake(make);
        model.addAttribute("carsByMakeList",carsByMakeList);
        return "carsByMake";
    }
}
