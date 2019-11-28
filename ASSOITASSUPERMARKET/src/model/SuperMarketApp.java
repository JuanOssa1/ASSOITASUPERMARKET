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
	/**
	 * Description: Permite obtener los valores de los atributos del objeto de tipo superMarket
	 * gets()*
	 * @return
	 */
	public Inventory getInventory() {
		return inventory;
	}
	/**
	 * Description: Permite actualizar cada uno de los atriubutos de un objeto tipo supermarket
	 * sets()*
	 * @param inventory
	 */
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
	/**
	 * Description: Valida si la in de un trabajador existe
	 * @param id
	 * @throws unavaiableIdException
	 */
	// -----------------------> Workers
	private void validateAvailabilityOfTheId(String id) throws unavaiableIdException {
		for (int i = 0; i < workers.size(); i++) {
			if (workers.get(i).getId().equals(id)) {
				throw new unavaiableIdException("Error!");
			}
		}
	}

	// TESTED!
	/**
	 * Description: Permine crear un nuevo trabajador de tipo gerente
	 * params*: Cada uno de los valores que quiero asignar a los atributos
	 * @param name
	 * @param id
	 * @param eps
	 * @param salary
	 * @param experience
	 * @param contract
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite crear un nuevo trajador de tipo administrador
	 * params*: Cada uno de los valores que quiero asignar a los atributos del objeto de tipo administrador
	 * @param name
	 * @param id
	 * @param eps
	 * @param salary
	 * @param experience
	 * @return
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite eliminar un trabajador dando su id
	 * @param Id ID del trabajador a eliminar
	 */
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
	/**
	 * Description: Valida si una id ya esta usada o no
	 * @param id Id que quiero comprobar
	 * @throws unavaiableIdException
	 */
	private void validateAvailabilityOfTheIdRealstate(String id) throws unavaiableIdException {
		for (int i = 0; i < realStates.size(); i++) {
			if (realStates.get(i).getId().equals(id)) {
				throw new unavaiableIdException("Error!");
			}
		}
	}

	// TESTED!
	
	/**
	 * Description: Permite crear inmobiliario de tipo privado
	 * params*: Valores que le quiero asignar a cada uno de los atributos del objeto administrador
	 * @param quantity
	 * @param buyYear
	 * @param name
	 * @param id
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite crear un objeto de tipo inmbiliario publico
	 * params*: Valor que le quiero asignar a cada uno de los atributos del objeto de inmobiliario publico
	 * @param quantity
	 * @param buyYear
	 * @param name
	 * @param id
	 * @param maintenance
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite eliminar un inmobiliario con la id asignada previamente
	 * @param id Id del producto que quiero eliminar
	 * @return
	 */
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
	/**
	 * Description: Permite agregar un producto de tipo unidad a la lsta de productos de tipo unidad
	 * params* Valores que le quiero asignar a cada uno de los atributos del objeto tipo producto unidad
	 * @param id
	 * @param name
	 * @param bestBefore
	 * @param price
	 * @param productType
	 * @param quantity
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite buscar un producto de tipo unidad con su id
	 * @param id ID del producto que quiero buscar
	 * @return
	 */
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
	/**
	 * Description: Permite actualizar el valor de los atributos de un objeto de tipo unity Product
	 * params*: Cada uno de los valores de los atributos que quiero reasignar a un objeto
	 * @param id: Id producto por unidad al cual le voy a actualizar los atrbutos
	 * @param name
	 * @param bestBefore
	 * @param price
	 * @param productType
	 * @param quantity
	 */
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
	/**
	 * Description: Permite eliminar producto por unidad
	 * @param id del producto que quiero
	 * @throws noMatchesException
	 */
	public void deleteUnityProduct(String id) throws noMatchesException {
		try {
			inventory.deleteUnityProduct(id);
		} catch (noMatchesException e) {
			throw new noMatchesException("Error");
		}
	}
	
	// WEIGHT<---------------------------------------------------------------------------------------------------------------------------------------------------
	// NOT TESTED!!!!
	/**
	 * Description: Permite un crear un producto de tipo peso y aniadirlo a la lista
	 * 
	 * @param id ID para buscar el producto a actualilzar
	 * params*: Valores de los atributos que quiero reescribir
	 * @param name
	 * @param bestBefore
	 * @param price
	 * @param productType
	 * @param weight
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite buscarun producto de tipo weightProduct
	 * @param id ID del producto tipo peso que se quiere buscar
	 * @return el producto buscado de lo contrario excepcion
	 */
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
	/**
	 * Description: Permite actualizar un producto de tipo peso
	 * @param id ID del producto que se quiere actualizar
	 * params*: Valores que quiero reasignar a los atributos del objeto tipo peso
	 * @param name
	 * @param bestBefore
	 * @param price
	 * @param productType
	 * @param weight
	 */
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
	/**
	 * Description: Permite elimiar unproducto de tipo peso de la lista
	 * @param id ID del productoa eliminar
	 * @throws noMatchesException
	 */
	public void deleteWeightProduct(String id) throws noMatchesException {
		try {
			inventory.deleteWeightProduct(id);
		} catch (noMatchesException e) {
			throw new noMatchesException("Error!");
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------->FIDELIZATION
	// TESTED!!!!
	/**
	 * Description: Permite agregar un cliente al arbol de clientes leales
	 * params*: Valores con los cuales se creara el objeto de tipo loyalClient
	 * @param id
	 * @param name
	 * @param age
	 * @param email
	 * @param points
	 * @param discountPercent
	 * @param dueCard
	 * @throws repeatedCustomerException
	 */
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
	/**
	 * Description: Permite buscar un cliente de tipo leal
	 * @param id Id que le corresponde a cliente leal que quiero buscar
	 * @return
	 */
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
	/**
	 * Description: Permite actualizar los atributos de un cliente leal 
	 * @param id ID del cliente que quiero actualizar
	 * params*: Valores que quiero reasignar a los atributos del objeto buscado
	 * @param name
	 * @param age
	 * @param email
	 * @param points
	 * @param discountPercent
	 * @param dueCard
	 */
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
	/**
	 * Description: Permite actualizar los valores de los atributos del cliente corrente
	 * @param id ID del cliente que quiero actualizar
	 * params*: Valores de los atributos que quiero reasignar
	 * @param name
	 * @param age
	 * @param email
	 * @throws repeatedCustomerException
	 */
	public void addCurrentClient(String id, String name, String age, String email) throws repeatedCustomerException {
		try {
			fidelization.insertCurrentClient(id, name, age, email);
		} catch (repeatedCustomerException e) {
			throw new repeatedCustomerException("Error");
		}
	}
	// TESTED!!!!
	/**
	 * Description: Permite buscar un cliente corriente con su id
	 * @param id Id Del cliente corriente que quiero encontrar
	 * @return String con los atributos de ese cliente corrente
	 */
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
	/**
	 * Description: Permite actualizar un cliente corriente
	 * @param id ID del cliente corriente que se va actualizar 
	 * params*: Valores que quiero actualizar al cliente corriente
	 * @param name
	 * @param age
	 * @param email
	 */
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
	/**
	 * Description: Permite actualizar el peso que hay en el inventario el producto de tipo peso 
	 * @param id ID producto que quiero actualizar
	 * @param requiredQuantity Cantidad que el usuario va a comprar
	 * @return retorna el producto de tipo peso
	 */
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
	/**
	 * Description:Permite actualizar la cantidad de un producto que hy en el inventario
	 * @param id
	 * @param requiredQuantity
	 * @return
	 */
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
	/**
	 * Description: Permite crear una factura y agregar productos por primera vez
	 * @param productId ID del producto que se quiere agregar a la factura
	 * @param requiredQuantity Cantidad requerida del producto
	 * @param date Fecha de la compra
	 * @param paymentType Medio de pago que uso en la compra
	 * @param factureNumber: Numero de la facutra que se le quiere asignar
	 * @param clientId : Id del cliente que hizo la compra
	 * @throws noMatchesException
	 * @throws insufficientQuantityException
	 * @throws NullPointerException
	 */
	public void createInvoice(String productId, String requiredQuantity, String date, String paymentType,
		String factureNumber, String clientId) throws noMatchesException, insufficientQuantityException, NullPointerException {
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
	/**
	 * Description: Permite agregar mas productos a una facutra que ya ha sido creada antes
	 * @param invoiceNumber Numero de la factura a la cual voy a aniadir los nuevos productos
	 * @param productId Id del producto que se quiere aniadir 
	 * @param requiredQuantity Cantidad requerida de producto solciada por el usuario
	 * @throws noMatchesException
	 * @throws insufficientQuantityException
	 */
	public void addMoreProductsToTheInvoice(String invoiceNumber, String productId, double requiredQuantity) throws noMatchesException, insufficientQuantityException {
		 CommercialInvoice invoice = null;
		try {
			invoice = cashRegister.searchInvoice(invoiceNumber);
		} catch (noMatchesException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new insufficientQuantityException("Error");
		}
		 Product product = searchGeneralProducts(productId);
		 if(product instanceof WeightProduct) {
				updateQuantityWeight(productId, requiredQuantity);
				WeightProduct tmpWeight = (WeightProduct) product;
				tmpWeight.setWeight(tmpWeight.getWeight()-requiredQuantity);
				invoice.getProducts().add(tmpWeight);
			} else {
				int requiredQuantityC = (int) requiredQuantity;
				updateQuantityUnity(productId, requiredQuantityC);
				UnityProduct tmpUnity = (UnityProduct) product;
				tmpUnity.setQuantity(tmpUnity.getQuantity()-requiredQuantityC);
				invoice.getProducts().add(tmpUnity);
			}
	}
	/**
	 * 
	 * @param date Nuava fecha que quiero reasignar 
	 * @param paymentType Nuevo tipp de pago que quiero reasginar
	 * @param factureNumber numero de la factura que quiero actualizar
	 */
	public void updateInvoiceInformation(String date, String paymentType, String factureNumber) {
		try {
			/*CommercialInvoice commercial = */cashRegister.updateInvoiceData(date, paymentType, factureNumber);
		} catch (noMatchesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	/**
	 * Description: Permite retornar un objeto de tipo String cin cada una de las propiedades de l factura
	 * @param facturenumber Numero de la factura que se quiere imprimir 
	 * @return Objeto de tipo string 
	 * @throws noMatchesException
	 */
	public String printInvoice(String facturenumber) throws noMatchesException {
		String msg = "";
		try {
			msg = cashRegister.printCommercialInvoice(facturenumber);
		} catch (noMatchesException e) {
			throw new noMatchesException("Failure");
		}
		return msg;
	}
	// ------------------------------------------>CODIGO GENERICO
	//TESTED!
	/**
	 * Description: Permite buscar y retornar un loyalclient o un current client con su id
	 * @param id Id del cliente que se quiere buscar
	 * @return objeto de tipo Client
	 * @throws noMatchesException
	 */
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
	/**
	 * Description: Permite buscar productos tanto de unidad como de peso
	 * @param id Id del producto que se quiere buscar
	 * @return Product
	 * @throws noMatchesException
	 */
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
	/**
	 * Descripttion: Lee de archivo plano de los gerentes y lo carga al arraylist de trabajadores
	 * @return
	 */
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
	/**
	 * Description: Permite guardar en el archivo plano la informacion que se encuentra en el arraylist de trabajadores
	 */
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
	

	// ------------------------------------------> SISTEMAS DE CARGA
	// POR VERFICIAR:
	// ---->CHECK! writeFlatRealStates()
	/**
	 * Descripttion: Lee de archivo plano del inmobiliario publico y lo carga al arraylist de inmobiliario
	 * @return
	 */
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
	/**
	 * Permite guardar la informacion que hay en el arryalist de inmobiliario publico y lo carga
	 */
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
	/**
	 * Description: Lee del archivo plano las linel y las carga al ArrayList
	 * @return
	 */
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
	/**
	 * Description: Permite guardar los inmmobiliarios privados en eun archivo plano
	 */
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
	/**
	 * Description: Permite serializar el inventario de productos
	 */
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
	/**
	 * Description: Permite deserializar el archivo de inventario
	 */
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
	/**
	 * Description: Permite seralizar el programa de fidelizacion
	 */
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
	/**
	 * Description: Permite deserializar el programa de fidelizaciones
	 */
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
	/**
	 * Descrption: Permite serializar la caja registradora
	 */
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
	/**
	 * Description: Permite deserializar la caja reistradoa
	 */
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
	/**
	 * Description: Llama a todos los metodos de carga
	 */
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
	/**
	 * Description: Permite llmar todos los metodos de carga
	 */
	public void saveEverything() {
		saveCashRegister();
		saveFidelization();
		saveInventory();
		saveRealStatesPublic();
		saveRealStatesPrivate();
		saveManagers();
	}
	
	//SISTEMAS DE ORDENAMIENTO WORKERS 
	/**
	 * Description: Permite ordenar el ArrayList  trabajdores por id
	 *
	 */
	public void organizeWithiDInsercion(){
		for (int i = 1; i<workers.size(); i++){
			for(int j = i; j>0 && workers.get(j-1).getId().compareTo(workers.get(j).getId()) > 0; j--){
				Worker tmp = workers.get(j);
				workers.set(j, workers.get(j-1));
				workers.set(j-1, tmp);	
			}	
		}	
	}
	/**
	 * Description: Permite ordenar el ArrayList de trabajadores 
	 */
	public void organizeWithName(){
		for (int i = 1; i<workers.size(); i++){
			for(int j = i; j>0 && workers.get(j-1).getName().compareTo(workers.get(j).getName()) > 0; j--){
				Worker tmp = workers.get(j);
				workers.set(j, workers.get(j-1));
				workers.set(j-1, tmp);	
			}	
		}	
	}
	//SISTEMAS DE ORDENAMIENTO REALSTATES
	/**
	 *Description: Permite ordenar el inmobiliario por burbuja con base al id
	 */
	public void organizeWRealStatesBurbuja() {
		Realstate tmp = null;
		Realstate tmp1 = null;
		for(int i = realStates.size(); i>0; i--) {
			for(int j = 0; j<-1-1; j++ ) {
				if(realStates.get(j).getId().compareTo(realStates.get(j+1).getId())>0) {
					tmp = realStates.get(j);
					tmp1 = realStates.get(j+1);
					realStates.set(j, tmp1);
					realStates.set(j+1, tmp);
				}
				else {
					realStates.set(j, tmp);
					realStates.set(j+1, tmp1);
				}
			}
		}
	}
	/**
	 * Description: Permite ordenar el inmobiliario por cantidad 
	 */
	public void organizeWRealStatesQuantity() {
		Realstate tmp = null;
		Realstate tmp1 = null;
		for(int i = realStates.size(); i>0; i--) {
			for(int j = 0; j<-1-1; j++ ) {
				if(realStates.get(j).getQuantity()>realStates.get(j+1).getQuantity()) {
					tmp = realStates.get(j);
					tmp1 = realStates.get(j+1);
					realStates.set(j, tmp1);
					realStates.set(j+1, tmp);
				}
				else {
					realStates.set(j, tmp);
					realStates.set(j+1, tmp1);
				}
			}
		}
	}
	/**
	 * Description: Permite buscar trajadores por busqueda binaria con base al ID
	 * @param valor Id del trabajador buscado
	 * @return
	 */
	public Worker searchWorkerBinary(String valor) {
        Worker aux = null;
        boolean resultado = false;
        int inicio = 0;
        int fin = workers.size() - 1;
        while (inicio <= fin && !resultado ) {
            int medio = (inicio + fin ) / 2 ;
            if (workers.get(medio).getId().compareTo(valor)==0 ) {
                aux = workers.get(medio);
            } else if (workers.get(medio).getId().compareToIgnoreCase(valor) > 0) {
                fin = medio -1 ;
            } else {
                inicio = medio + 1;
            }
        }

        return aux;
    }
}
