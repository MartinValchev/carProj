package com.java.carProject.service;

import com.java.carProject.entity.Cars;
import com.java.carProject.entity.Customers;
import com.java.carProject.entity.Parts;
import com.java.carProject.entity.Sales;
import com.java.carProject.repository.CustomerRepository;
import com.java.carProject.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String getCustomerName(Long customerId) {
        String customerName ="";
        Customers customer = customerRepository.getCustomerById(customerId);
        if(customer !=null){
            customerName =customer.getName();
        }
        return customerName;
    }

    @Override
    public int getBoughtCarsCount(Long customerId) {
        return salesRepository.getBoughtCarsCountByCustomerId(customerId);
    }

    @Override
    public double getTotalSpendMoneyOnCarsDiscount(Long customerId) {
        double totalSpendMoney=0;
        List<Sales> salesList = salesRepository.getSalesByCustomerId(customerId);
       if(salesList !=null) {
           List<Double> spendOnCarMoney = new ArrayList<>();
           double currentSpentMoney = 0;
           double multiplier =0;
           double currentSpentMoneyDiscount =0;
           for (Sales sales : salesList) {
              Cars car = sales.getCars();
                   List<Parts> carPartsList = car.getParts();
                   for (Parts part : carPartsList) {
                       currentSpentMoney += part.getPrice();
                   }
                   multiplier = 1.0 - sales.getDiscount();
                   currentSpentMoneyDiscount = currentSpentMoney*multiplier;
                   spendOnCarMoney.add(currentSpentMoneyDiscount);
           }
           for (Double money:spendOnCarMoney){
               totalSpendMoney +=money;
           }
       }

        return totalSpendMoney;
    }
}
