package com.java.carProject.service;

import com.java.carProject.entity.Suppliers;

import java.util.List;

public interface SuppliersService {
    List<Suppliers> getLocalSuppliers();
    List<Suppliers> getImporterSuppliers();
}
