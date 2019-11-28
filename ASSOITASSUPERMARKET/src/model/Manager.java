package model;

import java.io.Serializable;

public class Manager extends Worker implements Serializable{
	private String contract;

	public Manager(String name, String id, String eps, int salary, int experience, String contract) {
		super(name, id, eps, salary, experience);
		this.contract = contract;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	@Override
	public void addDate() {
		// TODO Auto-generated method stub	
	}
	@Override
	public String toString() {
		return "Manager [contract=" + contract + ", getName()=" + getName() + ", getId()=" + getId() + ", getSalary()="
				+ getSalary() + ", getExperience()=" + getExperience();
	}
	/**
	 * Description: Permite acceder al valor de los atributos de un objeto Manager
	 * gets()*
	 */
	/**
	 * Description: Permite modificar los valores de los atributos de un objeto Manager
	 * sets()*
	 */
}
