package com.java.carProject.repository;

import com.java.carProject.entity.Parts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PartsRepository extends CrudRepository<Parts,Long> {

    @Query("select e FROM Parts e where e.id=:id")
    Parts getPartsById(@Param("id") Long id);

    @Query("SELECT e from Parts e")
    List<Parts> getAllPartsList();

    @Query("SELECT e FROM Parts e where e.id IN :partIds")
    List<Parts> getPartsByListIds(@Param("partIds") Set<Long> partIds);
}
