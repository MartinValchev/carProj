package com.java.carProject.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class CustomerBindingModel {

    @NotNull(message = "Customer name cannot be blank")
    @NotBlank(message = "Customer name cannot be blank")
    @Pattern(regexp = "(\\w+\\s?)+",message = "Invalid customer name")
    private String name;

    @NotNull(message = "Customer birthDate cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
