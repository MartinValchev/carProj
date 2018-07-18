package com.java.carProject.repository;

import com.java.carProject.entity.Cars;
import com.java.carProject.entity.Parts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarsRepository extends CrudRepository<Cars,Long> {
    @Query("SELECT c FROM Cars c where c.make=:make")
    List<Cars> findByMake(@Param("make") String make);

    @Query("SELECT e FROM Cars e WHERE e.id =:id")
    Cars getCarByid(@Param("id") Long id);

}
