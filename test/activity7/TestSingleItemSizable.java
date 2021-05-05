package activity7;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import activity7.SingleItemSizable.Size;

public class TestSingleItemSizable
{
	
	@Test
	public void testSingleItemSizable() {
		assertThrows(AssertionError.class, () -> new SingleItemSizable("t", 1, Foodtype.Drinks, null));
	}
	
	@Test
	public void testGetPrice() {
		SingleItemSizable testItem = new SingleItemSizable("t", 1, Foodtype.Drinks, Size.Small);
		assertEquals(testItem.getPrice(), 0.9);
	}
	
	@Test
	public void testSameSizeAs() {
		SingleItemSizable testItem = new SingleItemSizable("t", 1, Foodtype.Drinks, Size.Small);
		assertThrows(AssertionError.class, ()-> testItem.sameSizeAs(null));
		assertTrue(testItem.sameSizeAs(Size.Small));
	}
	
}
