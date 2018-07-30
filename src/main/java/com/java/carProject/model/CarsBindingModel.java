package com.java.carProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CarsBindingModel {

    @NotBlank(message="Make should not be blank")
    @Size(min=2, max=20)
    private String make;

    @NotBlank(message="Make should not be blank")
    @Size(min=2, max=20)
    private String model;

    @NotNull
    private Long travelledDistance;

    @Pattern(regexp = "^(\\s?\\d+)+\\s?$",message = "Invalid id input")
    private String partIds;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public String getPartIds() {
        return partIds;
    }

    public void setPartIds(String partIds) {
        this.partIds = partIds;
    }
}
