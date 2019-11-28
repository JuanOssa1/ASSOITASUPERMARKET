package model;

import java.io.Serializable;

import logicInterfaces.DateRegistrator;

public abstract class Realstate implements DateRegistrator, Serializable{
	private String id;
	private int quantity;
	private String buyYear;
	private String name;
	public Realstate(int quantity, String buyYear, String name, String id) {
		super();
		this.quantity = quantity;
		this.buyYear = buyYear;
		this.name = name;
		this.id = id;
	}
	public int getQuantity() {
		return quantity; 
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBuyYear() {
		return buyYear;
	}
	public void setBuyYear(String buyYear) {
		this.buyYear = buyYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Realstate [id=" + id + ", quantity=" + quantity + ", buyYear=" + buyYear + ", name=" + name + "]";
	}
	public void addDate() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Description: Permite acceder al valor de los atributos genericos de tipo Product
	 * gets()*
	 */
	
	/**
	 * Description: Permite modificar los atributos genericos de tipo producto
	 * sets()*
	 */
}
