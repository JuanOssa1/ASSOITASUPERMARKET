package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import exceptions.unavaiableIdException;

public class SuperMarketApp {
	private Inventory inventory; //ESTO SE SERIALIZA
	private Fidelization fidelization; //ESTO SE SERIALIZA
	private CashRegister cashRegister; //ESTO SE SERIALIZA
	private ArrayList<Worker> workers;
	private ArrayList<Realstate> realStates;
	public static String FLATWORKERS = "marketRecords//worker.txt";
	//public static String FLATADMINISTRATORS = "marketRecords//administrator.txt";
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
	public String createNewManager(String name, String id, String eps, String salary, String experience, String contract) {
		// "Letter C summary of -C-onverted"
		String msg = "";
		int salaryC = Integer.parseInt(salary);
		int experienceC = Integer.parseInt(experience);
		try {
			validateAvailabilityOfTheId(id);
			Worker manager = new Manager(name, id, eps, salaryC, experienceC, contract);
			workers.add(manager);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
		}
		return msg;
	}
	public String createNewAdministrator(String name, String id, String eps, String salary, String experience) {
		String msg = ""; 
		int salaryC = Integer.parseInt(salary);
		int experienceC = Integer.parseInt(experience);
		try {
			validateAvailabilityOfTheId(id);
			Worker administrator = new Administrator(name, id, eps, salaryC, experienceC);
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
	//------------------------------------------> SISTEMAS DE CARGA
			//POR VERFICIAR:
			//---->CHECK! writeManagers()
	public String readManagers() {	
		String msg = "DATOS CARGADOS CON EXITO!!!";
		try {
			File	file = new File(FLATWORKERS);
			FileReader	frReader = new FileReader(file);
			BufferedReader	bufferRead = new BufferedReader(frReader);
			String saveString;
			while( (saveString = bufferRead.readLine()) != null){
				String[] parts = saveString.split(","); 
				String name = parts[0]; 
				String id = parts[1];
				String eps = parts[2];
				String salary = parts[3];
				String experience = parts[4];
				String contract = parts[5];
				if(contract != null) {
					createNewManager( name,  id,  eps,  salary,  experience,  contract);
				} else {
					createNewAdministrator(name,  id,  eps,  salary,  experience);
				}
				 		
			}	
			bufferRead.close();
			frReader.close();	
		}
		catch(Exception e){
			e.printStackTrace();
			}
		return msg;
		
	}
	public void writeManagers() {
		try{
			File file = new File(FLATWORKERS);
			FileWriter filwri =  new FileWriter(file);
			BufferedWriter  buffer = new BufferedWriter(filwri);
			for(int i = 0; i < workers.size();i++) {
				if(workers.get(i) instanceof Manager) {
					Manager manager = (Manager) workers.get(i);
					buffer.write(manager.getName()+","+manager.getId()+","+manager.getEps()+","+manager.getSalary()+","+manager.getExperience());
				} else {
					Administrator administrator = (Administrator)workers.get(i);
					buffer.write(administrator.getName()+","+administrator.getId()+","+administrator.getEps()+","+administrator.getSalary()+","+administrator.getExperience());
				}
			}
			buffer.close();
			filwri.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void startProgramLoadGamers(){
		File file = new File(SEARCHROUTE);
		ArrayList<Gamer> p;
		try {
			
				FileInputStream	fi = new FileInputStream(file);
				ObjectInputStream co = new ObjectInputStream(fi);
				p = (ArrayList<Gamer>)co.readObject();
				setGamers(p); 
				co.close();
			}
			
		
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public void sortGamersScore() {	
		for(int i = 0; i < gamers.size()-1; i++) {
			Gamer small = gamers.get(i);
			int wich = i;
			for(int j = i + 1; j < gamers.size(); j++) {
				if(gamers.get(j).getScore() < small.getScore()) {
					small = gamers.get(j);
					wich = j;
				}
				Gamer tmp = gamers.get(i);
				gamers.set(i, small);
				gamers.set(wich, tmp);
			}
		}
	}
	
}
