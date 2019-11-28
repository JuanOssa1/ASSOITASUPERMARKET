package model;

import java.io.Serializable;
import java.util.Calendar;

public class PublicState extends Realstate implements Serializable{
	private String maintenance;

	public PublicState(int quantity, String buyYear, String name, String id, String maintenance) {
		super(quantity, buyYear, name, id);
		this.maintenance = maintenance;
	}
	/**
	 * Description: Permite obtener el manteninimiento de un objeto public state
	 * @return
	 */
	public String getMaintenance() {
		return maintenance;
	}
	/**
	 * Description: Permite modificar el manteniminento de un objeto tipo public state
	 * @param maintenance
	 */
	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}
	
	/**
	 * Description: Converte a String el valor de todos los atributos de un objeto PublicState
	 */
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
