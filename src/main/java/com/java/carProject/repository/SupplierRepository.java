package com.java.carProject.repository;

import com.java.carProject.entity.Suppliers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Suppliers,Long> {
    @Query("SELECT e FROM Suppliers e where e.isImporter = FALSE")
    List<Suppliers> findLocalSuppliers();

    @Query("SELECT e FROM Suppliers e where e.isImporter=1")
    List<Suppliers> findImporterSuppliers();
}
