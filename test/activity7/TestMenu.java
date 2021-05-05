package activity7;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

public class TestMenu
{
	private final Menu menu = new Menu();
	AbstractMenuItem item = new MenuItemStub("Hotdog", 5.0, Foodtype.Mains);
	AbstractMenuItem item2 = new MenuItemStub("Hamburger", 10.0, Foodtype.Mains);
	AbstractMenuItem item3 = new MenuItemStub("Juice", 2.0, Foodtype.Drinks);
	AbstractMenuItem item4 = new MenuItemStub("Peanut", 3.0, Foodtype.Snacks);
	
	@BeforeEach
	private void setup()
	{
		try
		{
			Field field = Menu.class.getDeclaredField("aMenuItems");
			field.setAccessible(true);
			field.set(menu, new ArrayList<>());
		}
		catch (ReflectiveOperationException e)
		{
			
		}
	}
	
	@Test
	public void testAddItem()
	{
		menu.addItem(item);
		
		List<MenuItem> items = new ArrayList<>();
		items.add(item);

		assertEquals(getMenuItems(), items);
	}
	
	@Test
	public void testRemoveItem()
	{
		menu.addItem(item);
		
		List<MenuItem> items = new ArrayList<>();
		items.add(item);
		
		menu.removeItem(item);
		items.remove(item);

		assertEquals(getMenuItems(), items);
	}
	
	@Test
	public void testFilterItemsType()
	{
		menu.addItem(item);
		menu.addItem(item2);
		menu.addItem(item3);
		menu.addItem(item4);
		
		List<MenuItem> observedList = FilterStrategy.filterByFoodtype(getMenuItems(), Foodtype.Mains);
		   
		List<MenuItem> expectedList = new ArrayList<>();
		expectedList.add(item);
		expectedList.add(item2);
		
		assertEquals(observedList, expectedList);
	}
	
	@Test
	public void testFilterItemsPrice()
	{
		menu.addItem(item);
		menu.addItem(item2);
		menu.addItem(item3);
		menu.addItem(item4);
		
		List<MenuItem> observedList = FilterStrategy.filterByPrice(getMenuItems(), 2.5, 6.0);
		   
		List<MenuItem> expectedList = new ArrayList<>();
		expectedList.add(item);
		expectedList.add(item4);
		
		assertEquals(observedList, expectedList);
	}
	
	@Test
	public void testFilterDietaryCategory()
	{
		item3.addDietaryTag(DietaryTag.Vegan);
		item3.addDietaryTag(DietaryTag.Vegetarian);
		item4.addDietaryTag(DietaryTag.Vegan);
		item4.addDietaryTag(DietaryTag.Vegetarian);
		item4.addDietaryTag(DietaryTag.GlutonFree);
		
		menu.addItem(item);
		menu.addItem(item2);
		menu.addItem(item3);
		menu.addItem(item4);
		
		List<MenuItem> observedList = FilterStrategy.filterByTags(getMenuItems(), DietaryTag.Vegan, DietaryTag.Vegetarian);
		   
		List<MenuItem> expectedList = new ArrayList<>();
		expectedList.add(item3);
		expectedList.add(item4);
		
		assertEquals(observedList, expectedList);
	}
	
	// HELPER METHODS	
	private List<MenuItem> getMenuItems()
	{
		try
		{
			Field field = Menu.class.getDeclaredField("aMenuItems");
			field.setAccessible(true);
			return (List<MenuItem>) field.get(menu);
		}
		catch (ReflectiveOperationException e)
		{
			return null;
		}
	}
	
	// STUB
	class MenuItemStub extends AbstractMenuItem
	{
		public MenuItemStub(String pName, double pPrice, Foodtype pFoodtype)
		{
			super(pName, pPrice, pFoodtype);
		}

		@Override
		protected String extraInfo()
		{
			return "";
		}
	}
}
