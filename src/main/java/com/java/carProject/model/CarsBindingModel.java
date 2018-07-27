package com.java.carProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarsBindingModel {

    @NotBlank(message="Make should not be blank")
    @Size(min)
    private String make;
    private String model;
    private long travelledDistance;

}
