package model;

import java.io.Serializable;
import java.util.Calendar;

import exceptions.insufficientQuantityException;

public class WeightProduct extends Product implements Serializable {
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
	
	@Override
	/**
	 * Description: Convierte a String cada uno de los valores de los atributos de un objetp weighpriduct
	 */
	public String toString() {
		return "WeightProduct [weight=" + weight + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getBestBefore()=" + getBestBefore() + ", getPrice()=" + getPrice() + ", getProductType()="
				+ getProductType() + "]";
	}
	/**
	 * Description: Permite actualizar la cantidad disponible en inventrio de un producto tipo weight
	 * @param requiredQuantity
	 * @throws insufficientQuantityException
	 */
	public void update(double requiredQuantity) throws insufficientQuantityException {
		if(weight-requiredQuantity < 0) {
			throw new insufficientQuantityException("Epic Failure");
		}else {
			weight = weight - requiredQuantity;
		}
		
	}
	/**
	 * Description: Permite acceder al valor de los atributos de un objeto tipo WeightProduct
	 * gets()*
	 */
	
	/**
	 * Description: Permite modificar el valor de los atributos de un objeto tipo WeightProduct
	 * sets()*
	 */
}
