package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.insufficientQuantityException;
import model.WeightProduct;

class WeightProductTest {
	private WeightProduct weight;
	private void setUpSceneValidateQuantity() {
		 weight = new WeightProduct("12","Carne", "hoy", 2200, "comida", 12);
	}
	@Test
	void testUodateQuantity() {
		setUpSceneValidateQuantity();
		try {
			weight.update(54);
			fail("Se esperaba excepcion cantidad insuficiente");
		} catch (insufficientQuantityException e) {
			System.out.println("Pass!");
		}
	}
	@Test
	void testUpdateEnough() {
		setUpSceneValidateQuantity();
		try {
			weight.update(10.5);
			assertEquals(1.5, weight.getWeight());
		} catch (insufficientQuantityException e) {
			fail("No se esperaba excepcion");
		}
	}

}
