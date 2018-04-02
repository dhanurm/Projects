package com.stock.stock.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.stock.stock.model.Category;


@Repository
public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {

}
