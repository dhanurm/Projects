package com.stock.stock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.stock.stock.model.Category;
import com.stock.stock.model.Customer;
import com.stock.stock.model.Product;
import com.stock.stock.service.CategoryService;
import com.stock.stock.service.CustomerService;
import com.stock.stock.service.ProductService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import com.vaadin.ui.Button;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

import com.vaadin.ui.HorizontalLayout;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SpringUI
@Theme("apptheme")
public class StartApp extends UI {

	private static final long serialVersionUID = -8876001421435329573L;
//Add All Component for UI Using Vaadin
	private Panel panel;
	private FormLayout formlayout;
	private Upload up;
	private Receiver rs;
	private HorizontalLayout hLayout;
	private Label label;
	private VerticalLayout vLayout;
	private Panel panel2;
	private Button Login;
	private Button signup;
	private Window window;
	private Panel panel3;
	private FormLayout fLayout;
	private TextField userFName;
	private TextField userLName;
	private TextField userField;
	private PasswordField password;
	private TextField userNField;
	private PasswordField upassword;
	private TextField CName;
	private HorizontalLayout h;
	private final CustomerService customrser;
	private final CategoryService categoryservice;
	private final ProductService productservice;
	private final CategoryUI cUi;
	private final ProductUi pUi;
	private TextField proDesc;
	private TextField proName;
	private TextField proActualRate;
	Product p = new Product();
	private ComboBox<Category> select;

	//Wired bean for loose coupling
	@Autowired
	public StartApp(CustomerService customrser, CategoryService categoryservice, ProductService productservice,CategoryUI cUi,ProductUi pUi) {
		this.customrser = customrser;
		this.categoryservice = categoryservice;
		this.productservice = productservice;
		this.cUi=cUi;
		this.pUi=pUi;
	}

	//start Ui
	@Override
	protected void init(VaadinRequest request) {
		setUpUI();

	}

	//Main dashboard start
	private void setUpUI() {
		hLayout = new HorizontalLayout();
		hLayout.addStyleName("hLayout");
		panel = new Panel();
		vLayout = new VerticalLayout();
		label = new Label("Stock Managment");
		label.addStyleName("label");
		panel.addStyleName("card");
		vLayout.addComponent(label);
		panel.setContent(vLayout);
		panel2 = new Panel();
		panel2.addStyleName("panel2");
		panel2.setContent(addcontent());
		hLayout.addComponents(panel, panel2);
		setContent(hLayout);
	}

	//Add component for dashboard
	private Component addcontent() {
		formlayout = new FormLayout();
		Label singupL = new Label("SignIn");
		singupL.addStyleName("label");
		formlayout.addStyleName("layout");
		userField = new TextField();
		password = new PasswordField();
		userField.setPlaceholder("user Name");
		userField.setWidth("330px");

		password.setWidth("330px");
		password.setPlaceholder("Password");
		Login = new Button("SignIn", event -> Login());
		Login.setWidth("330px");
		Login.addStyleName("contact");
		signup = new Button("Signup", event -> signup());
		signup.setWidth("330px");
		signup.addStyleName("contact");
		formlayout.addComponents(singupL, userField, password, Login, signup);
		return formlayout;
	}

	//Signup Component
	private void signup() {
		window = new Window();
		HorizontalLayout h = new HorizontalLayout();
		panel3 = new Panel();
		panel3.addStyleName("panel3");
		fLayout = new FormLayout();
		Label singupL = new Label("Signup");
		singupL.addStyleName("label");
		userNField = new TextField();
		userNField.setWidth("330px");
		userNField.setPlaceholder("user Name");
		userField.setWidth("330px");

		userFName = new TextField();
		userFName.setPlaceholder("First Name");
		userFName.setWidth("330px");
		userLName = new TextField();
		userLName.setPlaceholder("Last Name");
		userLName.setWidth("330px");
		upassword = new PasswordField();
		upassword.setWidth("330px");
		upassword.setPlaceholder("Password");
		Button button = new Button("Register", event -> saveCustomer());
		fLayout.addComponents(singupL, userNField, userFName, userLName, upassword, button);
		panel3.setContent(fLayout);
		h.addComponent(panel3);
		window.setContent(h);
		window.center();
		addWindow(window);
	}
//Save the signup customer
	private void saveCustomer() {
		Customer c = new Customer();
		String userName = userNField.getValue();
		String userfName = userFName.getValue();
		String userlName = userLName.getValue();
		String password = upassword.getValue();

		c.setFirstName(userfName);
		c.setLastName(userlName);
		c.setUserName(userName);
		c.setPassword(password);
		if ((userfName == null || userfName == "") && (userlName == null || userlName == "")
				&& (userName == null || userName == "") && (password == null || password == "")) {

			Label Notification = new Label("Please Enter All Values");
			hLayout.addComponent(Notification);
		} else {
			boolean flag = customrser.findCustomer(userName);
			if (flag == true) {
				Label Notification = new Label("Please Enter Unique UserName");
				hLayout.addComponent(Notification);
			} else {
				customrser.insert(c);
				window.close();
				addTab();
			}
		}

	}
//Check customer is exists or not then get access to next page
	private void Login() {
		String userName = userField.getValue();
		String userPassword = password.getValue();

		if ((userName == null || userName == "") && (userPassword == null || userPassword == "")) {
			Label Notification = new Label("Please Enter All Values");
			hLayout.addComponent(Notification);
		} else {
			boolean flag = customrser.findCustomer(userName, userPassword);
			if (flag == true) {
				Label Notification = new Label("Please Enetr correct User Name and password");
				hLayout.addComponent(Notification);
			} else {
				addTab();
			}
		}

	}

//Add All tab to the next page	
	private void addTab() {
		hLayout.removeAllComponents();
		vLayout = new VerticalLayout();
		vLayout.addStyleName("hLayout");
		TabSheet tab = new TabSheet();

		// tab.addStyleName(ValoTheme.TABSHEET_FRAMED);
		Button category = new Button("Add Category", event -> AddCAtegory());
		category.setWidth("200px");
		tab.addTab(category);
		Button product = new Button("Add Product", event -> AddProduct());
		product.setWidth("200px");
		tab.addTab(product);

		Button Showproduct = new Button("ALL Product", event -> AllProduct());
		Showproduct.setWidth("200px");
		tab.addTab(Showproduct);

		Button delete = new Button("Delete", event -> delete());
		delete.setWidth("200px");
		tab.addTab(delete);

		vLayout.addComponent(tab);
		setContent(vLayout);
	}

	
	//delete perticular
	private void delete() {
		window = new Window();
		HorizontalLayout h = new HorizontalLayout();
		Label singupL = new Label("Delete Product And category");
		singupL.addStyleName("label");
		panel3 = new Panel();
		panel3.addStyleName("panel3");
		fLayout = new FormLayout();
	    select = cUi.setUpLayout();
		Button button = new Button("Delete Product");
		button.addClickListener(event -> {
			cUi.selectCat();
			window.close();
		});
		fLayout.addComponents(singupL, select, button);
		panel3.setContent(fLayout);
		h.addComponent(panel3);
		window.setContent(h);
		window.center();
		addWindow(window);
	}

	//display all Product
	private void AllProduct() {
		window = new Window();
		window.setContent(pUi.allProduct());
		addWindow(window);
	}
	
	//Add Category

	private void AddCAtegory() {
		window = new Window();
		h = new HorizontalLayout();
		panel3 = new Panel();
		panel3.addStyleName("panel3");
		fLayout = new FormLayout();
		Label singupL = new Label("Category");
		singupL.addStyleName("label");
		CName = new TextField();
		CName.setWidth("330px");
		CName.setPlaceholder(" Enter Category Name");
		Button button = new Button("Add Category", event -> saveCategory());
		fLayout.addComponents(singupL, CName, button);
		panel3.setContent(fLayout);
		h.addComponent(panel3);
		window.setContent(h);
		window.center();
		addWindow(window);

	}

	//Save Category(check category is already not exitst)
	private void saveCategory() {
		String categoryName = CName.getValue();
		Category cat = new Category();
		if ((categoryName == null || categoryName == "")) {
			Label Notification = new Label("Please Enter All Values");
			hLayout.addComponent(Notification);
		} else {
			cat.setCategoryName(categoryName);
			boolean flag = categoryservice.findAllCategory(cat);
			if (flag == true) {
				Label Notification = new Label("category already exist");
				h.addComponent(Notification);
			} else {
				categoryservice.insert(cat);
				window.close();
			}
		}

	}

	//Add All Product
	private void AddProduct() {

		window = new Window();
		HorizontalLayout h = new HorizontalLayout();
		Label singupL = new Label("Product");
		singupL.addStyleName("label");
		panel3 = new Panel();
		panel3.addStyleName("panel3");
		fLayout = new FormLayout();

		 select = cUi.setUpLayout();
		up = new Upload();
		rs = new Receiver() {
			private static final long serialVersionUID = 1L;

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				try {
					FileOutputStream fout = new FileOutputStream(filename);
					p.setpPhoto(filename);
					return fout;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		up.setReceiver(rs);
		proName = new TextField();
		proName.setWidth("330px");
		proName.setPlaceholder(" Enter Product Name");

		proDesc = new TextField();
		proDesc.setWidth("330px");
		proDesc.setPlaceholder(" Enter Product Description");

		proActualRate = new TextField();
		proActualRate.setWidth("330px");
		proActualRate.setPlaceholder(" Enter Product rate");

		Button button = new Button("Add Product", event -> saveProduct());
		fLayout.addComponents(singupL, proName, proDesc, proActualRate, select, up, button);
		panel3.setContent(fLayout);
		h.addComponent(panel3);
		window.setContent(h);
		window.center();
		addWindow(window);
	}

	//save product to database
	private void saveProduct() {

		Category cat = (select.getValue());
		HashSet<Category> categories = new HashSet<Category>();
		categories.add(cat);
		float rate = Float.parseFloat(proActualRate.getValue());
		p.setProActualRate(rate);
		p.setCategory(categories);
		p.setProName(proName.getValue());
		p.setProDesc(proDesc.getValue());
		productservice.insertProduct(p);
		window.close();
	}

}
