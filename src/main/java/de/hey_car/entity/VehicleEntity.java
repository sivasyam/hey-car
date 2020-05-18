package de.hey_car.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@Table(name = "vehicle", uniqueConstraints={@UniqueConstraint(columnNames = {"code"})})
@AllArgsConstructor
@NoArgsConstructor
@QueryEntity
public class VehicleEntity {
    @Id
    @Column(name="code", updatable = true)
    private String code;
    @Column(name="make", length = 500)
    private String make;
    @Column(name="model", length = 500)
    private String model;
    @Column(name="kw")
    private Long kw;
    @Column(name="year", length = 4)
    private Integer year;
    @Column(name="color", length = 25)
    private String color;
    @Column(name="price", length = 10)
    private Double price;
    @Column(name="dealer_id")
    private Long dealerId;
}
