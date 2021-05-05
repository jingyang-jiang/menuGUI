package activity7;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import activity7.TestMenu.MenuItemStub;

public class TestMenuItem
{
	private final Menu menu = new Menu();
	AbstractMenuItem item = new MenuItemStub("Hotdog", 2.50, Foodtype.Mains);
	
	// setup erase everything
	
	@Test
	public void testMenuItemName()
	{
		menu.addItem(item);
		
		assertEquals(getItemName((AbstractMenuItem) getMenuItems().get(0)), "Hotdog");
	}
	
	@Test
	public void testMenuItemCategory()
	{
		menu.addItem(item);
		
		assertEquals(getItemCategory((AbstractMenuItem) getMenuItems().get(0)), Foodtype.Mains);
	}
	
	@Test
	public void testMenuItemPrice()
	{
		menu.addItem(item);
		
		assertEquals(2.50, getItemPrice((AbstractMenuItem) getMenuItems().get(0)), 0.1);
	}
	
	@Test
	public void testMenuItemZeroTags()
	{
		menu.addItem(item);

		assertEquals(getItemTags((AbstractMenuItem) getMenuItems().get(0)), new HashSet<>());
	}
	
	@Test
	public void testMenuItemOneTag()
	{
		item.addDietaryTag(DietaryTag.Vegan);
		
		menu.addItem(item);

		Set<DietaryTag> expectedTags = new HashSet<>();
		expectedTags.add(DietaryTag.Vegan);
		
		assertEquals(getItemTags((AbstractMenuItem) getMenuItems().get(0)), expectedTags);
	}
	
	@Test
	public void testMenuItemMultipleTags()
	{
		item.addDietaryTag(DietaryTag.Vegan);
		item.addDietaryTag(DietaryTag.Vegetarian);
		
		menu.addItem(item);

		Set<DietaryTag> expectedTags = new HashSet<>();
		expectedTags.add(DietaryTag.Vegan);
		expectedTags.add(DietaryTag.Vegetarian);
		
		assertEquals(getItemTags((AbstractMenuItem) getMenuItems().get(0)), expectedTags);
	}
	
	// HELPER METHODS
	private String getItemName(AbstractMenuItem i) {
		try
		{
			Field field = AbstractMenuItem.class.getDeclaredField("aName");
			field.setAccessible(true);
			return (String) field.get(i);
		}
		catch (ReflectiveOperationException e)
		{
			return null;
		}
	}
	
	private Foodtype getItemCategory(AbstractMenuItem i) {
		try
		{
			Field field = AbstractMenuItem.class.getDeclaredField("aFoodtype");
			field.setAccessible(true);
			return (Foodtype) field.get(i);
		}
		catch (ReflectiveOperationException e)
		{
			return null;
		}
	}
	
	private double getItemPrice(AbstractMenuItem i) {
		try
		{
			Field field = AbstractMenuItem.class.getDeclaredField("aPrice");
			field.setAccessible(true);
			return (double) field.get(i);
		}
		catch (ReflectiveOperationException e)
		{
			return 0;
		}
	}
	
	private Set<DietaryTag> getItemTags(AbstractMenuItem i) {
		try
		{
			Field field = AbstractMenuItem.class.getDeclaredField("aDietaryTags");
			field.setAccessible(true);
			return (Set<DietaryTag>) field.get(i);
		}
		catch (ReflectiveOperationException e)
		{
			return null;
		}
	}
	
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
