package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exceptions.insufficientQuantityException;
import exceptions.noMatchesException;
import exceptions.repeatedCustomerException;
import exceptions.thereAreNoRecordsException;
import exceptions.unavaiableIdException;

public class SuperMarketApp {
	private Inventory inventory; //ESTO SE SERIALIZA
	private Fidelization fidelization; //ESTO SE SERIALIZA
	private CashRegister cashRegister; //ESTO SE SERIALIZA
	private ArrayList<Worker> workers;
	private ArrayList<Realstate> realStates;
	public static String FLATWORKERS = "marketRecords//worker.txt";
	public static String FLATREALSTATES = "marketRecords//realStates.txt";
	public static String SERIALIZEINVENTORY = "marketRecords//inventory.dat";
	public static String SERIALIZEFIDELIZATION = "marketRecords//fidelization.dat";
	public static String SERIALIZECASHREGISTER = "marketRecords//cashRegister.dat";
	public SuperMarketApp() {
		workers = new ArrayList<Worker>();
		realStates = new ArrayList<Realstate>();
		inventory = new Inventory();
		fidelization = new Fidelization();
		cashRegister = new CashRegister();
		loadEverythig();
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
	//-----------------------> Workers
	private void validateAvailabilityOfTheId(String id) throws unavaiableIdException {
		for (int i = 0; i < workers.size(); i++) {
			if(workers.get(i).getId().equals(id)) {
				throw new unavaiableIdException("Error!");
			}
		}
	}
	//TESTED!
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
	//TESTED!
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
	//TESTED!
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
	//TESTED!
	public String createNewPrivateState(String quantity, String buyYear, String name, String id) {
		String msg = ""; 
		int quantityC = Integer.parseInt(quantity);
		try {
			validateAvailabilityOfTheIdRealstate(id);
			PrivateState privateState = new PrivateState(quantityC, buyYear, name, id);
			realStates.add(privateState);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
		}
		return msg;
	}
	//TESTED!
	public String createNewPublicState(String quantity, String buyYear, String name, String id, String maintenance) {
		String msg = ""; 
		int quantityC = Integer.parseInt(quantity);
		try {
			validateAvailabilityOfTheIdRealstate(id);
			PublicState publicState = new PublicState(quantityC, buyYear, name, id, maintenance );
			realStates.add(publicState);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
		}
		return msg;
	}
	//TESTED!
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
	//------------------------------------------>INVENTORY
			//UNITY<----------------------------------------------------------------------------------------------------------------------------------------------------
	//NOT TESTED!!!!
	public void addUnityProduct(String id, String name, String bestBefore, String price, String productType, String quantity) {
		double priceC = Double.parseDouble(price);
		int quantityC = Integer.parseInt(quantity);
		try {
			inventory.addUnityProductToTheList(id, name, bestBefore, priceC, productType, quantityC);
		} catch (unavaiableIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//NOT TESTED!!!!
	public void searchUnityProduct(String id) {
		try {
			inventory.searchUnityProduct(id);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//NOT TESTED!!!!
	public void updateUnityProduct(String id, String name, String bestBefore, String price, String productType, String quantity) {
		double priceC = Double.parseDouble(price);
		int quantityC = Integer.parseInt(quantity);
		try {
			inventory.updateUnityProductData(id, name, bestBefore, priceC, productType, quantityC);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//NOT TESTED!!!!
	public void deleteUnityProduct() {
		//inventory
	}
			//WEIGHT<---------------------------------------------------------------------------------------------------------------------------------------------------
	//NOT TESTED!!!!
	public void addWeightProduct(String id, String name, String bestBefore, String price, String productType, String weight) {
		Double priceC = Double.parseDouble(price);
		Double weightC = Double.parseDouble(weight);
		try {
			inventory.addWeightProductToTheList(id, name, bestBefore, priceC, productType, weightC);
		} catch (unavaiableIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//NOT TESTED!!!!
	public void searchWeightProduct(String id) {
		try {
			inventory.searchWeightProduct(id);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//NOT TESTED!!!!
	public void updateWeightProduct(String id, String name, String bestBefore, String price, String productType, String weight) {
		Double priceC = Double.parseDouble(price);
		Double weightC = Double.parseDouble(weight);
		try {
			inventory.updateWeightProductData(id, name, bestBefore, priceC, productType, weightC);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//NOT TESTED!!!!
	public void deleteWeightProduct() {
		//inventory
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------->FIDELIZATION
	//TESTED!!!!
	public void addLoyalClient(String id, String name, String age, String email, String points, String discountPercent, String dueCard) {
		int pointsC = Integer.parseInt(points);
		double discountPercentC = Double.parseDouble(discountPercent);
		try {
			fidelization.insertLoyalClient( id,  name,  age,  email,  pointsC,  discountPercentC, dueCard);
		} catch (repeatedCustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	//TESTED!!!!
	public void searchLoyalClient(String id) {
		try {
			fidelization.searchLoyalClientWithId(id);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//TESTED!!!!
	public void updateLoyalClient(String id, String name, String age, String email, String points, String discountPercent, String dueCard) {
		int pointsC = Integer.parseInt(points);
		double discountPercentC = Double.parseDouble(discountPercent);
		try {
			fidelization.updateLoyalClientWithId(id, name, age, email, pointsC, discountPercentC, dueCard);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//TESTED 
	public void deleteLoyalClient() {
			
	}
			//CURRENT<------------------------------------------------------------------------------------------------------------------------------------------------------
	//TESTED!!!!
	public void addCurrentClient(String id, String name, String age, String email) {
		try {
			fidelization.insertCurrentClient(id, name, age, email);
		} catch (repeatedCustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//TESTED!!!!
	public void searchCurrentClient(String id) {
		try {
			fidelization.searchCurrentClientWithId(id);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//TESTED!!!!
	public void updateCurrentClient(String id, String name, String age, String email) {
		try {
			fidelization.updateCurrentClientWithId(id, name, age, email);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//NOT TESTED 
	public void deleteCurrentClient(String id, double requiredQuantity) {
		try {
			inventory.updateWeight(id, requiredQuantity);
			cashRegister.createInVoice(id, requiredQuantity);
		} catch (noMatchesException e) {
			e.printStackTrace();
		} catch (insufficientQuantityException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public void createInVoice(String id, double requiredQuantity) {
		
	}
	//------------------------------------------> SISTEMAS DE CARGA
			//POR VERFICIAR:
			//---->CHECK! writeManagers()
	public String loadManagers() {	
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
	public void saveManagers() {
		try{
			File file = new File(FLATWORKERS);
			FileWriter filwri =  new FileWriter(file);
			BufferedWriter  buffer = new BufferedWriter(filwri);
			for(int i = 0; i < workers.size();i++) {
				if(workers.get(i) instanceof Manager) {
					Manager manager = (Manager) workers.get(i);
					buffer.write(manager.getName()+","+manager.getId()+","+manager.getEps()+","+manager.getSalary()+","+manager.getExperience()+","+manager.getContract());
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
	
	
	

	//------------------------------------------> SISTEMAS DE CARGA
		//POR VERFICIAR:
		//---->CHECK! writeFlatRealStates()
	public String loadRealStates() {	
		String msg = "DATOS CARGADOS CON EXITO!!!";
		try {
			File	file = new File(FLATREALSTATES);
			FileReader	frReader = new FileReader(file);
			BufferedReader	bufferRead = new BufferedReader(frReader);
			String saveString;
			while( (saveString = bufferRead.readLine()) != null){
				String[] parts = saveString.split(","); 
				String quantity = parts[0]; 
				String buyYear = parts[1];
				String name = parts[2];
				String id = parts[3];
				String maintenance = parts[4];
				if(maintenance != null) {
					createNewPublicState( quantity,  buyYear,  name,  id, maintenance );
				} else {
					createNewPrivateState( quantity,  buyYear,  name,  id);
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
	public void saveRealStates() {
		try{
			File file = new File(FLATREALSTATES);
			FileWriter filwri =  new FileWriter(file);
			BufferedWriter  buffer = new BufferedWriter(filwri);
			for(int i = 0; i < realStates.size();i++) {
				if(realStates.get(i) instanceof PublicState) {
					PublicState publicState = (PublicState) realStates.get(i);
					buffer.write(publicState.getQuantity()+","+publicState.getBuyYear()+","+publicState.getName()+","+publicState.getId()+","+publicState.getMaintenance());
				} else {
					PrivateState privateState = (PrivateState)realStates.get(i);
					buffer.write(privateState.getQuantity()+","+privateState.getBuyYear()+","+privateState.getName()+","+privateState.getId());
				}
			}
			buffer.close();
			filwri.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//----------------------------------->SISTEMAS DE SERIALIZACION INVENTORY
	public void saveInventory()  {
		try {
			File fl = new File(SERIALIZEINVENTORY);
			ObjectOutputStream duct = new ObjectOutputStream(new FileOutputStream(fl));
			duct.writeObject(inventory);
			duct.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadInventory(){
		File file = new File(SERIALIZEINVENTORY);
		Inventory temporalInventory;
		try {
				FileInputStream	fi = new FileInputStream(file);
				ObjectInputStream co = new ObjectInputStream(fi);
				temporalInventory = (Inventory) co.readObject();
				inventory = temporalInventory;
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
	//----------------------------------->SISTEMAS DE SERIALIZACION FIDELIZATION
	public void saveFidelization()  {
		try {
			File fl = new File(SERIALIZEFIDELIZATION);
			ObjectOutputStream duct = new ObjectOutputStream(new FileOutputStream(fl));
			duct.writeObject(fidelization);
			duct.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void loadFidelization(){
		File file = new File(SERIALIZEFIDELIZATION);
		Fidelization temporalFidelization;
		try {
				FileInputStream	fi = new FileInputStream(file);
				ObjectInputStream co = new ObjectInputStream(fi);
				temporalFidelization = (Fidelization) co.readObject();
				fidelization = temporalFidelization;
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
	//----------------------------------->SISTEMAS DE SERIALIZACION CASHREGISTER
	public void saveCashRegister()  {
		try {
			File fl = new File(SERIALIZECASHREGISTER);
			ObjectOutputStream duct = new ObjectOutputStream(new FileOutputStream(fl));
			duct.writeObject(cashRegister);
			duct.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}	
	public void loadCashRegisater(){
		File file = new File(SERIALIZECASHREGISTER);
		CashRegister temporalCashRegister;
		try {
				FileInputStream	fi = new FileInputStream(file);
				ObjectInputStream co = new ObjectInputStream(fi);
				temporalCashRegister = (CashRegister) co.readObject();
				cashRegister = temporalCashRegister; 
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
	//SISTEMA GENERAL DE CARGA DE DATOS <-------------------------------------------------------------------------------------------------------------------
	public void loadEverythig() {
		loadCashRegisater();
		loadFidelization();
		loadInventory();
		loadRealStates();
		loadManagers();
	}
	//SISTEMA GENERAL DE GUARDADO DE DATOS <----------------------------------------------------------------------------------------------------------------
	public void saveEverything() {
		saveCashRegister();
		saveFidelization();
		saveInventory();
		saveRealStates();
		saveManagers();
	}
}
