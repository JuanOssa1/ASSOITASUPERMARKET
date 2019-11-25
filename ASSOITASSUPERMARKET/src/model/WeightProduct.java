package model;

import java.util.Calendar;

public class WeightProduct extends Product {
	private double weight;
	private WeightProduct next; 
	private WeightProduct previus;
	public WeightProduct(String id, String name, String bestBefore, double price, String productType, double weight) {
		super(id, name, bestBefore, price, productType);
		this.weight = weight;
	} 
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public WeightProduct getNext() {
		return next;
	}
	public void setNext(WeightProduct next) {
		this.next = next;
	}
	public WeightProduct getPrevius() {
		return previus;
	}
	public void setPrevius(WeightProduct previus) {
		this.previus = previus;
	}
}
