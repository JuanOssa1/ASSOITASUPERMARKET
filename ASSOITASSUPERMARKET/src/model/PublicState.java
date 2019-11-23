package model;

import java.util.Calendar;

public class PublicState extends Realstate{
	private Calendar maintenance;

	public PublicState(int quantity, String buyYear, String name, Calendar maintenance) {
		super(quantity, buyYear, name);
		this.maintenance = maintenance;
	}

	public Calendar getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Calendar maintenance) {
		this.maintenance = maintenance;
	}
	@Override
	public void addDate() {
		// TODO Auto-generated method stub
		
	}
}
