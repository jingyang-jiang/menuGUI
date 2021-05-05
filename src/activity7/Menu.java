
package activity7;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a menu that can be displayed in the menu display.
 */
public class Menu
{
	private final List<MenuItem> aMenuItems = new ArrayList<>(); 
	private final List<MenuObserver> aMenuObservers = new ArrayList<>();
	
	// Changes the menu
	public void addItem(MenuItem pMenuItem) {
		assert pMenuItem!=null;
		if(aMenuItems.contains(pMenuItem)) {
			System.out.println("item already exists");
			return;
		}
		aMenuItems.add(pMenuItem);
		for(MenuObserver aObserver: aMenuObservers) {
			aObserver.MenuItemAdded(pMenuItem);
		}
	}
	
	public void removeItem(MenuItem pMenuItem) {
		assert aMenuItems.contains(pMenuItem) && pMenuItem!=null;
		aMenuItems.remove(pMenuItem);
		for(MenuObserver aObserver: aMenuObservers) {
			aObserver.MenuItemRemoved(pMenuItem);
		}
	}
	
	// Add/remove MenuObversers
	public void addMenuObserver(MenuObserver pMenuObserver){
		assert pMenuObserver != null;
		if (!aMenuObservers.contains(pMenuObserver))
			aMenuObservers.add(pMenuObserver);
	}
	
	public void removeMenuObserver(MenuObserver pMenuObserver){
		assert pMenuObserver != null;
		if (aMenuObservers.contains(pMenuObserver))
			aMenuObservers.remove(pMenuObserver);
	}


}
