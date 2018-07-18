package com.java.carProject.service;

import com.java.carProject.entity.Cars;
import com.java.carProject.entity.Customers;
import com.java.carProject.entity.Sales;
import com.java.carProject.entity.SalesCustomers;

import java.util.List;
import java.util.Map;

public interface SalesService {
    String getCustomerName(Long customerId);
    int getBoughtCarsCount(Long customerId);
    double getTotalSpendMoneyOnCarsDiscount(Long customerId, boolean isDiscountApplied);
    List<SalesCustomers> generateSalesCustomersList(Long salesId);


}
