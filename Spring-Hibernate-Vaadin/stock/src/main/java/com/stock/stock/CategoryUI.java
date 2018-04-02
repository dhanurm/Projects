package com.stock.stock;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.stock.stock.model.Category;
import com.stock.stock.service.CategoryService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;



@SpringComponent
public class CategoryUI  extends VerticalLayout{

	private static final long serialVersionUID = -788850317041456826L;
	private final CategoryService categoryservice;
	private ComboBox<Category> select;
	

	@Autowired
public CategoryUI(CategoryService categoryservice) {
		
		this.categoryservice = categoryservice;
	}

	
//return Combobox to main UI
   public ComboBox<Category> setUpLayout() {
	 select = new ComboBox<Category>();
	List<Category> category = categoryservice.findCategory();
	select.setPlaceholder("no Category Selected");
	select.setItems(category);
	select.setWidth("330px");
	select.setItemCaptionGenerator(c -> c.getCategoryName());
	addComponent(select);
	return select;
	
}
   //Return selected Combobox value to UI
   public Category selectCat() {
	   Category cat = (select.getValue());
		HashSet<Category> categories = new HashSet<Category>();
		categories.add(cat);
		categoryservice.delete(cat);
		return cat;
   }
   
	
}
