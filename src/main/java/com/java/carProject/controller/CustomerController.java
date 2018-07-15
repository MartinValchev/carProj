package com.java.carProject.controller;

import com.java.carProject.entity.Customers;
import com.java.carProject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers/all/ascending")
    @ResponseBody
    public String getCustomersByBirthDateAsc(Model model){
       List<Customers> customersListAsc = customerService.getCustomersByBirthDateAsc();
       model.addAttribute("customersListAsc",customersListAsc);
        return "customersAsc";
    }
}
