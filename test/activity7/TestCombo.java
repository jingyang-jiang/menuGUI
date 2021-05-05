package activity7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestCombo
{

	SingleItem testItem1;
	SingleItem testItem2;
	Combo testCombo;
	@BeforeEach 
	public void setup() {
		testItem1 = new SingleItem("Drinks", 1, Foodtype.Drinks);
		testItem2 = new SingleItem("Snacks", 2, Foodtype.Snacks);
		testCombo = new Combo("testCombo", new ArrayList<SingleItem>(Arrays.asList(testItem1,testItem2)), 0.5);
	}
	
	@Test
	public void testCombo() {
		assertThrows(AssertionError.class, () -> new Combo("Combo",new ArrayList<SingleItem>() , 0));
		assertThrows(AssertionError.class, () -> new Combo("Combo", new ArrayList<SingleItem>(), 1));
	}
	
	@Test
	public void testGetPrice() {
		assertEquals(testCombo.getPrice(), 1.5);
	}
	
	@Test
	public void testItemSum() {
		try {
			Field field = Combo.class.getDeclaredField("aSingleItems");
			field.setAccessible(true);
			
			Method method = Combo.class.getDeclaredMethod("itemSum", List.class );
			method.setAccessible(true);
			
			assertEquals(method.invoke(null, field.get(testCombo)),3.0);
		}catch (ReflectiveOperationException e) {
			fail();
			
		}
	}
	
	
}
