package model;

import java.io.Serializable;

public class Administrator extends Worker implements Serializable{

	public Administrator(String name, String id, String eps, int salary, int experience) {
		super(name, id, eps,  salary, experience);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void addDate() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Description: Permite mostrar el vslor de cada uno de los atributos del objeto creado
	 */
	@Override
	public String toString() {
		return "Administrator [getName()=" + getName() + ", getId()=" + getId() + ", getSalary()=" + getSalary()
				+ ", getExperience()=" + getExperience();
	}
	

}
