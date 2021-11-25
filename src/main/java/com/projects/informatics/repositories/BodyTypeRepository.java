package com.projects.informatics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.informatics.entities.BodyType;

@Repository
public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {

	BodyType findByName(String name);

}
