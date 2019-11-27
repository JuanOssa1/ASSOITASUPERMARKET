package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import exceptions.repeatedCustomerException;
import exceptions.unavaiableIdException;
import model.Administrator;
import model.Client;
import model.Manager;
import model.PrivateState;
import model.Product;
import model.PublicState;
import model.Realstate;
import model.SuperMarketApp;
import model.Worker;

class SuperMarketAppTest {
	private SuperMarketApp superMarket;
	
	private void setUpSceneTestAddition() {
		superMarket = new SuperMarketApp();
		superMarket.createNewAdministrator("Carlos", "12346", "Comeva","400000", "7");
		superMarket.createNewAdministrator("Juam", "12356", "Comeva","500000", "3");
		superMarket.createNewAdministrator("Victor", "123487", "Comeva","200000", "8");
		superMarket.createNewManager("Chino", "12334", "Colsanitas", "233344", "12", "1 año");
		superMarket.createNewManager("Juan", "123234", "Colsanitas", "1000000", "8", "1 año");
		superMarket.createNewManager("Samuel", "12332334", "Colsanitas", "263331", "15", "1 año");
	}
	private void setUpSceneSameId() {
		superMarket = new SuperMarketApp(); 
		superMarket.createNewAdministrator("Carlos", "1", "Comeva","400000", "7");
		superMarket.createNewAdministrator("Juam", "12", "Comeva","500000", "3");
		superMarket.createNewAdministrator("Victor", "123", "Comeva","200000", "8");
		superMarket.createNewManager("Chino", "1", "Colsanitas", "233344", "12", "1 año");
		superMarket.createNewManager("Juan", "1234", "Colsanitas", "1000000", "8", "1 año");
		superMarket.createNewManager("Samuel", "12345", "Colsanitas", "263331", "15", "1 año");
	}
	
	@Test
	void testAddition() {
		setUpSceneTestAddition();
		ArrayList<Worker> workers = superMarket.getWorkers();
		ArrayList<Worker> handMadeWorker = new ArrayList<Worker>();
		Administrator aTest0 = new Administrator("Carlos", "12346", "Comeva",400000, 7);
		Administrator aTest1 = new Administrator("Juam", "12356", "Comeva",500000, 3);
		Administrator aTest2 = new Administrator("Victor", "123487", "Comeva",200000, 8);
		Worker mTest0 = new Manager("Chino", "12334", "Colsanitas", 233344, 12, "1 año");
		Worker mTest1 = new Manager("Juan", "123234", "Colsanitas", 1000000, 8, "1 año");
		Worker mTest2 = new Manager("Samuel", "12332334", "Colsanitas", 263331, 15, "1 año");
		handMadeWorker.add(aTest0);
		handMadeWorker.add(aTest1);
		handMadeWorker.add(aTest2);
		handMadeWorker.add(mTest0);
		handMadeWorker.add(mTest1);
		handMadeWorker.add(mTest2);
		assertEquals(workers.toString(), handMadeWorker.toString());
	}
	@Test
	 void testAddSameId() {
		setUpSceneSameId();
		ArrayList<Worker> workers = superMarket.getWorkers();
		assertEquals("[Administrator [getName()=Carlos, getId()=1, getSalary()=400000, getExperience()=7, Administrator [getName()=Juam, getId()=12, getSalary()=500000, getExperience()=3, Administrator [getName()=Victor, getId()=123, getSalary()=200000, getExperience()=8, Manager [contract=1 año, getName()=Juan, getId()=1234, getSalary()=1000000, getExperience()=8, Manager [contract=1 año, getName()=Samuel, getId()=12345, getSalary()=263331, getExperience()=15]", workers.toString());
	}
	@Test
	 void testDelete() {
		setUpSceneTestAddition();
		superMarket.deleteWorker("123487");
		ArrayList<Worker> workers = superMarket.getWorkers();
		assertEquals("[Administrator [getName()=Carlos, getId()=12346, getSalary()=400000, getExperience()=7, Administrator [getName()=Juam, getId()=12356, getSalary()=500000, getExperience()=3, Manager [contract=1 año, getName()=Chino, getId()=12334, getSalary()=233344, getExperience()=12, Manager [contract=1 año, getName()=Juan, getId()=123234, getSalary()=1000000, getExperience()=8, Manager [contract=1 año, getName()=Samuel, getId()=12332334, getSalary()=263331, getExperience()=15]",workers.toString());
	}
	@Test
	 void testDeleteNoneExistentId() {
		setUpSceneTestAddition();
		superMarket.deleteWorker("9999");
		ArrayList<Worker> workers = superMarket.getWorkers();
		assertEquals("[Administrator [getName()=Carlos, getId()=12346, getSalary()=400000, getExperience()=7, Administrator [getName()=Juam, getId()=12356, getSalary()=500000, getExperience()=3, Administrator [getName()=Victor, getId()=123487, getSalary()=200000, getExperience()=8, Manager [contract=1 año, getName()=Chino, getId()=12334, getSalary()=233344, getExperience()=12, Manager [contract=1 año, getName()=Juan, getId()=123234, getSalary()=1000000, getExperience()=8, Manager [contract=1 año, getName()=Samuel, getId()=12332334, getSalary()=263331, getExperience()=15]",workers.toString());
	}
	private void setUpSceneAddRealStates() {
		superMarket = new SuperMarketApp();
		superMarket.createNewPublicState("200", "23/11/2019", "Carrito de Supermercado","10994","23/11/2020");
		superMarket.createNewPublicState("50", "23/11/2019", "Canasta de Supermercado", "10995","23/11/2020");
		superMarket.createNewPublicState("15", "23/11/2019", "Caja registradora", "10996","23/11/2020");
		superMarket.createNewPrivateState("5", "23/11/2019", "Carrito de cafe", "11994");
		superMarket.createNewPrivateState("7", "23/11/2019", "Sofas", "11995");
		superMarket.createNewPrivateState("8", "23/11/2019", "Camiones", "11996");
	}
	
	private void setUpSceneRealStateSameId() {
		superMarket = new SuperMarketApp();
		superMarket.createNewPublicState("200", "23/11/2019", "Carrito de Supermercado", "1","23/11/2020");
		superMarket.createNewPublicState("50", "23/11/2019", "Canasta de Supermercado", "12" ,"23/11/2020");
		superMarket.createNewPublicState("15", "23/11/2019", "Caja registradora","123","23/11/2020");
		superMarket.createNewPrivateState("5", "23/11/2019", "Carrito de cafe", "1234");
		superMarket.createNewPrivateState("7", "23/11/2019", "Sofas", "12345");
		superMarket.createNewPrivateState("8", "23/11/2019", "Camiones", "1");
	}
	
	@Test
	void testAdittionRealStates() {
		setUpSceneAddRealStates();
		ArrayList<Realstate> realStates = superMarket.getRealStates();
		ArrayList<Realstate> handMaderealState = new ArrayList<Realstate>();
		PublicState puTest0 = new PublicState(200, "23/11/2019", "Carrito de Supermercado", "10994" ,"23/11/2020");
		PublicState puTest1 = new PublicState(50, "23/11/2019", "Canasta de Supermercado", "10995","23/11/2020");
		PublicState puTest2 = new PublicState(15, "23/11/2019", "Caja registradora","10996","23/11/2020");
		PrivateState priTest0 = new PrivateState(5, "23/11/2019", "Carrito de cafe", "11994");
		PrivateState priTest1 = new PrivateState(7, "23/11/2019", "Sofas", "11995");
		PrivateState priTest2 = new PrivateState(8, "23/11/2019", "Camiones", "11996");
		handMaderealState.add(puTest0);
		handMaderealState.add(puTest1);
		handMaderealState.add(puTest2);
		handMaderealState.add(priTest0);
		handMaderealState.add(priTest1);
		handMaderealState.add(priTest2);
		assertEquals(realStates.toString(), handMaderealState.toString());
	}
	
	@Test
	 void testAddSameIdRealState() {
		setUpSceneRealStateSameId();
		ArrayList<Realstate> realStates = superMarket.getRealStates();
		assertEquals("[PublicState [maintenance=23/11/2020, getQuantity()=200, getBuyYear()=23/11/2019, getName()=Carrito de Supermercado, getId()=1], PublicState [maintenance=23/11/2020, getQuantity()=50, getBuyYear()=23/11/2019, getName()=Canasta de Supermercado, getId()=12], PublicState [maintenance=23/11/2020, getQuantity()=15, getBuyYear()=23/11/2019, getName()=Caja registradora, getId()=123], PrivateState [getQuantity()=5, getBuyYear()=23/11/2019, getName()=Carrito de cafe, getId()=1234], PrivateState [getQuantity()=7, getBuyYear()=23/11/2019, getName()=Sofas, getId()=12345]]",realStates.toString());
	}
	@Test
	void testDeleteRealState() {
		setUpSceneAddRealStates();
		superMarket.deleteRealState("10996");
		ArrayList<Realstate> realStates = superMarket.getRealStates();
		assertEquals("[PublicState [maintenance=23/11/2020, getQuantity()=200, getBuyYear()=23/11/2019, getName()=Carrito de Supermercado, getId()=10994], PublicState [maintenance=23/11/2020, getQuantity()=50, getBuyYear()=23/11/2019, getName()=Canasta de Supermercado, getId()=10995], PrivateState [getQuantity()=5, getBuyYear()=23/11/2019, getName()=Carrito de cafe, getId()=11994], PrivateState [getQuantity()=7, getBuyYear()=23/11/2019, getName()=Sofas, getId()=11995], PrivateState [getQuantity()=8, getBuyYear()=23/11/2019, getName()=Camiones, getId()=11996]]", realStates.toString());
	}
	@Test
	void testDeleteNoneExistendIdRealState() {
		setUpSceneAddRealStates();
		superMarket.deleteRealState("1");
		ArrayList<Realstate> realStates = superMarket.getRealStates();
		assertEquals("[PublicState [maintenance=23/11/2020, getQuantity()=200, getBuyYear()=23/11/2019, getName()=Carrito de Supermercado, getId()=10994], PublicState [maintenance=23/11/2020, getQuantity()=50, getBuyYear()=23/11/2019, getName()=Canasta de Supermercado, getId()=10995], PublicState [maintenance=23/11/2020, getQuantity()=15, getBuyYear()=23/11/2019, getName()=Caja registradora, getId()=10996], PrivateState [getQuantity()=5, getBuyYear()=23/11/2019, getName()=Carrito de cafe, getId()=11994], PrivateState [getQuantity()=7, getBuyYear()=23/11/2019, getName()=Sofas, getId()=11995], PrivateState [getQuantity()=8, getBuyYear()=23/11/2019, getName()=Camiones, getId()=11996]]", realStates.toString());
	}
	private void setUpSceneGeneralSearchClient() throws repeatedCustomerException {
		superMarket = new SuperMarketApp();
		superMarket.addLoyalClient("1", "Juam", "22", "juan2233", "2222", "0.2", "MasterCard");
		superMarket.addCurrentClient("2", "Juan", "22", "maza@eded");
	}
	@Test
	void testGeneralSearch() throws repeatedCustomerException {
		setUpSceneGeneralSearchClient();
		Client client0 = superMarket.searchGeneralClient("1");
		Client client1 = superMarket.searchGeneralClient("2");
		assertEquals("LoyalClient [points=2222, discountPercent=0.2, dueCard=MasterCard, getId()=1, getName()=Juam, getAge()=22, getEmail()=juan2233]", client0.toString());
		assertEquals("Client [id=2, name=Juan, age=22, email=maza@eded]",client1.toString());
	}
	private void setUpSceneGeneralSearchProduct() {
		superMarket = new SuperMarketApp();
		superMarket.addUnityProduct("1", "panela", "hoy", "120000", "dulce", "10");
		superMarket.addWeightProduct("2", "carne", "hoy", "12333", "proteina", "120000");
	}
	@Test
	void testGeneralSearchProduct() {
		setUpSceneGeneralSearchProduct();
		Product product0 = superMarket.searchGeneralProducts("1");
		Product product1 = superMarket.searchGeneralProducts("2");
		assertEquals("UnityProduct [quantity=10, getId()=1, getName()=panela, getBestBefore()=hoy, getPrice()=120000.0, getProductType()=dulce]", product0.toString());
		assertEquals("WeightProduct [weight=120000.0, getId()=2, getName()=carne, getBestBefore()=hoy, getPrice()=12333.0, getProductType()=proteina]", product1.toString());
	}
}
