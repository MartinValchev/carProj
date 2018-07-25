package com.java.carProject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Cars {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private long travelledDistance;

    @ManyToMany
    @JoinTable(name="parts_cars",
            joinColumns = {@JoinColumn(name="car_id")},
            inverseJoinColumns = {@JoinColumn(name="part_id")})
    private List<Parts> parts = new ArrayList<>();

    public List<Parts> getParts() {
        return parts;
    }

    public void setParts(List<Parts> parts) {
        this.parts = parts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

}
