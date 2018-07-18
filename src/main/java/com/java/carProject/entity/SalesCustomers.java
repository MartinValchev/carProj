package com.java.carProject.entity;

public class SalesCustomers {
    Cars car;
    Customers customer;
    double priceOfSale;
    double priceOfSaleDiscount;
    double discount;
    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public double getPriceOfSale() {
        return priceOfSale;
    }

    public void setPriceOfSale(double priceOfSale) {
        this.priceOfSale = priceOfSale;
    }

    public double getPriceOfSaleDiscount() {
        return priceOfSaleDiscount;
    }

    public void setPriceOfSaleDiscount(double priceOfSaleDiscount) {
        this.priceOfSaleDiscount = priceOfSaleDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
