package model;

import java.util.ArrayList;

public class SuperMarketApp {
	private Inventory inventory; //ESTO SE SERIALIZA
	private Fidelization fidelization; //ESTO SE SERIALIZA
	private CashRegister cashRegister; //ESTO SE SERIALIZA
	private ArrayList<Manager> managers; //ESTO SE SERIALIZA
	private ArrayList<Administrator> administrators; //ESTO SE SERIALIZA
	private ArrayList<PublicState> publicStates; //ESTO SE SERIALIZA
	private ArrayList<PrivateState> privateStates; //ESTO SE SERIALIZA
	public SuperMarketApp() {
		managers = new ArrayList<Manager>();
		administrators = new ArrayList<Administrator>();
		publicStates = new ArrayList<PublicState>();
		privateStates = new ArrayList<PrivateState>();
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Fidelization getFidelization() {
		return fidelization;
	}
	public void setFidelization(Fidelization fidelization) {
		this.fidelization = fidelization;
	}
	public CashRegister getCashRegister() {
		return cashRegister;
	}
	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}
	public ArrayList<Manager> getManagers() {
		return managers;
	}
	public void setManagers(ArrayList<Manager> managers) {
		this.managers = managers;
	}
	public ArrayList<Administrator> getAdministrators() {
		return administrators;
	}
	public void setAdministrators(ArrayList<Administrator> administrators) {
		this.administrators = administrators;
	}
	public ArrayList<PublicState> getPublicStates() {
		return publicStates;
	}
	public void setPublicStates(ArrayList<PublicState> publicStates) {
		this.publicStates = publicStates;
	}
	public ArrayList<PrivateState> getPrivateStates() {
		return privateStates;
	}
	public void setPrivateStates(ArrayList<PrivateState> privateStates) {
		this.privateStates = privateStates;
	}
	
}
