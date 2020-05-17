package de.hey_car.repository;

import de.hey_car.entity.VehicleEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 *
 */
public interface VehicleRepository extends JpaRepository<VehicleEntity, String>, QuerydslPredicateExecutor<VehicleEntity> {
    List<VehicleEntity> findAll(Specification<VehicleEntity> specification);
}
