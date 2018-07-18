package com.java.carProject.controller;

import com.java.carProject.entity.SalesCustomers;
import com.java.carProject.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SalesController {
    @Autowired
    SalesService salesService;

    @GetMapping("/customers/{id}")
    public String getSalesByCustomerId(@PathVariable Long id, Model model){
        String customerName = salesService.getCustomerName(id);
        int boughtCarsCount = salesService.getBoughtCarsCount(id);
        double moneySpent = salesService.getTotalSpendMoneyOnCarsDiscount(id,true);
        model.addAttribute("customerName",customerName);
        model.addAttribute("boughtCarsCount",boughtCarsCount);
        model.addAttribute("moneySpent",moneySpent);
        return "sales";
    }
    @GetMapping("/Sales/{id}")
    public String getAllSalesById(@PathVariable Long id,Model model){
        List<SalesCustomers> salesCustomersList = salesService.generateSalesCustomersList(id);
        model.addAttribute("salesCustomersList",salesCustomersList);
        return "salesById";
    }

    @GetMapping("/Sales")
    public String getSalesList(Model model){
        List<SalesCustomers> salesCustomersList = salesService.generateSalesCustomersList(null);
        model.addAttribute("salesCustomersList",salesCustomersList);
        return "allSales";
    }

}
