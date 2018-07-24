package com.java.carProject.repository;

import com.java.carProject.entity.Parts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PartsRepository extends CrudRepository<Parts,Long> {

    @Query("select e FROM Parts e where e.id=:id")
    Parts getPartsById(@Param("id") Long id);
}
