package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.insufficientQuantityException;
import model.UnityProduct;

class UnityProductTest {
	private UnityProduct unity;
	
	private void setUpSceneValidateQuantity() {
		 unity = new UnityProduct("12","Papas margarita", "hoy", 2200, "comida", 12);
	}
	@Test
	void testUodateQuantity() {
		setUpSceneValidateQuantity();
		try {
			unity.update(54);
			fail("Se esperaba excepcion cantidad insuficiente");
		} catch (insufficientQuantityException e) {
			System.out.println("Pass!");
		}
	}
	@Test
	void testUpdateEnough() {
		setUpSceneValidateQuantity();
		try {
			unity.update(10);
			assertEquals(2, unity.getQuantity());
		} catch (insufficientQuantityException e) {
			fail("No se esperaba excepcion");
		}
	}

}
