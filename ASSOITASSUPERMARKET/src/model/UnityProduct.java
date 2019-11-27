package model;



import java.io.Serializable;

import exceptions.insufficientQuantityException;

public class UnityProduct extends Product implements Serializable{
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
	
	@Override
	public String toString() {
		return "UnityProduct [quantity=" + quantity + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getBestBefore()=" + getBestBefore() + ", getPrice()=" + getPrice() + ", getProductType()="
				+ getProductType() + "]";
	}
	public void update(int requiredQuantity) throws insufficientQuantityException {
		if(quantity-requiredQuantity < 0) {
			throw new insufficientQuantityException("Epic Failure");
		}else {
			quantity = quantity - requiredQuantity;
		}
	}
}
