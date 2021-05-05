package activity7;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Combo extends AbstractMenuItem
{

	//private fields of Combo
	private final List<SingleItem> aSingleItems;
	private final double aDiscount;

	/**
	 * @param pName
	 * @param pSingleItems
	 * @param pDiscount
	 * @pre pSingleItems != null && pDiscount < 1 && pDiscount > 0;
	 */
	protected Combo(String pName,List<SingleItem> pSingleItems, double pDiscount)
	{
		super(pName, itemSum(pSingleItems), Foodtype.Combo);
		assert pSingleItems != null && pDiscount < 1 && pDiscount > 0;
		aSingleItems = pSingleItems;
		aDiscount = pDiscount;
	}
	@Override
	public double getPrice()
	{
		return itemSum(aSingleItems) * aDiscount;
	}

	//---------------------------Functional helper method to get sum in the lists ----------------------------------
	private static double itemSum(List<SingleItem> pSingleItems)
	{
		return pSingleItems.stream().mapToDouble(item -> item.getPrice()).sum();
	}


	// I decided to do this as an intersection of tags 
	@Override
	public Set<DietaryTag> getDietaryTags()
	{
		return aSingleItems.stream().map(item -> item.getDietaryTags())
				.reduce((Set<DietaryTag> s1, Set<DietaryTag> s2) -> {
					s1.retainAll(s2);
					return s1;
				}).get();
	}

	@Override
	protected String extraInfo()
	{
		return aSingleItems.stream().map(item->item.description()).reduce((String s1, String s2)-> s1 + s2).get();
	}

	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj)&& aDiscount == ((Combo)obj).aDiscount && aSingleItems.equals(((Combo)obj).aSingleItems);
	}
	
	@Override
	public int hashCode()
	{

		return super.hashCode()+Objects.hash(aDiscount,aSingleItems);
	}
}
