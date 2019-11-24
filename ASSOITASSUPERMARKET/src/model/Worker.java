package model;

import logicInterfaces.DateRegistrator;

public abstract class Worker implements DateRegistrator{
	private String name;
	private String id;
	private String eps;
	private int salary;
	private int experience;
	public Worker(String name, String id, String eps, int salary, int experience) {
		super();
		this.name = name;
		this.id = id;
		this.eps = eps;
		this.salary = salary;
		this.experience = experience;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public void addDate() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		return "Worker [name=" + name + ", id=" + id + ", eps=" + eps + ", salary=" + salary + ", experience="
				+ experience + "]";
	}
	
	
}
