package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.noMatchesException;
import exceptions.repeatedCustomerException;
import exceptions.thereAreNoRecordsException;
import model.CurrentClient;
import model.Fidelization;
import model.LoyalClient;

class FidelizationTest {
	private Fidelization fidelization;
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------->LOYAL
	public void setUpSceneRepeatedLoyalClient() throws repeatedCustomerException {
		fidelization = new Fidelization();
		fidelization.insertLoyalClient("1", "Juan", "18", "Carlitos@yahoo.com", 1233, 0.3, "1234");
		fidelization.insertLoyalClient("1", "Juan", "18", "Carlitos@yahoo.com", 1233, 0.3, "1234");
	}
	@Test
	void testInsertRepeatedLoyalClient() {
		try {
			setUpSceneRepeatedLoyalClient();
			fail("Se esperaba excepcion cliente repetido");
		} catch (repeatedCustomerException e) {	
			System.out.println("Pass testInsertRepeatedLoyalClient!");
		}
	}
	public void setUpSceneLoyalClient() throws repeatedCustomerException {
		fidelization = new Fidelization();
		fidelization.insertLoyalClient("1", "Juan", "18", "juan.ossa@hotmail.com", 1233, 0.3, "121");
		fidelization.insertLoyalClient("2", "Carlos", "14", "carlitos@yahoo.com", 1233, 0.3, "1222");
		fidelization.insertLoyalClient("3", "Pedro", "12", "pedro@gmail.com", 1233, 0.3, "123334");
		fidelization.insertLoyalClient("4", "Victor", "22", "victor@4chan.com", 1233, 0.3, "12444");
	}
	@Test
	void testInsertLoyalClient() {
		try {
			setUpSceneLoyalClient();
			LoyalClient clientInfo0 = fidelization.searchLoyalClientWithId("1");
			LoyalClient clientInfo1= fidelization.searchLoyalClientWithId("2");
			LoyalClient clientInfo2 = fidelization.searchLoyalClientWithId("3");
			LoyalClient clientInfo3 = fidelization.searchLoyalClientWithId("4");
			assertEquals("LoyalClient [points=1233, discountPercent=0.3, dueCard=121, getId()=1, getName()=Juan, getAge()=18, getEmail()=juan.ossa@hotmail.com]",clientInfo0.toString());
			assertEquals("LoyalClient [points=1233, discountPercent=0.3, dueCard=1222, getId()=2, getName()=Carlos, getAge()=14, getEmail()=carlitos@yahoo.com]",clientInfo1.toString());
			assertEquals("LoyalClient [points=1233, discountPercent=0.3, dueCard=123334, getId()=3, getName()=Pedro, getAge()=12, getEmail()=pedro@gmail.com]",clientInfo2.toString());
			assertEquals("LoyalClient [points=1233, discountPercent=0.3, dueCard=12444, getId()=4, getName()=Victor, getAge()=22, getEmail()=victor@4chan.com]",clientInfo3.toString());
		} catch (repeatedCustomerException | noMatchesException | NullPointerException e) {
			fail();
			e.printStackTrace();
		}
	}
	@Test
	void testNoMatchesException() {
		try {
			setUpSceneLoyalClient();
			fidelization.searchLoyalClientWithId("99");
		} catch (repeatedCustomerException e) {
			fail("Se esperaba excepcion sin coincidencias");
		} catch (noMatchesException e) {
			System.out.println("Pass testNoMatchesException!");
		} catch (NullPointerException e) {
			fail("Se esperaba excepcion sin coincidencias");
		}
	}
	@Test
	void testUpdateInfo() {
		try {
			setUpSceneLoyalClient();
			String infoBeforeUpdate = fidelization.searchLoyalClientWithId("2").toString();
			fidelization.updateLoyalClientWithId("2", "Alberto", "22", "carlosbakan@hotmail.com", 1234344, 0.1, "122343");
			String infoAfterUpdate = fidelization.searchLoyalClientWithId("2").toString();
			assertNotEquals(infoBeforeUpdate, infoAfterUpdate);
		} catch (repeatedCustomerException | noMatchesException | NullPointerException e) {
			fail();
			e.printStackTrace();
		}
	}
	@Test
	void testNoMatchesExceptionUpdateInfo(){
		try {
			setUpSceneLoyalClient();
			fidelization.updateLoyalClientWithId("2222", "Carlos", "33", "juanbdbs@jjuan.ciom", 999999, 0.01, "22334");
		} catch (repeatedCustomerException e) {
			fail("Se esperaba excepcion sin coincidencias");
		} catch (noMatchesException e) {
			System.out.println("Pass testNoMatchesExceptionUpdateInfo!");
		} catch (NullPointerException e) {
			fail("Se esperaba excepcion sin coincidencias");
		}
		
	}
	@Test
	void testThereAreNoRecordsExceptionForUpdate() {
		try {
			fidelization.updateLoyalClientWithId("2222", "Carlos", "33", "juanbdbs@jjuan.ciom", 999999, 0.01, "22334");
		} catch (noMatchesException e) {
			fail("Se esperaba excepcion sin registros");
		} catch (NullPointerException e) {
			System.out.println("Pass testThereAreNoRecordsExceptionForUpdate!");
		}
	}
	@Test
	void testThereAreNoRecordsExceptionForSearchInfo() {
		try {
			fidelization.searchLoyalClientWithId("22344");
		} catch (NullPointerException e) {
			System.out.println("Pass testThereAreNoRecordsExceptionForSearchInfo!");
			System.out.println();
		} catch (noMatchesException e) {
			fail("Se esperaba excepcion sin registros");
		}
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------>CURRENT
	public void setUpSceneRepeatedCurrentClient() throws repeatedCustomerException {
		fidelization = new Fidelization();
		fidelization.insertCurrentClient("1", "Juan", "18", "Carlitos@yahoo.com");
		fidelization.insertCurrentClient("1", "Juan", "18", "Carlitos@yahoo.com");
	}
	@Test
	void testInsertRepeatedCurrentClient() {
		try {
			setUpSceneRepeatedLoyalClient();
			fail("Se esperaba excepcion cliente repetido");
		} catch (repeatedCustomerException e) {	
			System.out.println("Pass testInsertRepeatedLoyalClientCurrent!");
		}
	}
	public void setUpSceneCurrentClient() throws repeatedCustomerException {
		fidelization = new Fidelization();
		fidelization.insertCurrentClient("1", "Juan", "18", "juan.ossa@hotmail.com");
		fidelization.insertCurrentClient("2", "Carlos", "14", "carlitos@yahoo.com");
		fidelization.insertCurrentClient("3", "Pedro", "12", "pedro@gmail.com");
		fidelization.insertCurrentClient("4", "Victor", "22", "victor@4chan.com");
	}
	@Test
	void testInsertCurrentClient() {
		try {
			setUpSceneCurrentClient();
			CurrentClient clientInfo0 = fidelization.searchCurrentClientWithId("1");
			CurrentClient clientInfo1= fidelization.searchCurrentClientWithId("2");
			CurrentClient clientInfo2 = fidelization.searchCurrentClientWithId("3");
			CurrentClient clientInfo3 = fidelization.searchCurrentClientWithId("4");
			assertEquals("Client [id=1, name=Juan, age=18, email=juan.ossa@hotmail.com]",clientInfo0.toString());
			assertEquals("Client [id=2, name=Carlos, age=14, email=carlitos@yahoo.com]",clientInfo1.toString());
			assertEquals("Client [id=3, name=Pedro, age=12, email=pedro@gmail.com]",clientInfo2.toString());
			assertEquals("Client [id=4, name=Victor, age=22, email=victor@4chan.com]",clientInfo3.toString());
		} catch (repeatedCustomerException | noMatchesException | NullPointerException e) {
			fail();
			e.printStackTrace();
		}
	}
	@Test
	void testNoMatchesExceptionSearchCurrent() {
		try {
			setUpSceneCurrentClient();
			fidelization.searchCurrentClientWithId("99");
		} catch (repeatedCustomerException e) {
			fail("Se esperaba excepcion sin coincidencias");
		} catch (noMatchesException e) {
			System.out.println("Pass testNoMatchesExceptionCurrent!");
		} catch (NullPointerException e) {
			fail("Se esperaba excepcion sin coincidencias");
		}
	}
	@Test
	void testUpdateInfoCurrent() {
		try {
			setUpSceneCurrentClient();
			String infoBeforeUpdate = fidelization.searchCurrentClientWithId("2").toString();
			fidelization.updateCurrentClientWithId("2", "Alberto", "22", "carlosbakan@hotmail.com");
			String infoAfterUpdate = fidelization.searchCurrentClientWithId("2").toString();
			assertNotEquals(infoBeforeUpdate, infoAfterUpdate);
		} catch (repeatedCustomerException | noMatchesException | NullPointerException e) {
			fail();
			e.printStackTrace();
		}
	}
	@Test
	void testNoMatchesExceptionUpdateInfoCurrent(){
		try {
			setUpSceneCurrentClient();
			fidelization.updateCurrentClientWithId("2222", "Carlos", "33", "juanbdbs@jjuan.ciom");
		} catch (repeatedCustomerException e) {
			fail("Se esperaba excepcion sin coincidencias");
		} catch (noMatchesException e) {
			System.out.println("Pass testNoMatchesExceptionUpdateInfoCurrent!");
		} catch (NullPointerException e) {
			fail("Se esperaba excepcion sin coincidencias");
		}
		
	}
	@Test
	void testThereAreNoRecordsExceptionForUpdateCurrent() {
		try {
			fidelization.updateCurrentClientWithId("2222", "Carlos", "33", "juanbdbs@jjuan.ciom");
		} catch (noMatchesException e) {
			fail("Se esperaba excepcion sin registros");
		} catch (NullPointerException e) {
			System.out.println("Pass testThereAreNoRecordsExceptionForUpdateCurrent!");
		}
	}
	@Test
	void testThereAreNoRecordsExceptionForSearchInfoCurrent() {
		try {
			fidelization.searchLoyalClientWithId("22344");
		} catch (NullPointerException e) {
			System.out.println("Pass testThereAreNoRecordsExceptionForSearchInfoCurrent!");
		} catch (noMatchesException e) {
			fail("Se esperaba excepcion sin registros");
		}
	}
}
