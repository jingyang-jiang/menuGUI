package activity7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSpecialDecorator
{
	
	SingleItem testItem1;
	
	@BeforeEach 
	public void setup() {
		testItem1 = new SingleItem("Drinks", 1, Foodtype.Drinks);
	}
	
	@Test
	public void testSpecialDecorator() {
		assertThrows(AssertionError.class, () -> new SpecialDecorator(0, testItem1));
		assertThrows(AssertionError.class, () -> new SpecialDecorator(1, testItem1));
		assertThrows(AssertionError.class, () -> new SpecialDecorator(0.5, null));
	}

	@Test
	public void testGetPrice() {
		SpecialDecorator testDecorator = new SpecialDecorator(0.5, testItem1);
		assertEquals(testDecorator.getPrice(), testItem1.getPrice()*0.5);
		
		
	}
}
