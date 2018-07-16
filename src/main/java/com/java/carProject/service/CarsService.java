package com.java.carProject.service;

import com.java.carProject.entity.Cars;

import java.util.List;

public interface CarsService {
    List<Cars> getCarsByMake(String make);
}
