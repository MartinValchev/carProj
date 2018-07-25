package com.java.carProject.service;

import com.java.carProject.entity.Parts;

import java.util.List;

public interface PartsService {
    Parts getPartsById(Long id);

    List<Parts> getAllPartsList();
}
