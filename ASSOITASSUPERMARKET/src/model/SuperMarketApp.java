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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SuperMarketApp {
	private Inventory inventory; // ESTO SE SERIALIZA
	private Fidelization fidelization; // ESTO SE SERIALIZA
	private CashRegister cashRegister; // ESTO SE SERIALIZA
	private ArrayList<Worker> workers;
	private ArrayList<Realstate> realStates;
	private MediaPlayer mediaPlayer;
	public static String FLATWORKERS = "marketRecords//worker.txt";
	public static String FLATPUBLICSTATES = "marketRecords//realStates.txt";
	public static String FLATPRIVATESTATES = "marketRecords//privateStates.txt";
	public static String SERIALIZEINVENTORY = "./marketRecords/inventory.dat";
	public static String SERIALIZEFIDELIZATION = "./marketRecords/fidelization.dat";
	public static String SERIALIZECASHREGISTER = "./marketRecords/cashRegister.dat";

	public SuperMarketApp() { 
		workers = new ArrayList<Worker>();
		realStates = new ArrayList<Realstate>();
		inventory = new Inventory();
		fidelization = new Fidelization();
		cashRegister = new CashRegister(/*this*/);
		//loadEverythig();
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

	// -----------------------> Workers
	private void validateAvailabilityOfTheId(String id) throws unavaiableIdException {
		for (int i = 0; i < workers.size(); i++) {
			if (workers.get(i).getId().equals(id)) {
				throw new unavaiableIdException("Error!");
			}
		}
	}

	// TESTED!
	public void createNewManager(String name, String id, String eps, String salary, String experience,
			String contract) throws unavaiableIdException {
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
			throw new unavaiableIdException("Error");
		} catch (NumberFormatException e) {
			
		}
		//return msg;
	}

	// TESTED!
	public String createNewAdministrator(String name, String id, String eps, String salary, String experience) throws unavaiableIdException {
		String msg = "";
		int salaryC = Integer.parseInt(salary);
		int experienceC = Integer.parseInt(experience);
		try {
			validateAvailabilityOfTheId(id);
			Worker administrator = new Administrator(name, id, eps, salaryC, experienceC);
			workers.add(administrator);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
			throw new unavaiableIdException("Error");
		} catch (NumberFormatException e) {
			
		}
		return msg;
	}

	// TESTED!
	public String deleteWorker(String id) {
		String msg = "";
		for (int i = 0; i < workers.size(); i++) {
			if (workers.get(i).getId().equals(id)) {
				msg = "El Trabajador eliminado fue:" + " " + workers.get(i).toString();
				workers.remove(i);
			} else {
				msg = "El Trabajador con id " + id + " no existe";
			}
		}
		return msg;
	}

	// --------------------------> Realstates
	private void validateAvailabilityOfTheIdRealstate(String id) throws unavaiableIdException {
		for (int i = 0; i < realStates.size(); i++) {
			if (realStates.get(i).getId().equals(id)) {
				throw new unavaiableIdException("Error!");
			}
		}
	}

	// TESTED!
	public void createNewPrivateState(String quantity, String buyYear, String name, String id) throws unavaiableIdException {
		String msg = "";
		int quantityC = Integer.parseInt(quantity);
		try {
			validateAvailabilityOfTheIdRealstate(id);
			PrivateState privateState = new PrivateState(quantityC, buyYear, name, id);
			realStates.add(privateState);
		} catch (unavaiableIdException e) {
			msg = "El id " + id + " ya se encuentra usado";
			throw new unavaiableIdException("Error");
		} catch (NumberFormatException e) {
			
		}
		//return msg;
	}

	// TESTED!
	public void createNewPublicState(String quantity, String buyYear, String name, String id, String maintenance) throws unavaiableIdException {
		String msg = "Aniadido Exitosamente";
		int quantityC = Integer.parseInt(quantity);
		try {
			validateAvailabilityOfTheIdRealstate(id);
			PublicState publicState = new PublicState(quantityC, buyYear, name, id, maintenance);
			realStates.add(publicState);
		} catch (unavaiableIdException e) {
			//msg = "El id " + id + " ya se encuentra usado";
			throw new unavaiableIdException("Error");
		} catch (NumberFormatException e) {
			
		}
		//return msg;
	}

	// TESTED!
	public String deleteRealState(String id) {
		String msg = "";
		for (int i = 0; i < realStates.size(); i++) {
			if (realStates.get(i).getId().equals(id)) {
				msg = "El Inmobiliario eliminado fue:" + " " + realStates.get(i).toString();
				realStates.remove(i);
			} else {
				msg = "El Inmobiliario con id " + id + " no existe";
			}
		}
		return msg;
	}

	// ------------------------------------------>INVENTORY
	// UNITY<----------------------------------------------------------------------------------------------------------------------------------------------------
	// NOT TESTED!!!!
	public void addUnityProduct(String id, String name, String bestBefore, String price, String productType,
			String quantity) throws unavaiableIdException {
		double priceC = Double.parseDouble(price);
		int quantityC = Integer.parseInt(quantity);
		try {
			inventory.addUnityProductToTheList(id, name, bestBefore, priceC, productType, quantityC);
		} catch (unavaiableIdException e) {
			throw new unavaiableIdException("Fail");
		}
	}

	// NOT TESTED!!!!
	public UnityProduct searchUnityProduct(String id) {
		UnityProduct unity = null;
		try {
			unity = inventory.searchUnityProduct(id);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unity;
	}

	// NOT TESTED!!!!
	public void updateUnityProduct(String id, String name, String bestBefore, String price, String productType,
			String quantity) {
		double priceC = Double.parseDouble(price);
		int quantityC = Integer.parseInt(quantity);
		try {
			inventory.updateUnityProductData(id, name, bestBefore, priceC, productType, quantityC);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// NOT TESTED!!!!
	public void deleteUnityProduct(String id) throws noMatchesException {
		try {
			inventory.deleteUnityProduct(id);
		} catch (noMatchesException e) {
			throw new noMatchesException("Error");
		}
	}

	// WEIGHT<---------------------------------------------------------------------------------------------------------------------------------------------------
	// NOT TESTED!!!!
	public void addWeightProduct(String id, String name, String bestBefore, String price, String productType,
			String weight) throws unavaiableIdException {
		Double priceC = Double.parseDouble(price);
		Double weightC = Double.parseDouble(weight);
		try {
			inventory.addWeightProductToTheList(id, name, bestBefore, priceC, productType, weightC);
		} catch (unavaiableIdException e) {
			throw new unavaiableIdException("Fail");
		}
	}

	// NOT TESTED!!!!
	public WeightProduct searchWeightProduct(String id) {
		WeightProduct weight = null;
		try {
			weight = inventory.searchWeightProduct(id);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weight;
	}

	// NOT TESTED!!!!
	public void updateWeightProduct(String id, String name, String bestBefore, String price, String productType,
			String weight) {
		Double priceC = Double.parseDouble(price);
		Double weightC = Double.parseDouble(weight);
		try {
			inventory.updateWeightProductData(id, name, bestBefore, priceC, productType, weightC);
		} catch (noMatchesException e) {
			
		} catch (NumberFormatException e) {
			
		}
	}

	// NOT TESTED!!!!
	public void deleteWeightProduct(String id) throws noMatchesException {
		try {
			inventory.deleteWeightProduct(id);
		} catch (noMatchesException e) {
			throw new noMatchesException("Error!");
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------->FIDELIZATION
	// TESTED!!!!
	public void addLoyalClient(String id, String name, String age, String email, String points, String discountPercent,
			String dueCard) throws repeatedCustomerException {
		int pointsC = Integer.parseInt(points);
		double discountPercentC = Double.parseDouble(discountPercent);
		try {
			fidelization.insertLoyalClient(id, name, age, email, pointsC, discountPercentC, dueCard);
		} catch (repeatedCustomerException e) {
			throw new repeatedCustomerException("Error");
		}
	}

	// TESTED!!!!
	public String searchLoyalClient(String id) {
		String client = null;
		try {
			client = fidelization.searchLoyalClientWithId(id).toString();
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}

	// TESTED!!!!
	public void updateLoyalClient(String id, String name, String age, String email, String points,
			String discountPercent, String dueCard) {
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
		} catch (NumberFormatException e) {
			
		}
	}
	// CURRENT<------------------------------------------------------------------------------------------------------------------------------------------------------
	// TESTED!!!!
	public void addCurrentClient(String id, String name, String age, String email) throws repeatedCustomerException {
		try {
			fidelization.insertCurrentClient(id, name, age, email);
		} catch (repeatedCustomerException e) {
			throw new repeatedCustomerException("Error");
		}
	}
	// TESTED!!!!
	public String searchCurrentClient(String id) {
		String msg = "";
		try {
			 msg = fidelization.searchCurrentClientWithId(id).toString();
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	// TESTED!!!!
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

	// NOT TESTED
	public void deleteCurrentClient(String id, double requiredQuantity) {
		/*
		  try { inventory.updateWeight(id, requiredQuantity);
		  cashRegister.createInVoice(id, requiredQuantity); } catch (noMatchesException
		  e) { e.printStackTrace(); } catch (insufficientQuantityException e) {
		  e.printStackTrace(); }
		*/
	}

	public WeightProduct updateQuantityWeight(String id, double requiredQuantity) {
		WeightProduct productW = null;
		try {
			productW = inventory.updateWeight(id, requiredQuantity);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (insufficientQuantityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productW;
	}

	public UnityProduct updateQuantityUnity(String id, int requiredQuantity) {
		UnityProduct productU = null;
		try {
			productU = inventory.updateUnity(id, requiredQuantity);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (insufficientQuantityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productU;
	}

	// ------------------------------------------>CASH REGISTER
	public void createInvoice(String productId, String requiredQuantity, String date, String paymentType,
		String factureNumber, String clientId) throws noMatchesException, insufficientQuantityException {
		double requiredQuantityC = Double.parseDouble(requiredQuantity);
		ArrayList<Product> products = new ArrayList<Product>();
		if (searchGeneralProducts(productId) instanceof WeightProduct) {
			try {
				inventory.updateWeight(productId, requiredQuantityC);
				WeightProduct tmpWeight = (WeightProduct) searchGeneralProducts(productId);
				tmpWeight.setWeight(requiredQuantityC);
				products.add(tmpWeight);
			} catch (noMatchesException e) {
				throw new noMatchesException("");
			} catch (insufficientQuantityException e) {
				throw new insufficientQuantityException("");
			}
		} else {
			int requiredQuantityC2 = (int) requiredQuantityC;
			try {
				inventory.updateUnity(productId, requiredQuantityC2);
				UnityProduct tmpUnity = (UnityProduct) searchGeneralProducts(productId);
				tmpUnity.setQuantity(requiredQuantityC2);
				products.add(tmpUnity);
			} catch (noMatchesException e) {
				throw new noMatchesException("");
			} catch (insufficientQuantityException e) {
				throw new insufficientQuantityException("");
			}
		}
		Client client = searchGeneralClient(clientId);
		try {
			cashRegister.addInvoiceProductToTheList(date, paymentType, factureNumber, products, client);
		} catch (unavaiableIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addMoreProductsToTheInvoice(String invoiceNumber, String productId, double requiredQuantity) throws noMatchesException {
		 CommercialInvoice invoice = null;
		try {
			invoice = cashRegister.searchInvoice(invoiceNumber);
		} catch (noMatchesException e) {
			e.printStackTrace();
		}
		 Product product = searchGeneralProducts(productId);
		 if(product instanceof WeightProduct) {
				updateQuantityWeight(productId, requiredQuantity);
				WeightProduct tmpWeight = (WeightProduct) product;
				tmpWeight.setWeight(requiredQuantity);
				invoice.getProducts().add(tmpWeight);
			} else {
				int requiredQuantityC = (int) requiredQuantity;
				updateQuantityUnity(productId, requiredQuantityC);
				UnityProduct tmpUnity = (UnityProduct) product;
				tmpUnity.setQuantity(requiredQuantityC);
				invoice.getProducts().add(tmpUnity);
			}
	}
	public void updateInvoiceInformation(String date, String paymentType, String factureNumber) {
		try {
			/*CommercialInvoice commercial = */cashRegister.updateInvoiceData(date, paymentType, factureNumber);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public String printInvoice(String facturenumber) {
		String msg = "";
		try {
			msg = cashRegister.printCommercialInvoice(facturenumber);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	// ------------------------------------------>CODIGO GENERICO
	//TESTED!
	public Client searchGeneralClient(String id) throws noMatchesException {
		Client client = null;
		boolean validator = false;
		try {
			client = fidelization.searchLoyalClientWithId(id);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (noMatchesException e) {
			validator = true;
		}
		if (validator == true) {
			try {
				client = fidelization.searchCurrentClientWithId(id);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (noMatchesException e) {
				throw new noMatchesException("Info");
			}
		}
		return client;
	}
	//TESTED!
	public Product searchGeneralProducts(String id) throws noMatchesException {
		Product product = null;
		boolean validator = false;
		try {
			product = inventory.searchUnityProduct(id);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (noMatchesException e) {
			validator = true;
		}
		if (validator == true) {
			try {
				product = inventory.searchWeightProduct(id);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (noMatchesException e) {
				throw new noMatchesException("Info");
			}
		}
		return product;
	}
	
	// ------------------------------------------> SISTEMAS DE CARGA
	// POR VERFICIAR:
	// ---->CHECK! writeManagers()
	public String loadManagers() {
		String msg = "DATOS CARGADOS CON EXITO!!!";
		try {
			File file = new File(FLATWORKERS);
			FileReader frReader = new FileReader(file);
			BufferedReader bufferRead = new BufferedReader(frReader);
			String saveString;
			while ((saveString = bufferRead.readLine()) != null) {
				String[] parts = saveString.split(",");
				String name = parts[0];
				String id = parts[1];
				String eps = parts[2];
				String salary = parts[3];
				String experience = parts[4];
				String contract = parts[5];
				if (contract != null) {
					createNewManager(name, id, eps, salary, experience, contract);
				} else {
					createNewAdministrator(name, id, eps, salary, experience);
				}

			}
			bufferRead.close();
			frReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public void saveManagers() {
		try {
			File file = new File(FLATWORKERS);
			FileWriter filwri = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(filwri);
			for (int i = 0; i < workers.size(); i++) {
				if (workers.get(i) instanceof Manager) {
					Manager manager = (Manager) workers.get(i);
					buffer.write(manager.getName() + "," + manager.getId() + "," + manager.getEps() + ","
							+ manager.getSalary() + "," + manager.getExperience() + "," + manager.getContract());
				} else {
					Administrator administrator = (Administrator) workers.get(i);
					buffer.write(administrator.getName() + "," + administrator.getId() + "," + administrator.getEps()
							+ "," + administrator.getSalary() + "," + administrator.getExperience());
				}
			}
			buffer.close();
			filwri.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void playCrazyFrog() {	
		String path = "pictures//amigosTraiganCerveza.mp3";
		Media media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.5);
		mediaPlayer.play();
	}

	// ------------------------------------------> SISTEMAS DE CARGA
	// POR VERFICIAR:
	// ---->CHECK! writeFlatRealStates()
	public String loadRealPublic() {
		String msg = "DATOS CARGADOS CON EXITO!!!";
		try {
			File file = new File(FLATPUBLICSTATES);
			FileReader frReader = new FileReader(file);
			BufferedReader bufferRead = new BufferedReader(frReader);
			String saveString;
			while ((saveString = bufferRead.readLine()) != null) {
				String[] parts = saveString.split(",");
				String quantity = parts[0];
				String buyYear = parts[1];
				String name = parts[2];
				String id = parts[3];
				String maintenance = parts[4];
				createNewPublicState(quantity, buyYear, name, id, maintenance);
			}
			bufferRead.close();
			frReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	public void saveRealStatesPublic() {
		try {
			File file = new File(FLATPUBLICSTATES);
			FileWriter filwri = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(filwri);
			for (int i = 0; i < realStates.size(); i++) {
				if(realStates.get(i) instanceof PublicState) {
					PublicState publicState = (PublicState) realStates.get(i);
					buffer.write(publicState.getQuantity() + "," + publicState.getBuyYear() + ","
							+ publicState.getName() + "," + publicState.getId() + "," + publicState.getMaintenance()+"\n");
				}		
			}
			buffer.close();
			filwri.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public String loadRealPrivate() {
		String msg = "DATOS CARGADOS CON EXITO!!!";
		try {
			File file = new File(FLATPRIVATESTATES);
			FileReader frReader = new FileReader(file);
			BufferedReader bufferRead = new BufferedReader(frReader);
			String saveString;
			while ((saveString = bufferRead.readLine()) != null) {
				String[] parts = saveString.split(",");
				String quantity = parts[0];
				String buyYear = parts[1];
				String name = parts[2];
				String id = parts[3];
				createNewPrivateState(quantity, buyYear, name, id);
			}
			bufferRead.close();
			frReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	public void saveRealStatesPrivate() {
		try {
			File file = new File(FLATPRIVATESTATES);
			FileWriter filwri = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(filwri);
			for (int i = 0; i < realStates.size(); i++) {
				if(realStates.get(i) instanceof PrivateState) {}
				PrivateState privateState = (PrivateState) realStates.get(i);
					buffer.write(privateState.getQuantity() + "," + privateState.getBuyYear() + ","
							+ privateState.getName() + "," + privateState.getId() +"\n");
			}
			buffer.close();
			filwri.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// ----------------------------------->SISTEMAS DE SERIALIZACION INVENTORY
	public void saveInventory() {
		try {
			File fl = new File(SERIALIZEINVENTORY);
			ObjectOutputStream duct = new ObjectOutputStream(new FileOutputStream(fl));
			duct.writeObject(inventory);
			duct.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadInventory() {
		File file = new File(SERIALIZEINVENTORY);
		Inventory temporalInventory;
		try {
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream co = new ObjectInputStream(fi);
			temporalInventory = (Inventory) co.readObject();
			inventory = temporalInventory;
			co.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------->SISTEMAS DE SERIALIZACION FIDELIZATION
	public void saveFidelization() {
		try {
			File fl = new File(SERIALIZEFIDELIZATION);
			ObjectOutputStream duct = new ObjectOutputStream(new FileOutputStream(fl));
			duct.writeObject(fidelization);
			duct.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFidelization() {
		File file = new File(SERIALIZEFIDELIZATION);
		Fidelization temporalFidelization;
		try {
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream co = new ObjectInputStream(fi);
			temporalFidelization = (Fidelization) co.readObject();
			fidelization = temporalFidelization;
			co.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------->SISTEMAS DE SERIALIZACION CASHREGISTER
	public void saveCashRegister() {
		try {
			File fl = new File(SERIALIZECASHREGISTER);
			ObjectOutputStream duct = new ObjectOutputStream(new FileOutputStream(fl));
			duct.writeObject(cashRegister);
			duct.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadCashRegisater() {
		File file = new File(SERIALIZECASHREGISTER);
		CashRegister temporalCashRegister;
		try {
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream co = new ObjectInputStream(fi);
			temporalCashRegister = (CashRegister) co.readObject();
			cashRegister = temporalCashRegister;
			co.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// SISTEMA GENERAL DE CARGA DE DATOS
	// <-------------------------------------------------------------------------------------------------------------------
	public void loadEverythig() {
		loadCashRegisater();
		loadFidelization();
		loadInventory();
		loadRealPublic();
		loadRealPrivate();
		loadManagers();
	} 

	// SISTEMA GENERAL DE GUARDADO DE DATOS
	// <----------------------------------------------------------------------------------------------------------------
	public void saveEverything() {
		saveCashRegister();
		saveFidelization();
		saveInventory();
		saveRealStatesPublic();
		saveRealStatesPrivate();
		saveManagers();
	}
}
