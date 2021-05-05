package activity7;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import activity7.SingleItemSizable.Size;

public class TestSizable {
	SingleItem food1 = new SingleItem("burger", 3.5, Foodtype.Mains);
	SingleItem food2 = new SingleItem("fries", 2, Foodtype.Snacks);
	SingleItemSizable smallFood1 = new SingleItemSizable("burger", 3.5, Foodtype.Mains,Size.Small);
	SingleItemSizable mediumFood1 = new SingleItemSizable("burger", 3.5, Foodtype.Mains,Size.Medium);
	SingleItemSizable largeFood1 = new SingleItemSizable("burger", 3.5, Foodtype.Mains,Size.Large);
	SingleItemSizable smallFood2 = new SingleItemSizable("fries", 2, Foodtype.Snacks,Size.Small);
	SingleItemSizable mediumFood2 = new SingleItemSizable("fries", 2, Foodtype.Snacks,Size.Medium);
	SingleItemSizable largeFood2 = new SingleItemSizable("fries", 2, Foodtype.Snacks,Size.Large);
	
	@Test
	public void testPrice() {
		assertEquals(smallFood1.getPrice(),food1.getPrice()*0.9);
		assertEquals(mediumFood1.getPrice(),food1.getPrice());
		assertEquals(largeFood1.getPrice(),food1.getPrice()*1.1);
	}
	
	@Test
	public void testSameSize() {
		assertTrue(smallFood1.sameSizeAs(Size.Small));
		assertFalse(smallFood1.sameSizeAs(Size.Large));
	}
	
	@Test
	public void testEquals() {
		SingleItemSizable smallFoodTest = new SingleItemSizable("burger", 3.5, Foodtype.Mains,Size.Small);
		assertFalse(smallFood1.equals(smallFood2));
		assertFalse(smallFood1.equals(largeFood1));
		assertFalse(smallFood1.equals(largeFood2));
		assertTrue(smallFood1.equals(smallFoodTest));
		
	}
	
	@Test
	public void testExtraInfo() {
		assertEquals(smallFood1.extraInfo(),"{Size:Small}");
	}
	
	@Test
	public void testHash() {
		assertEquals(smallFood1.hashCode(), food1.hashCode() + Objects.hash(Size.Small));
	}
}
