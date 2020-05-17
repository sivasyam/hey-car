package de.hey_car.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@Setter
@Getter
public class VehicleDTO {
    @CsvBindByName
    @NotNull
    private String code;
    @CsvBindByName(column = "make/model")
    @NotNull
    private String make;
    @NotNull
    private String model;
    @CsvBindByName(column = "power-in-ps")
    private Long kw;
    @NotNull
    @CsvBindByName
    @NotNull
    private Integer year;
    @CsvBindByName
    @NotNull
    private String color;
    @CsvBindByName
    @NotNull
    private Double price;
}