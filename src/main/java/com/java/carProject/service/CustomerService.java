package com.java.carProject.service;

import com.java.carProject.entity.Customers;

import java.util.List;

public interface CustomerService {
    List<Customers> getCustomersByBirthDateAsc();
    List<Customers> getCustomersByBirthDateDesc();
}
