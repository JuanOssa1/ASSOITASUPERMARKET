package model;

import java.io.Serializable;

public class PrivateState extends Realstate implements Serializable{
	public PrivateState(int quantity, String buyYear, String name, String id) {
		super(quantity, buyYear, name, id);
	}
	
	@Override
	public String toString() {
		return "PrivateState [getQuantity()=" + getQuantity() + ", getBuyYear()=" + getBuyYear() + ", getName()="
				+ getName() + ", getId()=" + getId() + "]";
	}

	@Override
	public void addDate() {
		// TODO Auto-generated method stub
		
	}
	
}
