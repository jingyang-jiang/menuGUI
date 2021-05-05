package activity7;

public class SingleItem extends AbstractMenuItem
{

	protected SingleItem(String pName, double pPrice, Foodtype pFoodtype)
	{
		super(pName, pPrice, pFoodtype);
	}

	@Override
	protected String extraInfo()
	{
		return "";
	}


}
