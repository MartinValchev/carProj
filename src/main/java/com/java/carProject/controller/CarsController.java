package com.java.carProject.controller;

import com.java.carProject.entity.Cars;
import com.java.carProject.entity.Parts;
import com.java.carProject.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/cars/{id}/parts")
    public String getPartsByCarId(@PathVariable Long id, Model model){
       Cars car = carsService.getCarById(id);
       List<Parts> carPartsList = car.getParts().stream().collect(Collectors.toList());
       model.addAttribute("car",car);
       model.addAttribute("carPartsList",carPartsList);
        return "parts";
    }
}
