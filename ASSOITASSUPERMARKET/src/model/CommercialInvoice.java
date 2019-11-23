package model;

import java.util.ArrayList;

public class CommercialInvoice {
	private String date;
	private double totalPrice;
	private String paymentType;
	private String factureNumber;
	private CommercialInvoice left; 
	private CommercialInvoice right;
	private Client client;
	private ArrayList<Product> products;
	public CommercialInvoice(String date, double totalPrice, String paymentType, String factureNumber) {
		super();
		this.date = date;
		this.totalPrice = totalPrice;
		this.paymentType = paymentType;
		this.factureNumber = factureNumber;
		products = new ArrayList<Product>();
	}
	public CommercialInvoice getLeft() {
		return left;
	}
	public void setLeft(CommercialInvoice left) {
		this.left = left;
	}
	public CommercialInvoice getRight() {
		return right;
	}
	public void setRight(CommercialInvoice right) {
		this.right = right;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getFactureNumber() {
		return factureNumber;
	}
	public void setFactureNumber(String factureNumber) {
		this.factureNumber = factureNumber;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
}
