package com.stock.stock.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.stock.stock.model.Product;


@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

}

