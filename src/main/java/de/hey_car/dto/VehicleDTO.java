package de.hey_car.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class VehicleDTO {
    @NotEmpty
    private String code;
    @NotEmpty
    private String make;
    private String model;
    @NotEmpty
    private Long kw;
    @NotEmpty
    private Integer year;
    @NotEmpty
    private String color;
    @NotEmpty
    private Double price;
}
