package com.stock.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.stock.stock.model.Product;
import com.stock.stock.service.ProductService;
import com.vaadin.spring.annotation.SpringComponent;

import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;


@SpringComponent
public class ProductUi extends VerticalLayout {

	private static final long serialVersionUID = 4804164533561376743L;
	private final ProductService productservice;

	private HorizontalLayout h;
	private Grid<Product> grid;

	@Autowired
	public ProductUi(ProductService productservice) {

		this.productservice = productservice;
	}

	//return grid view product to main UI
	public HorizontalLayout allProduct() {
		h = new HorizontalLayout();
		List<Product> p = productservice.findAll();
		grid = new Grid<Product>(Product.class);
		grid.setCaption("Product Information");
		grid.setItems(p);
		h.addComponent(grid);
		return h;

	}

}
