package com.stock.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.stock.model.Customer;
import com.stock.stock.repository.CustomerRepo;

@Service
public class CustomerService {

	private final CustomerRepo c;

	@Autowired
	public CustomerService(CustomerRepo c) {
		this.c = c;
	}

	public List<Customer> findAll() {
		return (List<Customer>) c.findAll();
	}

	public void insert(Customer customer) {
		c.save(customer);
	}

	public void delete(Customer customer) {
		c.delete(customer);
	}

	public boolean findCustomer(String uName){
		boolean flag=false;
		List<Customer> c= findAll();
		for(Customer cus:c)
		{
			if(cus.getUserName().toLowerCase().contains(uName.toLowerCase())) {
				flag= true;
				break;
			}
			flag=false;
		}
		return flag;
	}
	public boolean findCustomer(String uName,String password) {
		boolean flag=false;
		List<Customer> c= findAll();
		for(Customer cus:c)
		{
			if(cus.getUserName().toLowerCase().contains(uName.toLowerCase())&&(cus.getPassword().contains(password))) {
				flag= false;
				break;
			}
			flag=true;
		}
		return flag;
	}
}
