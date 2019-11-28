package model;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Product implements Serializable {
	private String id;
	private String name;
	private String bestBefore;
	private double price;
	private String productType;
	public Product(String id, String name, String bestBefore, double price, String productType) {
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
	public String getBestBefore() {
		return bestBefore;
	}
	public void setBestBefore(String bestBefore) {
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
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", bestBefore=" + bestBefore + ", price=" + price
				+ ", productType=" + productType + "]";
	}
	/**
	 * Description: Permite acceder a los valores de un objeto tipo product
	 * gets()*
	 */
	
	/**
	 * Description:  Permite modificar los valores de un objeto tipo product
	 * sets()*;
	 */

}
