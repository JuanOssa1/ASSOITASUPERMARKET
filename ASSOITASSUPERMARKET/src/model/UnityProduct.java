package model;

import java.util.Calendar;

public class UnityProduct extends Product{
	private int quantity;
	private UnityProduct next; 
	private UnityProduct previus;
	public UnityProduct(String id, String name, String bestBefore, double price, String productType, int quantity) {
		super(id, name, bestBefore, price, productType);
		this.quantity = quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public UnityProduct getNext() {
		return next;
	}
	public void setNext(UnityProduct next) {
		this.next = next;
	}
	public UnityProduct getPrevius() {
		return previus;
	}
	public void setPrevius(UnityProduct previus) {
		this.previus = previus;
	}
	
}
