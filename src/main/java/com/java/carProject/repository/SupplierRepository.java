package com.java.carProject.repository;

import com.java.carProject.entity.Suppliers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Suppliers,Long> {
    @Query("SELECT e FROM Suppliers e where e.isImporter = FALSE")
    List<Suppliers> findLocalSuppliers();

    @Query("SELECT e FROM Suppliers e where e.isImporter=1")
    List<Suppliers> findImporterSuppliers();

    @Query("SELECT e FROM Suppliers e")
    List<Suppliers> getAllSuppliers();

    @Query("SELECT e FROM Suppliers e WHERE e.id=:id")
    Suppliers getSuppliersById(@Param("id") Long id);
}
