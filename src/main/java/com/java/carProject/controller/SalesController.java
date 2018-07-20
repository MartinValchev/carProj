package com.java.carProject.controller;

import com.java.carProject.entity.Sales;
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
        List<Sales> salesList = salesService.getSalesById(id);
        List<SalesCustomers> salesCustomersList = salesService.generateSalesCustomersList(salesList);
        model.addAttribute("salesCustomersList",salesCustomersList);
        return "salesById";
    }

    @GetMapping("/Sales")
    public String getSalesList(Model model){
        List<Sales> salesList = salesService.getAllSalesList();
        List<SalesCustomers> salesCustomersList = salesService.generateSalesCustomersList(salesList);
        model.addAttribute("salesCustomersList",salesCustomersList);
        return "allSales";
    }
    @GetMapping("/Sales/discounted")
    public String getDiscountedSalesList(Model model){
        List<Sales> salesList = salesService.getAllDiscountedSales();
        List<SalesCustomers> discountedSalesCustomersList = salesService.generateSalesCustomersList(salesList);
        model.addAttribute("discountedSalesCustomersList",discountedSalesCustomersList);
        return "allDiscountedSales";
    }

    @GetMapping("/Sales/discounted/{percent}")
    public String getFixDiscountedSalesList(@PathVariable double percent, Model model){
        double inputPercent = percent/100;
        List<Sales> salesList = salesService.getAllFixedDiscountedSales(inputPercent);
        List<SalesCustomers> discountedSalesCustomersList = salesService.generateSalesCustomersList(salesList);
        model.addAttribute("discountedSalesCustomersList",discountedSalesCustomersList);
        return "discountedSalesPercent";
    }

}
