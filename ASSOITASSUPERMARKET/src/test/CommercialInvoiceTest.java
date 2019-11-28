package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.CommercialInvoice;
import model.LoyalClient;
import model.Product;
import model.UnityProduct;
import model.WeightProduct;

class CommercialInvoiceTest {
	private CommercialInvoice invoice;
	private void setUpSceneInsert() {
		ArrayList<Product> products = new ArrayList<Product>();
		LoyalClient client = new LoyalClient("1", "Juan", "18", "Carlitos@yahoo.com", 1233, 0.3, "1234");
		UnityProduct unity0 = new UnityProduct("1", "Papas margarita", "hoy", 1000, "Carbohidrato", 8);
		UnityProduct unity1 = new UnityProduct("2", "Cerveza", "maniana", 2000, "Cebada", 10);
		UnityProduct unity2 = new UnityProduct("3", "agua", "ayer", 3000, "hidratante", 5);
		WeightProduct weight0 = new WeightProduct("4", "carne", "1 mes", 2500, "proteina", 2);
		WeightProduct weight1 = new WeightProduct("5", "verdura", "1 mes", 2500, "proteina", 3);
		WeightProduct weight2 = new WeightProduct("6", "naranjas", "1 mes", 2600, "proteina", 1);
		products.add(unity0);
		products.add(unity1);
		products.add(unity2);
		products.add(weight0);
		products.add(weight1);
		products.add(weight2);
		invoice = new CommercialInvoice("12/12/12", "Efectivo", "1223",products, client);
	}
	@Test
	void testLoyalDiscount() {
		setUpSceneInsert();
		//invoice.calculateLoyalDiscount();
		assertEquals(0.3,invoice.calculateLoyalDiscount());
	}
	@Test
	void testSubtotal() {
		setUpSceneInsert();
		assertEquals(58100,invoice.subTotal());
	}
	@Test
	void testSavings() {
		setUpSceneInsert();
		assertEquals(17430,invoice.calculateSavings());
	}
	@Test
	void testCalculateIVA() {
		setUpSceneInsert();
		assertEquals(11039,invoice.calculateIVA());
	}
	@Test
	void testTotal() {
		setUpSceneInsert();
		assertEquals(51709, invoice.total());
	}
	@Test
	void printProducts() {
		setUpSceneInsert();
		assertEquals("UnityProduct [quantity=8, getId()=1, getName()=Papas margarita, getBestBefore()=hoy, getPrice()=1000.0, getProductType()=Carbohidrato]"+ "\n" +"UnityProduct [quantity=10, getId()=2, getName()=Cerveza, getBestBefore()=maniana, getPrice()=2000.0, getProductType()=Cebada]"+ "\n" + "UnityProduct [quantity=5, getId()=3, getName()=agua, getBestBefore()=ayer, getPrice()=3000.0, getProductType()=hidratante]"+ "\n" + "WeightProduct [weight=2.0, getId()=4, getName()=carne, getBestBefore()=1 mes, getPrice()=2500.0, getProductType()=proteina]"+ "\n" + "WeightProduct [weight=3.0, getId()=5, getName()=verdura, getBestBefore()=1 mes, getPrice()=2500.0, getProductType()=proteina]" + "\n"+"WeightProduct [weight=1.0, getId()=6, getName()=naranjas, getBestBefore()=1 mes, getPrice()=2600.0, getProductType()=proteina]" + "\n",invoice.printProducts());
	}

}
