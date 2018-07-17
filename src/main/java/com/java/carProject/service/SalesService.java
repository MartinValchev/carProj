package com.java.carProject.service;

public interface SalesService {
    String getCustomerName(Long customerId);
    int getBoughtCarsCount(Long customerId);
    double getTotalSpendMoneyOnCarsDiscount(Long customerId);

}
