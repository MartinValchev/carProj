package com.java.carProject.model;

import com.java.carProject.entity.Suppliers;

import javax.validation.constraints.*;

public class PartsBindingModel {

    private Long id;

    @NotBlank(message="Name should not be blank")
    @Size(min = 2, max = 20)
    private String name;

    @Min(value=1,message="Price must be grater or equal to 1")
    private double price;

    @Min(value=1,message="Quantity cannot be negative or null")
    private long quantity;

    @NotNull(message="Supplier should not be null")
    private Suppliers suppliers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }
}
