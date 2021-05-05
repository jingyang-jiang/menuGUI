package activity7;

public interface MenuObserver 
{
	/*
	 * Callback method for when an item has been added to the menu
	 */
    public void MenuItemAdded(MenuItem pMenuItem);
    
    /*
     * Callback method for when an item has been removed from the menu
     */
    public void MenuItemRemoved(MenuItem pMenuItem);
    

}
