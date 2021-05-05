package activity7;

import java.util.Set;

import activity7.SingleItemSizable.Size;

public interface MenuItem
{
	/**
	 * @return the price of a certain item
	 */
	double getPrice();

	/**
	 * @return the set of DietaryTags
	 */
	Set<DietaryTag> getDietaryTags(); 

	/**
	 * @return the String description of an item
	 */
	String description();

	/**
	 * @param pFoodtype
	 * @return check if Foodtype matches the input
	 */
	boolean sameFoodTypeAs(Foodtype pFoodtype);

	/**
	 * @param pSize
	 * @return by default it returns false for subtypes other than SingleItemSizable, in which can an actual comparison will be made.
	 */
	default boolean sameSizeAs(Size pSize) {
		return false;
	}
	
	

}
