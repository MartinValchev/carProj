package com.java.carProject.repository;

import com.java.carProject.entity.Customers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customers,Long> {
    @Query("SELECT p FROM Customers p ORDER by p.birthDate asc")
    List<Customers> findByBirthDateAsc();

    @Query("SELECT p FROM Customers p ORDER by p.birthDate desc")
    List<Customers> findByBirthDateDesc();



}
