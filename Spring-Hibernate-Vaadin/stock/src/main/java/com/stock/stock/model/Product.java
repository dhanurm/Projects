package com.stock.stock.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -7216504873675907598L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proId")
	private long proId;

	@NotNull
	@Column(nullable = false)
	private String proName;

	@NotNull
	@Column(nullable = false)
	private String proDesc;

	@NotNull
	private float proActualRate;

	@NotNull
	private String pPhoto;
	public String getpPhoto() {
		return pPhoto;
	}

	public void setpPhoto(String pPhoto) {
		this.pPhoto = pPhoto;
	}

	@ManyToMany
	@JoinTable(name = "product_category", joinColumns = { @JoinColumn(name = "proId") }, inverseJoinColumns = {
			@JoinColumn(name = "categoryId") })
	private Set<Category> category;

	
	public long getProId() {
		return proId;
	}

	public String getProName() {
		return proName;
	}

	public String getProDesc() {
		return proDesc;
	}

	public float getProActualRate() {
		return proActualRate;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public void setProActualRate(float proActualRate) {
		this.proActualRate = proActualRate;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

}