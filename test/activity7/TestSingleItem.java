package activity7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestSingleItem
{

	
	@Test
	public void testSingleItem() {
		assertThrows(AssertionError.class, ()-> new SingleItem(null, 1, Foodtype.Mains));
		assertThrows(AssertionError.class, ()-> new SingleItem("Mains", 0, Foodtype.Mains));
		assertThrows(AssertionError.class, ()-> new SingleItem("Mains", 1, null));
	}
	
	@Test
	public void testExtraInfo() {
		SingleItem testItem = new SingleItem("Drinks", 1, Foodtype.Drinks);
		assertEquals(testItem.extraInfo(), "");
	}
}
