package com.java.carProject.service;

import com.java.carProject.entity.*;
import com.java.carProject.repository.CarsRepository;
import com.java.carProject.repository.CustomerRepository;
import com.java.carProject.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    CarsRepository carsRepository;

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
    public double getTotalSpendMoneyOnCarsDiscount(Long customerId, boolean isDiscountApplied) {
        double totalSpendMoney=0;
        List<Sales> salesList = salesRepository.getSalesByCustomerId(customerId);
       if(salesList !=null) {
           List<Double> spendOnCarMoney = new ArrayList<>();
           double currentSpentMoney = 0;
           double multiplier =0;
           double currentSpentMoneyDiscount =0;
           double discount =0;
           for (Sales sales : salesList) {
              Cars car = sales.getCars();
                   List<Parts> carPartsList = car.getParts();
                   for (Parts part : carPartsList) {
                       currentSpentMoney += part.getPrice();
                   }
                   if(isDiscountApplied){
                       discount =sales.getDiscount();
                   }
                   multiplier = 1.0 - discount;
                   currentSpentMoneyDiscount = currentSpentMoney*multiplier;
                   spendOnCarMoney.add(currentSpentMoneyDiscount);
           }
           for (Double money:spendOnCarMoney){
               totalSpendMoney +=money;
           }
       }

        return totalSpendMoney;
    }


    private double getCarPrice(Cars car) {
        double carPrice =0;
        if(car !=null && car.getParts() !=null) {
            List<Parts> partsList = car.getParts();
            for(Parts part: partsList){
                carPrice += part.getPrice();
            }
        }

        return carPrice;
    }

    @Override
    public List<SalesCustomers> generateSalesCustomersList(List<Sales> salesList) {
        List<SalesCustomers> resultList = new ArrayList<>();
        if(salesList !=null) {
            for (Sales sales : salesList) {
                SalesCustomers salesCustomers = new SalesCustomers();
                salesCustomers.setCar(sales.getCars());
                double discount = sales.getDiscount();
                double multiplier = 1 - discount;
                salesCustomers.setDiscount(discount*100);
                salesCustomers.setCustomer(sales.getCustomers());
                double carPrice = getCarPrice(sales.getCars());
                salesCustomers.setPriceOfSale(carPrice);
                salesCustomers.setPriceOfSaleDiscount(carPrice * multiplier);
                resultList.add(salesCustomers);
            }
        }
        return resultList;
    }

    @Override
    public List<Sales> getAllSalesList() {
        return salesRepository.getAllSales();
    }

    @Override
    public List<Sales> getSalesById(Long salesId) {
        return salesRepository.getSalesById(salesId);
    }

    @Override
    public List<Sales> getAllDiscountedSales() {
        return salesRepository.getDiscountedSales();
    }

    @Override
    public List<Sales> getAllFixedDiscountedSales(double percent) {
        return salesRepository.getDiscountedSalesPercent(percent);
    }


    public Map<Customers, List<Double>> generateCustomerPriceMap(List<Sales> salesList) {
        Map<Customers,List<Double>> resultMap = new HashMap<>();
        List<Double> spendOnCarMoney = new ArrayList<>();
        double currentSpentMoney = 0;
        double multiplier =0;
        double currentSpentMoneyDiscount =0;
        double discount =0;
        if(salesList !=null){
            Customers prevCustomer =null;
            Customers currentCustomer =null;
            for(Sales sales: salesList){
                Customers customer = sales.getCustomers();
                if(prevCustomer ==null){
                    prevCustomer =customer;
                }
                currentCustomer = customer;
                if(currentCustomer.getId() == prevCustomer.getId()){
                    Cars car = sales.getCars();
                    List<Parts> carPartsList = car.getParts();
                    for (Parts part : carPartsList) {
                        currentSpentMoney += part.getPrice();
                    }
                    discount =sales.getDiscount();
                    multiplier = 1.0 - discount;
                    currentSpentMoneyDiscount += currentSpentMoney*multiplier;
                }else{
                    // sales records for particular customer is changed
                    List<Double> priceList = new ArrayList<>();
                    priceList.add(currentSpentMoneyDiscount);
                    priceList.add(currentSpentMoney);
                    resultMap.put(prevCustomer,priceList);
                    prevCustomer= currentCustomer;
                    currentSpentMoney =0;
                    currentSpentMoneyDiscount=0;

                }




            }
        }
        return resultMap;
    }


}
