package activity7;
import java.util.List;
import static java.util.stream.Collectors.toList;
import activity7.SingleItemSizable.Size;
import java.util.Arrays;
public interface FilterStrategy
{
	List<MenuItem> select(List<MenuItem> pItems); 
	
	/**Method to filter a list of AbstractMenuItem based on their price(inclusive)
	 * @param pItems
	 * @param lowerBound
	 * @param upperBound
	 * @return
	 */
	public static List<MenuItem> filterByPrice(List<MenuItem> pItems, double lowerBound, double upperBound){

		return pItems.stream().filter(item -> (item.getPrice()>= lowerBound) && (item.getPrice() <= upperBound) ).collect(toList());
	}
	
	/**Method to filter a list of AbstractMenuItem based on Food type
	 * @param pItems
	 * @param pFoodtype
	 * @return
	 */
	public static List<MenuItem> filterByFoodtype(List<MenuItem> pItems,Foodtype pFoodtype){
		return pItems.stream().filter(item -> item.sameFoodTypeAs(pFoodtype)).collect(toList());
	}
	
	/**Method to filter a list of AbstractMenuItem based on any combination of DietaryTags
	 * @param pItems
	 * @param dietaryTags
	 * @return
	 */
	public static List<MenuItem> filterByTags(List<MenuItem> pItems,DietaryTag ...dietaryTags){
		return pItems.stream().filter(item -> item.getDietaryTags().containsAll(Arrays.asList(dietaryTags))).collect(toList());
	}

	/**Method to filter a list of AbstractMenuItem based on DietaryTag
	 * @param pItems
	 * @param dietaryTag
	 * @return
	 */
	public static List<MenuItem> filterByTag(List<MenuItem> pItems,DietaryTag pdietaryTag){
		return pItems.stream().filter(item -> item.getDietaryTags().contains(pdietaryTag)).collect(toList());
	}
	
	/**Method to get a list of discounted items
	 * @param pItems
	 * @return
	 */
	public static List<MenuItem> filterBySpecial(List<MenuItem> pItems){
		return pItems.stream().filter(item -> item.getClass()==SpecialDecorator.class).collect(toList());
	}
	
	/**Method to filter a list of AbstractMenuItem based on Size
	 * @param pItems
	 * @param pSize
	 * @return
	 */
	public static List<MenuItem> filterBySize(List<MenuItem> pItems, Size pSize){
		return pItems.stream().filter(item->item.sameSizeAs(pSize)).collect(toList());
	}
	
}
