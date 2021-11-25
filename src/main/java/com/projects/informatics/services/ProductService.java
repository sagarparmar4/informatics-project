package com.projects.informatics.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.projects.informatics.entities.Product;
import com.projects.informatics.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getProducts(String searchQuery, Map<String, String> filters) {
		if (!filters.isEmpty()) {
			Assert.isTrue(filters.containsKey("column"), "Filter column not provided");
			Assert.isTrue(filters.containsKey("order"), "Filter order not provided");

			Sort sort = Sort.by(Direction.fromString(filters.get("order")), filters.get("column"));
			List<Product> test = productRepository.findByFilters(searchQuery, sort);
			return test;
		}
		return productRepository.findByModelNameContaining(searchQuery);
	}
}
