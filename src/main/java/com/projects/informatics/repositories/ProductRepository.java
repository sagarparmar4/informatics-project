package com.projects.informatics.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projects.informatics.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByModelName(String modelName);

	List<Product> findByModelNameContaining(String modelName);

	@Query("SELECT p FROM Product p WHERE p.modelName LIKE CONCAT('%', :modelName, '%')")
	List<Product> findByFilters(@Param("modelName") String modelName, Sort sort);

}
