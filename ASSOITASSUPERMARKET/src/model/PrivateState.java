package model;

public class PrivateState extends Realstate{
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
