package model;

import logicInterfaces.DateRegistrator;

public abstract class Realstate implements DateRegistrator{
	private int quantity;
	private String buyYear;
	private String name;
	public Realstate(int quantity, String buyYear, String name) {
		super();
		this.quantity = quantity;
		this.buyYear = buyYear;
		this.name = name;
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
	public void addDate() {
		// TODO Auto-generated method stub
		
	}
}
