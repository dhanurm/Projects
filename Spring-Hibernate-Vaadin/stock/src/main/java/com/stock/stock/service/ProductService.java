package com.stock.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.stock.model.Product;
import com.stock.stock.repository.ProductRepo;


@Service
public class ProductService {
	@Autowired
	ProductRepo p;
	
	public void insertProduct(Product product) {
		p.save(product);
	}

	public List<Product> findAll() {
		
		return (List<Product>) p.findAll();
	}
}
