package model;

import java.io.Serializable;
import java.util.Calendar;

public class PublicState extends Realstate implements Serializable{
	private String maintenance;

	public PublicState(int quantity, String buyYear, String name, String id, String maintenance) {
		super(quantity, buyYear, name, id);
		this.maintenance = maintenance;
	}

	public String getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}
	
	
	@Override
	public String toString() {
		return "PublicState [maintenance=" + maintenance + ", getQuantity()=" + getQuantity() + ", getBuyYear()="
				+ getBuyYear() + ", getName()=" + getName() + ", getId()=" + getId() + "]";
	}

	@Override
	public void addDate() {
		// TODO Auto-generated method stub
		
	}
}
