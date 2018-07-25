package com.java.carProject.service;

import com.java.carProject.entity.Suppliers;
import com.java.carProject.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuppliersServiceImpl implements SuppliersService {

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public List<Suppliers> getLocalSuppliers() {
        List<Suppliers>  localSuppliersList =supplierRepository.findLocalSuppliers();
        return localSuppliersList;
    }

    @Override
    public List<Suppliers> getImporterSuppliers() {
        List<Suppliers> importerSuppliersList = supplierRepository.findImporterSuppliers();
        return importerSuppliersList;
    }

    @Override
    public List<Suppliers> getAllSuppliers() {
        return supplierRepository.getAllSuppliers();
    }
}
