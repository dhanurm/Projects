package com.stock.stock.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.stock.stock.model.Customer;


@Repository
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long> {
}
