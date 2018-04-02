package com.stock.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.stock.model.Category;
import com.stock.stock.repository.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	CategoryRepo cat;

	public List<Category> findCategory() {
		return (List<Category>) cat.findAll();
	}

	public void insert(Category category) {
		cat.save(category);
	}
	public boolean findAllCategory(Category category) {
		boolean flag = false;
		List<Category> c = findCategory();
		for (Category c1 : c) {
			if ((c1.getCategoryName().toLowerCase()).contains(category.getCategoryName().toLowerCase())) {
				flag = true;
				break;
			}
			flag = false;
		}
		return flag;
	}

	public void delete(Category cat2) {
		cat.delete(cat2);
		
	}
}
