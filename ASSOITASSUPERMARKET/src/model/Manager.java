package model;

public class Manager extends Workers {
	private String contract;

	public Manager(String name, String id, int salary, int experience, String contract) {
		super(name, id, salary, experience);
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

	
}
