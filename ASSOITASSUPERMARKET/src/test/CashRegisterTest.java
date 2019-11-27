package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.noMatchesException;
import exceptions.unavaiableIdException;
import model.CashRegister;
import model.CommercialInvoice;
import model.CurrentClient;
import model.Inventory;
import model.LoyalClient;
import model.Product;
import model.UnityProduct;
import model.WeightProduct;

class CashRegisterTest {
	
	private CashRegister cash;
	private void setUpSceneInsert() throws unavaiableIdException {
		cash = new CashRegister();
		LoyalClient client = new LoyalClient("1", "pedro", "juan@fff", "1234", 2333, 0.2, "12333");
		CurrentClient current = new CurrentClient("1223","carlos","20", "carlos@drr");
		ArrayList<Product> products = new ArrayList<Product>(); 
		UnityProduct product0 = new UnityProduct("casa", "carro", "moto", 23.3, "hola", 3);
		products.add(product0);
		cash.addInvoiceProductToTheList("12/12/12", "Efectivo", "1", products, client);
		cash.addInvoiceProductToTheList("13/11/19", "Debito", "2", products, current);
	}
	@Test
	void testInsertAndSearch() {
		try {
			setUpSceneInsert();
			CommercialInvoice invoice0= cash.searchInvoice("1");
			CommercialInvoice invoice1= cash.searchInvoice("2");
			assertEquals("CommercialInvoice [date=12/12/12, totalPrice=0.0, paymentType=Efectivo, factureNumber=1, client=LoyalClient [points=2333, discountPercent=0.2, dueCard=12333, getId()=1, getName()=pedro, getAge()=juan@fff, getEmail()=1234], products=[UnityProduct [quantity=3, getId()=casa, getName()=carro, getBestBefore()=moto, getPrice()=23.3, getProductType()=hola]]]", invoice0.toString());
			assertEquals("CommercialInvoice [date=13/11/19, totalPrice=0.0, paymentType=Debito, factureNumber=2, client=CurrentClient [getId()=1223, getName()=carlos, getAge()=20, getEmail()=carlos@drr], products=[UnityProduct [quantity=3, getId()=casa, getName()=carro, getBestBefore()=moto, getPrice()=23.3, getProductType()=hola]]]", invoice1.toString());
		} catch (unavaiableIdException e) {
			fail("No se esperaba exception");
		} catch (noMatchesException e) {
			fail("No se esperaba exception");
		}
	}
	@Test
	void testSearchNoneExistInvoice() {
		try {
			setUpSceneInsert();
			cash.searchInvoice("55");
			fail("Se esperaba exception noMatches");
		} catch (unavaiableIdException e) {
			fail("Se esperaba exception noMatches");
		} catch (noMatchesException e) {
			System.out.println("Pass!");
		}
	}
	
	private void setUpSceneRepeadtedInvoice() throws unavaiableIdException {
		cash = new CashRegister();
		LoyalClient client = new LoyalClient("1", "pedro", "juan@fff", "1234", 2333, 0.2, "12333");
		CurrentClient current = new CurrentClient("1223","carlos","20", "carlos@drr");
		ArrayList<Product> products = new ArrayList<Product>(); 
		UnityProduct product0 = new UnityProduct("casa", "carro", "moto", 23.3, "hola", 3);
		products.add(product0);
		cash.addInvoiceProductToTheList("12/12/12", "Efectivo", "2", products, client);
		cash.addInvoiceProductToTheList("13/11/19", "Debito", "2", products, current);
	}
	
	@Test 
	void testRepeatedIdExceptionInvoice() {
		try {
			setUpSceneRepeadtedInvoice();
			fail("Se esperaba excepcion id no disponible");
		} catch (unavaiableIdException e) {
			System.out.println("Pass!");
		}
	}
	@Test
	void testUpdateInfoInvoice() {
		try {
			setUpSceneInsert();
			String infoBeforeUpdate = cash.searchInvoice("1").toString();
			cash.updateInvoiceData("hoy", "efectivo", "1");
			String infoAfterUpdate = cash.searchInvoice("1").toString();
			assertNotEquals(infoBeforeUpdate,infoAfterUpdate);
		} catch (unavaiableIdException e) {
			fail();
		} catch (noMatchesException e) {
			fail();
		}
	}
}
