package activity7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import activity7.SingleItemSizable.Size;

import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;

/**
 * this observer follows a push data flow design. 
 *
 */
public class MenuView implements MenuObserver{

	//---------------------data is stored here-------------
	private final List<MenuItem> aMenuItems = new ArrayList<>(); 

	//------------------------two callbacks----------------
	@Override
	public void MenuItemAdded(MenuItem pMenuItem)
	{
		aMenuItems.add(pMenuItem);	
	}
	
	@Override
	public void MenuItemRemoved(MenuItem pMenuItem)
	{
		aMenuItems.remove(pMenuItem);
	}
	
	//-----------------------helper method to turn a list into a listView
	private ListView<MenuItem> toListView(List<MenuItem> aMenuitems)
	{
		ObservableList<MenuItem> ListView = FXCollections.observableArrayList();
		for(MenuItem item: aMenuitems)
		{ListView.add(item);}
		return new ListView<MenuItem>(ListView);
	}
	
	//------------------------Filtering methods dependent on FilterStrategy, the results are sorted based on SortingStrategy
	public ListView<MenuItem> getAll(Comparator<MenuItem> comparator)
	{
		return toListView(aMenuItems.stream().sorted(comparator).collect(toList())) ;
	}
	
	public ListView<MenuItem> getItemsByFoodtype(Foodtype pFoodtype,Comparator<MenuItem> comparator)
	{
		return toListView(FilterStrategy.filterByFoodtype(aMenuItems, pFoodtype).stream().sorted(comparator).collect(toList()) );
	}

	public ListView<MenuItem> getItemsByDietaryTag(DietaryTag pDietaryTag, Comparator<MenuItem> comparator)
	{
		return toListView(FilterStrategy.filterByTag(aMenuItems, pDietaryTag).stream().sorted(comparator).collect(toList()) );
	}
	
	public ListView<MenuItem> getItemsOnSpecial(Comparator<MenuItem> comparator)
	{
		return toListView(FilterStrategy.filterBySpecial(aMenuItems.stream().sorted(comparator).collect(toList()) ));
	}
	
	public ListView<MenuItem> getItemsBySize(Size pSize, Comparator<MenuItem> comparator)
	{
		return toListView(FilterStrategy.filterBySize(aMenuItems.stream().sorted(comparator).collect(toList()), pSize));
	}
	
	public ListView<MenuItem> getCheapest(int count)
	{
		assert count < aMenuItems.size() && 0 < count;
		return toListView(aMenuItems.stream().sorted(SortingStrategy.ByIncreasingPrice()).limit(count).collect(toList()) );
	}

	public ListView<MenuItem> getExpensivest(int count)
	{
		assert count < aMenuItems.size() && 0 < count;
		return toListView(aMenuItems.stream().sorted(SortingStrategy.ByDecreasingPrice()).limit(count).collect(toList()) );
	}
	
}

