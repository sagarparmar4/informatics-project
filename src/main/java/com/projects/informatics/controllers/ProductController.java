package com.projects.informatics.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.informatics.entities.Product;
import com.projects.informatics.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/getProducts")
	public List<Product> getProducts(@RequestParam String searchQuery,
			@RequestBody(required = false) Map<String, String> filters) {
		return productService.getProducts(searchQuery, filters);
	}

}
