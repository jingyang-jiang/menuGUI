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



public class TestAbstractMenuItem
{

	ItemStub testStub1;
	ItemStub testStub2;
	
	class ItemStub extends AbstractMenuItem{

		protected ItemStub(String pName, double pPrice, Foodtype pFoodtype)
		{
			super(pName, pPrice, pFoodtype);
		}
		@Override
		protected String extraInfo()
		{
			return null;
		}
		
	}
	
	@BeforeEach
	private void setup(){
		testStub1= new ItemStub("Drinks", 1, Foodtype.Drinks);
		testStub2= new ItemStub("Snacks", 2, Foodtype.Snacks);
	}
	
	@Test 
	public void testAbstractMenuItem() {
		assertThrows(AssertionError.class, () ->  new ItemStub(null, 1, Foodtype.Drinks) );
		assertThrows(AssertionError.class, () ->  new ItemStub("test", 0, Foodtype.Drinks) );
		assertThrows(AssertionError.class, () ->  new ItemStub("test", 1, null) );
	}
	
	@Test
	public void testGetPrice() {
		assertEquals(testStub1.getPrice(), 1);
		assertEquals(testStub2.getPrice(), 2);
	}
	
	@Test
	public void testAddDietaryTag() {
		
		Set<DietaryTag> tags = getTagsMeta(testStub1);
		assertTrue(tags.isEmpty());
		
		testStub1.addDietaryTag(DietaryTag.Vegan);
		assertFalse(tags.isEmpty());
	}
	
	@Test
	public void testRemoveDietaryTag() {
		Set<DietaryTag> tags = getTagsMeta(testStub1);
		tags.add(DietaryTag.Vegan);
		
		testStub1.removeDietaryTag(DietaryTag.Vegan);
		assertTrue(tags.isEmpty());
		
	}
	
	@Test
	public void testSameFoodtypeAs() {
		assertThrows(AssertionError.class, ()-> testStub1.sameFoodTypeAs(null));
		assertTrue(testStub1.sameFoodTypeAs(Foodtype.Drinks));
		assertFalse(testStub1.sameFoodTypeAs(Foodtype.Mains));
	}
	
	@Test
	public void testHasTag() {
		assertThrows(AssertionError.class, () -> testStub1.sameFoodTypeAs(null));
		assertFalse(testStub1.hasTag(DietaryTag.Vegan));
	}
	@Test 
	public void testEquals() {
		assertFalse(testStub1.equals(testStub2));
		ItemStub testStub3 = new ItemStub("Drinks", 1, Foodtype.Drinks);
		assertTrue(testStub1.equals(testStub3));
		assertTrue(testStub1.equals(testStub1));
		assertFalse(testStub1.equals("hello"));
		assertFalse(testStub1.equals(null));
		assertFalse(testStub1.equals(testStub2));
	}
	
	private Set<DietaryTag> getTagsMeta(AbstractMenuItem pAbstractMenuItem){
		try {
			Field field = AbstractMenuItem.class.getDeclaredField("aDietaryTags");
			field.setAccessible(true);
			return (Set<DietaryTag>) field.get(pAbstractMenuItem);
		}catch(ReflectiveOperationException e){
			return null;
		}
	}
	@Test
	public void testDescription() {
		SingleItem food = new SingleItem("Meatball", 3.5, Foodtype.Mains);
		System.out.println(food.description());
		food.addDietaryTag(DietaryTag.GlutonFree);
		assertEquals(food.description(), "Meatball:Mains([GlutonFree])$3.5");
		assertEquals(food.description(), food.toString());
	}
	@Test
	public void testGetTags() {
		Set<DietaryTag> pTags = new HashSet<DietaryTag>();
		testStub1.addDietaryTag(DietaryTag.Vegan);
		testStub1.addDietaryTag(DietaryTag.Vegetarian);
		testStub1.addDietaryTag(DietaryTag.GlutonFree);
		pTags.add(DietaryTag.Vegan);
		pTags.add(DietaryTag.Vegetarian);
		pTags.add(DietaryTag.GlutonFree);
		assertEquals(testStub1.getDietaryTags(),pTags);
		
	}
	@Test
	public void testHash() {
		String name = "burger";
		double price = 2.5;
		Foodtype foodtype = Foodtype.Mains;
		ItemStub testStub3 = new ItemStub(name, price, foodtype);
		assertEquals(testStub3.hashCode(),Objects.hash(name, price, foodtype, testStub3.getDietaryTags()));
	}
	
}
