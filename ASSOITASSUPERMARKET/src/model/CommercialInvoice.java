package model;

import java.util.ArrayList;

public class CommercialInvoice {
	private String date;
	private double totalPrice;
	private String paymentType;
	private String factureNumber;
	private CommercialInvoice next; 
	private CommercialInvoice previus;
	private Client client;
	private ArrayList<Product> products;
	public CommercialInvoice(String date,/* double totalPrice,*/ String paymentType, String factureNumber, ArrayList<Product> products, Client client) {
		super();
		this.date = date;
		/*this.totalPrice = totalPrice;*/
		this.paymentType = paymentType;
		this.factureNumber = factureNumber;
		//products = new ArrayList<Product>();
		this.products = products;
		products = new ArrayList<Product>();
	}
	public CommercialInvoice getPrevius() {
		return previus;
	}
	public void setPrevius(CommercialInvoice previus) {
		this.previus = previus;
	}
	public CommercialInvoice getNext() {
		return next;
	}
	public void setNext(CommercialInvoice next) {
		this.next = next;
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
