package com.projects.informatics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.informatics.entities.FuelType;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

	FuelType findByName(String name);

}
