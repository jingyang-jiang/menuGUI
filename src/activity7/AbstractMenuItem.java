package activity7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractMenuItem implements MenuItem
{

	//private field that are inherited by all subtypes 
	private final String aName;
	private final double aPrice;
	private final Foodtype aFoodtype;
	private final Set<DietaryTag> aDietaryTags = new HashSet<>();

	/**
	 * @param pName
	 * @param pPrice
	 * @param pFoodtype
	 * @pre pName!=null && pPrice > 0 && pFoodtype!=null
	 */

	protected AbstractMenuItem(String pName,double pPrice, Foodtype pFoodtype) {
		assert pName!=null && pPrice > 0 && pFoodtype!=null; 
		aName = pName;
		aPrice = pPrice;
		aFoodtype = pFoodtype; 
	}
	
	@Override
	public double getPrice() {
		return aPrice;
	}

	//------------------Template Method Design ---------------------------------------//
	protected abstract String extraInfo();
	
	@Override
	public String description() {
		StringBuilder description = new StringBuilder();
		description.append(aName);
		description.append(":" + aFoodtype);
		description.append("(");
		for (DietaryTag tag : aDietaryTags)
		{
			description.append("[" + tag + "]");
		}
		description.append(")");
		description.append(extraInfo());
		description.append("$"+this.getPrice());
		return description.toString();
	}

	// --------------------------DietaryTag manipulation----------------------------
	public void addDietaryTag(DietaryTag pDietaryTag)
	{
		assert pDietaryTag != null;
		aDietaryTags.add(pDietaryTag);
	}

	public void removeDietaryTag(DietaryTag pDietaryTag)
	{
		assert pDietaryTag != null;
		aDietaryTags.remove(pDietaryTag);
	}

	// ----------------------------dedicated predicate methods for filtering-----------------------
	@Override
	public boolean sameFoodTypeAs(Foodtype pFoodtype)
	{
		assert pFoodtype!=null;
		return aFoodtype == pFoodtype;
	}

	public boolean hasTag(DietaryTag pDietaryTag)
	{
		assert pDietaryTag!=null;
		return aDietaryTags.contains(pDietaryTag);
	}

	@Override
	public Set<DietaryTag> getDietaryTags(){
		return Set.copyOf(aDietaryTags);
	}
	
	//----------------------------------Equality Checking & toString-------------------------------
	 @Override
	public boolean equals(Object obj)
	{
		if(obj == null)return false;
		else if(obj == this)return true;
		else if(obj.getClass()!= getClass())return false;
		else {
			return aName == ((AbstractMenuItem)obj).aName &&
					aPrice == ((AbstractMenuItem)obj).aPrice &&
					aFoodtype == ((AbstractMenuItem)obj).aFoodtype &&
					aDietaryTags.equals(((AbstractMenuItem)obj).aDietaryTags);
		}
	}
	 @Override
	public int hashCode()
	{
		return Objects.hash(aName,aPrice,aFoodtype,aDietaryTags);
	}
	 
	 @Override
	public String toString()
	{
		return this.description();
	}
	 
	 
	 
	 
}
