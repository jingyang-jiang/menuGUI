package activity7;

import java.util.Objects;

public class SingleItemSizable extends SingleItem
{
	public enum Size
	{
		Small, Medium, Large
	};

	//private fields
	private final Size aSize;
	private static double SizeFactor = 0.1;

	
	/**
	 * @param pName
	 * @param pPrice
	 * @param pFoodtype
	 * @param pSize
	 * @pre pSize != null;
	 */
	protected SingleItemSizable(String pName, double pPrice, Foodtype pFoodtype, Size pSize)
	{
		super(pName, pPrice, pFoodtype);
		assert pSize != null;
		aSize = pSize;
	}

	@Override
	public double getPrice()
	{

		if (aSize == Size.Small)
			return (1 - SizeFactor) * super.getPrice();
		else if (aSize == Size.Large)
			return (1 + SizeFactor) * super.getPrice();
		else
			return super.getPrice();
	}
	
	@Override
	protected String extraInfo() {
		return "{Size:"+aSize+"}";
	}
	@Override
	public boolean sameSizeAs(Size pSize) {
		assert pSize!=null;
		return aSize==pSize;
	}
	@Override
	public boolean equals(Object obj)
	{
		
		return super.equals(obj)&& aSize==((SingleItemSizable) obj).aSize;
	}
	@Override
	public int hashCode()
	{
		return super.hashCode()+Objects.hash(aSize);
	}
}
