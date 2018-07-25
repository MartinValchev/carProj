package com.java.carProject.repository;

import com.java.carProject.entity.Parts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartsRepository extends CrudRepository<Parts,Long> {

    @Query("select e FROM Parts e where e.id=:id")
    Parts getPartsById(@Param("id") Long id);

    @Query("SELECT e from Parts e")
    List<Parts> getAllPartsList();

    @Query("DELETE e FROM Parts e where e.id IN :partIds")
    void deleteParts(@Param("partIds") List<Long> partIds);
}
