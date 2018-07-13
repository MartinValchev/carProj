package com.java.carProject.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
@NamedQuery(name = "Customers.findByBirthDateAsc", query = "SELECT p FROM Customers p ORDER by p.birthDate asc")

public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date birthDate;
    private boolean isYoundDriver;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoundDriver() {
        return isYoundDriver;
    }

    public void setYoundDriver(boolean youndDriver) {
        isYoundDriver = youndDriver;
    }



}
