package com.projects.informatics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.informatics.entities.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

	Color findByName(String name);

}
