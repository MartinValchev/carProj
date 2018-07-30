package com.java.carProject.controller;

import com.java.carProject.entity.Cars;
import com.java.carProject.entity.Parts;
import com.java.carProject.model.CarsBindingModel;
import com.java.carProject.repository.CarsRepository;
import com.java.carProject.service.CarsService;
import com.java.carProject.service.PartsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CarsController {

    @Autowired
    CarsService carsService;
    @Autowired
    CarsRepository carsRepository;

    @Autowired
    PartsService partsService;


    @GetMapping("/cars/{make}")
    public String getCarsByMake(@PathVariable String make, Model model){
        List<Cars> carsByMakeList = carsService.getCarsByMake(make);
        model.addAttribute("carsByMakeList",carsByMakeList);
        return "carsByMake";
    }
    @GetMapping("/cars/id/{id}")
    public String getCarById(@PathVariable Long id, Model model){
        Cars cars = carsService.getCarById(id);
        model.addAttribute("cars",cars);
        return "carById";
    }

    @GetMapping("/cars/{id}/parts")
    public String getPartsByCarId(@PathVariable Long id, Model model){
       Cars car = carsService.getCarById(id);
       List<Parts> carPartsList = car.getParts().stream().collect(Collectors.toList());
       model.addAttribute("car",car);
       model.addAttribute("carPartsList",carPartsList);
        return "parts";
    }
    @GetMapping("/cars/all")
    public String getAllCarts(Model model){
        List<Cars> allCarsList = carsService.getAllCars();
        model.addAttribute("allCarsList",allCarsList);
        return "allCars";
    }

    @GetMapping("/addCars")
    public String getAddCars(@ModelAttribute CarsBindingModel carsBindingModel){
        return "AddCars";
    }
    @PostMapping("/addCars")
    public String addCars(@Valid @ModelAttribute CarsBindingModel carsBindingModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "AddCars";
        }else {
            ModelMapper modelMapper = new ModelMapper();
            Cars cars = modelMapper.map(carsBindingModel, Cars.class);
            String partIds = cars.getPartIds();
            List<Parts> parts = partsService.generateParts(partIds);
            cars.setParts(parts);
            Cars savedCars = carsRepository.save(cars);
            return "redirect:/cars/id/" + savedCars.getId();
        }

    }



}
