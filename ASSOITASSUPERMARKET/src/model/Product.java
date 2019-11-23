package model;

import java.util.Calendar;

public abstract class Product {
	private String id;
	private String name;
	private Calendar bestBefore;
	private double price;
	private String productType;
	public Product(String id, String name, Calendar bestBefore, double price, String productType) {
		super();
		this.id = id;
		this.name = name;
		this.bestBefore = bestBefore;
		this.price = price;
		this.productType = productType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getBestBefore() {
		return bestBefore;
	}
	public void setBestBefore(Calendar bestBefore) {
		this.bestBefore = bestBefore;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	

}
