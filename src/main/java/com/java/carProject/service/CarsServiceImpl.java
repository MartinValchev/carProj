package com.java.carProject.service;

import com.java.carProject.entity.Cars;
import com.java.carProject.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    CarsRepository carsRepository;
    @Override
    public List<Cars> getCarsByMake(String make) {
        List<Cars> carByMakeList = carsRepository.findByMake(make);
        return carByMakeList;
    }
}
