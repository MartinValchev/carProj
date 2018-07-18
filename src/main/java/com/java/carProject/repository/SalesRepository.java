package com.java.carProject.repository;

import com.java.carProject.entity.Sales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SalesRepository extends CrudRepository<Sales,Long>{
    @Query("SELECT e from Sales e where e.customers.id=:customerId")
    List<Sales> getSalesByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT COUNT(e.cars) from Sales e where e.customers.id=:customerId")
    Integer getBoughtCarsCountByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT e from Sales e")
    List<Sales> getAllSales();

    @Query("SELECT e from Sales e where e.id=:salesId")
    List<Sales> getSalesById(@Param("salesId") Long salesId);

}
