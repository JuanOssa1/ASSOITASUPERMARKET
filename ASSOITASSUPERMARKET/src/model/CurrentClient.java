package model;

public class CurrentClient extends Client{
	
	private CurrentClient left; 
	private CurrentClient right;
	public CurrentClient(String id, String name, String age, String email) {
		super(id, name, age, email);
		// TODO Auto-generated constructor stub
	}
	public CurrentClient getLeft() {
		return left;
	}
	public void setLeft(CurrentClient left) {
		this.left = left;
	}
	public CurrentClient getRight() {
		return right;
	}
	public void setRight(CurrentClient right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "CurrentClient [getId()=" + getId() + ", getName()=" + getName() + ", getAge()=" + getAge()
				+ ", getEmail()=" + getEmail() + "]";
	}
	

}
