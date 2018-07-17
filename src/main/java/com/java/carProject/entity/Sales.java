package com.java.carProject.entity;

import javax.persistence.*;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double discount;
    @ManyToOne
    @JoinColumn(name="car_id")
    private Cars cars;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customers customers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

}
