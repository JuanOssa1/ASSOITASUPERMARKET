package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.noMatchesException;
import exceptions.unavaiableIdException;
import model.Inventory;
import model.UnityProduct;
import model.WeightProduct;

class InventoryTest {
	private Inventory inventory;
	
	private void setUpSceneInsert() throws unavaiableIdException {
		inventory = new Inventory();
		inventory.addUnityProductToTheList("1", "Papas margarita", "hoy", 1000, "Carbohidrato", 20);
		inventory.addUnityProductToTheList("2", "Cerveza", "maniana", 2000, "Cebada", 20);
		inventory.addUnityProductToTheList("3", "agua", "ayer", 3000, "hidratante", 20);
		inventory.addWeightProductToTheList("4", "carne", "1 mes", 2500, "proteina", 20000);
		inventory.addWeightProductToTheList("5", "verdura", "1 mes", 2500, "proteina", 20000);
		inventory.addWeightProductToTheList("6", "naranjas", "1 mes", 2600, "proteina", 20000);
	}
	@Test
	void testInsertAndSearch() { 
		try {
			setUpSceneInsert();
			UnityProduct unity0 = inventory.searchUnityProduct("1");
			UnityProduct unity1 = inventory.searchUnityProduct("2");
			UnityProduct unity2 = inventory.searchUnityProduct("3");
			WeightProduct weight0 = inventory.searchWeightProduct("4");
			WeightProduct weight1 = inventory.searchWeightProduct("5");
			WeightProduct weight2 = inventory.searchWeightProduct("6");
			assertEquals("UnityProduct [quantity=20, getId()=1, getName()=Papas margarita, getBestBefore()=hoy, getPrice()=1000.0, getProductType()=Carbohidrato]", unity0.toString());
			assertEquals("UnityProduct [quantity=20, getId()=2, getName()=Cerveza, getBestBefore()=maniana, getPrice()=2000.0, getProductType()=Cebada]", unity1.toString());
			assertEquals("UnityProduct [quantity=20, getId()=3, getName()=agua, getBestBefore()=ayer, getPrice()=3000.0, getProductType()=hidratante]", unity2.toString());
			assertEquals("WeightProduct [weight=20000.0, getId()=4, getName()=carne, getBestBefore()=1 mes, getPrice()=2500.0, getProductType()=proteina]", weight0.toString());
			assertEquals("WeightProduct [weight=20000.0, getId()=5, getName()=verdura, getBestBefore()=1 mes, getPrice()=2500.0, getProductType()=proteina]", weight1.toString());
			assertEquals("WeightProduct [weight=20000.0, getId()=6, getName()=naranjas, getBestBefore()=1 mes, getPrice()=2600.0, getProductType()=proteina]", weight2.toString());
		} catch (unavaiableIdException e) {
			fail("No se esperaba exception");
		} catch (noMatchesException e) {
			fail("No se esperaba exception");
		}
	}
	@Test
	void testSearchNoneExistIdException() {
		try {
			setUpSceneInsert();
			inventory.searchUnityProduct("31");
			fail("Se esperaba exception noMatches");
		} catch (unavaiableIdException e) {
			fail("Se esperaba exception noMatches");
		} catch (noMatchesException e) {
			System.out.println("Pass!");
		}
	}
	private void setUpSceneRepeadtedIdUnity() throws unavaiableIdException {
		inventory = new Inventory();
		inventory.addUnityProductToTheList("2", "Papas margarita", "hoy", 1000, "Carbohidrato", 20);
		inventory.addUnityProductToTheList("2", "Cerveza", "maniana", 2000, "Cebada", 20);
	}
	@Test 
	void testRepeatedIdExceptionUnity() {
		try {
			setUpSceneRepeadtedIdUnity();
			fail("Se esperaba excepcion id no disponible");
		} catch (unavaiableIdException e) {
			System.out.println("Pass!");
		}
	}
	@Test
	void testUpdateInfoUnity() {
		try {
			setUpSceneInsert();
			String infoBeforeUpdate = inventory.searchUnityProduct("2").toString();
			inventory.updateUnityProductData("2", "Jairo emilio en gotas", "Un mes", 1223, "Natural", 55);
			String infoAfterUpdate = inventory.searchUnityProduct("2").toString();
			assertNotEquals(infoBeforeUpdate,infoAfterUpdate);
		} catch (unavaiableIdException e) {
			fail();
		} catch (noMatchesException e) {
			fail();
		}
	}
	//------------------------------------------------>Weight Product
	@Test
	void testSearchNoneExistIdExceptionWeight() {
		try {
			setUpSceneInsert();
			inventory.searchWeightProduct("31");
			fail("Se esperaba exception noMatches");
		} catch (unavaiableIdException e) {
			fail("Se esperaba exception noMatches");
		} catch (noMatchesException e) {
			System.out.println("Pass!");
		}
	}
	private void setUpSceneRepeadtedIdWeight() throws unavaiableIdException {
		inventory = new Inventory();
		inventory.addWeightProductToTheList("2", "Arroz", "hoy", 1000, "Carbohidrato", 20);
		inventory.addWeightProductToTheList("2", "Carne", "maniana", 2000, "Cebada", 20);
	}
	@Test 
	void testRepeatedIdExceptionWeight() {
		try {
			setUpSceneRepeadtedIdWeight();
			fail("Se esperaba excepcion id no disponible");
		} catch (unavaiableIdException e) {
			System.out.println("Pass!");
		}
	}
	@Test
	void testUpdateInforWeight() {
		try {
			setUpSceneInsert();
			String infoBeforeUpdate = inventory.searchWeightProduct("4").toString();
			inventory.updateWeightProductData("4", "carmen emilio en gotas", "Un siglo", 1223, "Natural", 55);
			String infoAfterUpdate = inventory.searchWeightProduct("4").toString();
			assertNotEquals(infoBeforeUpdate,infoAfterUpdate);
		} catch (unavaiableIdException e) {
			fail();
		} catch (noMatchesException e) {
			fail();
		}
	}
	private void setUpSceneRepeatedGeneral() throws unavaiableIdException {
		inventory = new Inventory();
		inventory.addWeightProductToTheList("2", "Arroz", "hoy", 1000, "Carbohidrato", 20);
		inventory.addUnityProductToTheList("2", "Papas margarita", "hoy", 1000, "Carbohidrato", 20);
	}
	@Test
	void testRepeatedExceptionSharingProduct() {
		try {
			setUpSceneRepeatedGeneral();
			fail("Se esperaba excepcion id no disponible");
		} catch (unavaiableIdException e) {
			System.out.println("Pass!");
		}
	}
	@Test
	void testDeleteUnityProductConsistent() {
		try {
			setUpSceneInsert();
			inventory.deleteUnityProduct("2");
			assertEquals("UnityProduct [quantity=20, getId()=1, getName()=Papas margarita, getBestBefore()=hoy, getPrice()=1000.0, getProductType()=Carbohidrato]",inventory.searchUnityProduct("1").toString());
			assertEquals("UnityProduct [quantity=20, getId()=3, getName()=agua, getBestBefore()=ayer, getPrice()=3000.0, getProductType()=hidratante]", inventory.searchUnityProduct("3").toString());
		} catch (unavaiableIdException e) {
			fail();
		} catch (noMatchesException e) {
			fail();
		}
	}
	@Test
	void testTrueDeleteUnity() {
		try {
			setUpSceneInsert();
			inventory.deleteUnityProduct("2");
			inventory.searchUnityProduct("2");
			fail("Se esperaba exception no matches");
		} catch (unavaiableIdException e) {
			fail();
		} catch (noMatchesException e) {
			System.out.println("Pass!");
		}
	}
	@Test
	void testDeleteWeightProductConsistent() {
		try {
			setUpSceneInsert();
			inventory.deleteWeightProduct("5");
			assertEquals("WeightProduct [weight=20000.0, getId()=4, getName()=carne, getBestBefore()=1 mes, getPrice()=2500.0, getProductType()=proteina]",inventory.searchWeightProduct("4").toString());
			assertEquals("WeightProduct [weight=20000.0, getId()=6, getName()=naranjas, getBestBefore()=1 mes, getPrice()=2600.0, getProductType()=proteina]", inventory.searchWeightProduct("6").toString());
		} catch (unavaiableIdException e) {
			fail();
		} catch (noMatchesException e) {
			fail();
		}
	}
	@Test
	void testTrueDeleteWeight() {
		try {
			setUpSceneInsert();
			inventory.deleteUnityProduct("5");
			inventory.searchUnityProduct("5");
			fail("Se esperaba exception no matches");
		} catch (unavaiableIdException e) {
			fail();
		} catch (noMatchesException e) {
			System.out.println("Pass!");
		}
	}

}
