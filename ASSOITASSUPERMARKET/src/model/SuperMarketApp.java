package model;

import java.util.ArrayList;

import exceptions.unavaiableIdException;

public class SuperMarketApp {
	private Inventory inventory; //ESTO SE SERIALIZA
	private Fidelization fidelization; //ESTO SE SERIALIZA
	private CashRegister cashRegister; //ESTO SE SERIALIZA
	private ArrayList<Worker> workers;
	private ArrayList<Realstate> realStates;
	/*
	private ArrayList<Manager> managers; //ESTO SE SERIALIZA
	private ArrayList<Administrator> administrators; //ESTO SE SERIALIZA
	private ArrayList<PublicState> publicStates; //ESTO SE SERIALIZA
	private ArrayList<PrivateState> privateStates; //ESTO SE SERIALIZA
	*/
	public SuperMarketApp() {
		workers = new ArrayList<Worker>();
		realStates = new ArrayList<Realstate>();
		/*
		managers = new ArrayList<Manager>();
		administrators = new ArrayList<Administrator>();
		publicStates = new ArrayList<PublicState>();
		privateStates = new ArrayList<PrivateState>();
		*/
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
	/*
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
	*/
	public ArrayList<Worker> getWorkers() {
		return workers;
	}
	public void setWorkers(ArrayList<Worker> workers) {
		this.workers = workers;
	}
	public ArrayList<Realstate> getRealStates() {
		return realStates;
	}
	public void setRealStates(ArrayList<Realstate> realStates) {
		this.realStates = realStates;
	}
	private void validateAvailabilityOfTheId(String id) throws unavaiableIdException {
		for (int i = 0; i < workers.size(); i++) {
			if(workers.get(i).getId().equals(id)) {
				throw new unavaiableIdException("Error!");
			}
		}
	}
	//-----------------------> Workers
	public String createNewManager(String name, String id, String eps, int salary, int experience, String contract) {
		String msg = ""; 
		try {
			validateAvailabilityOfTheId(id);
			Worker manager = new Manager(name, id, eps, salary, experience, contract);
			workers.add(manager);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
		}
		return msg;
	}
	public String createNewAdministrator(String name, String id, String eps, int salary, int experience) {
		String msg = ""; 
		try {
			validateAvailabilityOfTheId(id);
			Worker administrator = new Administrator(name, id, eps, salary, experience);
			workers.add(administrator);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
		}
		return msg;
	}
	public String deleteWorker(String id) {
		String msg = "";
		for (int i = 0; i < workers.size(); i++) {
			if(workers.get(i).getId().equals(id)) {
				msg = "El Trabajador eliminado fue:"+" "+workers.get(i).toString();
				workers.remove(i);
			}else{
				msg = "El Trabajador con id "+ id +" no existe";
			}
		}
		return msg;
	}
	//--------------------------> Realstates
	
	
	private void validateAvailabilityOfTheIdRealstate(String id) throws unavaiableIdException {
		for (int i = 0; i < realStates.size(); i++) {
			if(realStates.get(i).getId().equals(id)) {
				throw new unavaiableIdException("Error!");
			}
		}
	}
	
	public String createNewPrivateState(int quantity, String buyYear, String name, String id) {
		String msg = ""; 
		try {
			validateAvailabilityOfTheIdRealstate(id);
			PrivateState privateState = new PrivateState(quantity, buyYear, name, id);
			realStates.add(privateState);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
		}
		return msg;
	}
	public String createNewPublicState(int quantity, String buyYear, String name, String maintennance, String id) {
		String msg = ""; 
		try {
			validateAvailabilityOfTheIdRealstate(id);
			PublicState publicState = new PublicState(quantity, buyYear, name, maintennance, id);
			realStates.add(publicState);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
		}
		return msg;
	}
	
	public String deleteRealState(String id) {
		String msg = "";
		for (int i = 0; i < realStates.size(); i++) {
			if(realStates.get(i).getId().equals(id)) {
				msg = "El Inmobiliario eliminado fue:"+" "+realStates.get(i).toString();
				realStates.remove(i);
			}else{
				msg = "El Inmobiliario con id "+ id +" no existe";
			}
		}
		return msg;
	}
}
