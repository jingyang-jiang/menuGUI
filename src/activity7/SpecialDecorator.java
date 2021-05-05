package activity7;

import java.util.Set;

/**
 * this decorator puts a MenuItem on Speical.
 *
 */
public class SpecialDecorator implements MenuItem
{
	//private fields
	private double aSpecialDiscount;
	private MenuItem aMenuItem;

	/**
	 * @param pSpecialDiscount
	 * @param aMenuItem
	 * @pre assert pSpecialDiscount > 0 && aSpecialDiscount < 1 && aMenuItem != null;
	 */
	public SpecialDecorator(double pSpecialDiscount, MenuItem aMenuItem)
	{
		assert pSpecialDiscount > 0 && pSpecialDiscount < 1 && aMenuItem != null;
		this.aSpecialDiscount = pSpecialDiscount;
		this.aMenuItem = aMenuItem;
	}

	@Override
	public double getPrice()
	{
		return aMenuItem.getPrice() * aSpecialDiscount;
	}

	@Override
	public boolean sameFoodTypeAs(Foodtype pFoodtype)
	{
		return aMenuItem.sameFoodTypeAs(pFoodtype);
	}

	@Override
	public Set<DietaryTag> getDietaryTags()
	{
		return aMenuItem.getDietaryTags();
	}

	@Override
	public String description()
	{
		return aMenuItem.description()+" is on Special for $"+getPrice();
	}
	@Override
	public String toString()
	{
		return this.description();
	}
}
