package com.java.carProject.service;

import com.java.carProject.entity.Customers;
import com.java.carProject.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customers> getCustomersByBirthDateAsc() {
        List<Customers> customersList =customerRepository.findByBirthDateAsc();
        return customersList;
    }

    @Override
    public List<Customers> getCustomersByBirthDateDesc() {
        List<Customers> customersList = customerRepository.findByBirthDateDesc();
        return customersList;
    }


}
