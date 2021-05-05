package activity7;

import java.util.List;
import java.util.Comparator;
import static java.util.Comparator.comparing;

public interface SortingStrategy
{
	List<MenuItem> choose(List<MenuItem> pItems); 

	/**
	 * @return a Comparator based on description
	 */
	public static Comparator<MenuItem> ByAlphabeticOrder(){
		return comparing(MenuItem::description);
	}

	/**
	 * @return a Comparator based on description in reverse.
	 */
	public static Comparator<MenuItem> ByReversedAlphabeticOrder(){
		return comparing(MenuItem::description).reversed();
	}
	
	/**
	 * @return a Comparator based on price increasingly
	 */
	public static Comparator<MenuItem> ByIncreasingPrice(){
		return comparing(MenuItem::getPrice);
	}
	
	/**
	 * @return a Comparator based on price decreasingly
	 */
	public static Comparator<MenuItem> ByDecreasingPrice(){
		return ByIncreasingPrice().reversed();
	}
}
