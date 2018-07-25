package com.java.carProject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="parts")
public class Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private long quantity;

    @ManyToMany(mappedBy = "parts")
    private List<Cars> cars = new ArrayList<>();

    public List<Cars> getCars() {
        return cars;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }

    @ManyToOne
    @JoinColumn(name ="supplier_id")
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
