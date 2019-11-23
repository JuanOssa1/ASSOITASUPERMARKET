package model;

public class CurrentClient extends Client{
	
	private CurrentClient left; 
	private CurrentClient right;
	public CurrentClient(String id, String name, String age, String string) {
		super(id, name, age, string);
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
	

}
