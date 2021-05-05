package activity7;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import activity7.SingleItemSizable.Size;

/*
 * Skeleton code that illustrates the general layout
 * expected. Modify as necessary.
 */
public class MenuDisplay extends Application
{
	private static final int WIDTH_SIDE = 200;
	private static final int WIDTH_CENTER = 300;
	private Menu menu = new Menu();
	private MenuView view = new MenuView();
	private BorderPane root = new BorderPane();
	private HBox controls;
	private static final String STYLE = 
			"-fx-pref-height: 300px; -fx-border-width: 1; -fx-border-color: black;" +
			"-fx-background-color: lightgrey; -fx-padding: 5px; -fx-alignment: top-center";
	
	/**
	 * Launches the application.
	 * @param pArgs This program takes no argument.
	 */
	public static void main(String[] pArgs) 
	{
        launch(pArgs);
    }
    
    @Override
    public void start(Stage pStage) 
    {
		//register observer
    	menu.addMenuObserver(view);
    	//setup
    	setup();
    	//Add Button
		addButton(root);
		//Remove Button
		removeButton(root);
		//Controls
		controls = controls(root, view);
    	//Initial view using Standard Configuration
    	StandardConfig(root, view, SortingStrategy.ByIncreasingPrice());
        pStage.setScene(new Scene(new VBox(root, controls,specialButton(root))));
        pStage.show();
    }
    
       //-------------------------------------Pre-built Configurations----------------------------------------------------------
    /**
     * Standard Configuration: Drinks|Mains|Snacks
     * @param root
     * @param view
     * @param Sorting
     */
    public static void StandardConfig(BorderPane root, MenuView view, Comparator<MenuItem> Sorting)
    {
    	assert root!=null && view!=null;
    	root.setLeft(createPanel(WIDTH_SIDE, "Drinks", view.getItemsByFoodtype(Foodtype.Drinks, Sorting)));
    	root.setCenter(createPanel(WIDTH_CENTER, "Mains", view.getItemsByFoodtype(Foodtype.Mains, Sorting)));
    	root.setRight(createPanel(WIDTH_SIDE, "Snacks", view.getItemsByFoodtype(Foodtype.Snacks, Sorting)));
    }
   
    /**
     * Configuration: Cheapest x items|All Items|Expensivest x items
     * @param root
     * @param view
     * @param x
     */
    public static void CheapestandExpensivest(BorderPane root, MenuView view, int x) 
    {
    	assert root!=null && view!=null;
    	root.setLeft(createPanel(WIDTH_SIDE, "Top "+x+" Cheapest Items", view.getCheapest(x)));
    	root.setCenter(createPanel(WIDTH_CENTER, "All Items", view.getAll(SortingStrategy.ByIncreasingPrice())));
    	root.setRight(createPanel(WIDTH_SIDE, "Top "+x+" Expensivest Items", view.getExpensivest(x)));
    }

	/**
     * Configuration Vegetarian | Vegan | Gluten Free
     * @param root
     * @param view
     * @param Sorting
     */
    public static void DietaryTypes(BorderPane root, MenuView view, Comparator<MenuItem> Sorting)
    {
    	assert root!=null && view!=null;
    	root.setLeft(createPanel(WIDTH_SIDE, "Vegetarian", view.getItemsByDietaryTag(DietaryTag.Vegetarian, Sorting)));
    	root.setCenter(createPanel(WIDTH_CENTER, "Vegan", view.getItemsByDietaryTag(DietaryTag.Vegan, Sorting)));
    	root.setRight(createPanel(WIDTH_SIDE, "Gluten Free", view.getItemsByDietaryTag(DietaryTag.GlutonFree, Sorting)));
    }
    
    //-------------------------------------Customized Configurations----------------------------------------------------------
    /**
     * Customized view
     * @param root
     * @param titles
     * @param views
     */
    public static void CustomizedView(BorderPane root, String[] titles, ListView<MenuItem>... views)
    {
    	assert root!=null && views.length == 3 && titles.length == 3;
    	root.setLeft(createPanel(WIDTH_SIDE, titles[0], views[0]));
    	root.setCenter(createPanel(WIDTH_CENTER, titles[1], views[1]));
    	root.setRight(createPanel(WIDTH_SIDE, titles[2], views[2]));
    }
    
    //------------------------------------------------Panel Creation---------------------------------------------------------------
    
    private static VBox createPanel(int pWidth, String pTitle, ListView<MenuItem> MenuView)
    {
    	VBox panel = new VBox();
    	panel.setStyle(STYLE);
    	panel.setPrefWidth(pWidth);
    	Label title = new Label(pTitle);
    	title.setStyle("-fx-font-weight: bold");
    	panel.getChildren().add(title);
    	panel.getChildren().add(MenuView);
    	return panel;
    }
    
    private static HBox createControl(String... pConfigurations)
    {
    	assert pConfigurations.length > 0;
    	HBox control = new HBox();
    	control.setPadding(new Insets(5));
        control.setAlignment(Pos.CENTER);
    	ToggleGroup group = new ToggleGroup();

        for( String configuration : pConfigurations )
        {
        	RadioButton button = new RadioButton(configuration);
        	button.setPadding(new Insets(5));
        	button.setToggleGroup(group);
            control.getChildren().add(button);
        }
        ((RadioButton)control.getChildren().get(0)).setSelected(true);
        return control;
    }
    
  //------------------------------------------------Helpers for Remove--------------------------------------------------------

    private static MenuItem getSelected(ListView<MenuItem> pView)
    {return pView.getSelectionModel().getSelectedItem();}
    
  //------------------------------------------------Helpers for Adding--------------------------------------------------------
    private void setup() {
    	// Create items to display
    	SingleItem x = new SingleItem("Poutine",5.5,Foodtype.Mains);
    	SpecialDecorator x1 = new SpecialDecorator(0.8, x);
    	SingleItemSizable x2 = new SingleItemSizable("Pizza", 10, Foodtype.Mains, Size.Medium);
    	SingleItem y = new SingleItem("Boba",5,Foodtype.Drinks);
    	SingleItem z = new SingleItem("Chips",3.5,Foodtype.Snacks);
    	SingleItem a = new SingleItem("Cookie",1.5,Foodtype.Snacks);
    	SingleItem b = new SingleItem("Orange Soda",2.5,Foodtype.Drinks);
    	SingleItem c = new SingleItem("Ramen",10.5,Foodtype.Mains);
    	x.addDietaryTag(DietaryTag.Vegetarian);
    	y.addDietaryTag(DietaryTag.GlutonFree);
    	z.addDietaryTag(DietaryTag.Vegan);
		// add items to be displayed.
    	menu.addItem(x);
    	menu.addItem(y);
    	menu.addItem(z);
    	menu.addItem(a);
    	menu.addItem(b);
    	menu.addItem(c);
    	menu.addItem(x1);
    	menu.addItem(x2);
    }
    private void addButton (BorderPane root){
    	//This VBox contains everything
    	VBox CommandCenter = new VBox();
    	//Two TextFields for input
    	TextField name = new TextField(); 
    	TextField price = new TextField();
    	//ChoiceBox for foodtype it has to be selected otherwise add won't do anything
    	ChoiceBox<Foodtype> chooseFoodtype = new ChoiceBox<>();
    	chooseFoodtype.getItems().addAll(Foodtype.Drinks,Foodtype.Snacks,Foodtype.Mains);
    	//ChoiceBox for size, it can be left as null 
    	ChoiceBox<Size> chooseSize = new ChoiceBox<>();
    	chooseSize.getItems().addAll(Size.values());
    	//This HBox contains all check boxes, 
    	CheckBox vegan = new CheckBox("Vegan");
    	CheckBox vegetarian = new CheckBox("Vegetarian");
    	CheckBox glutonFree = new CheckBox("GlutonFree");
    	HBox chooseDietaryTag = new HBox(vegan,vegetarian,glutonFree);
    	
   
		Button ActionButton = new Button("Add items");
		
		ActionButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
			public void handle(ActionEvent actionEvent) {
				try {
					String pName = name.getText();
					Double pPrice = Double.parseDouble(price.getText());
					name.clear();
					price.clear();
					//i didn't check null because the constructor will handle this by throwing an assertion error, which will be caught below
					Foodtype pFoodtype = chooseFoodtype.getValue();
					//now get all tags from checkboxes
					List<DietaryTag> pDietaryTags = new ArrayList<>();
					if(vegan.isSelected())pDietaryTags.add(DietaryTag.Vegan);
					if(vegetarian.isSelected())pDietaryTags.add(DietaryTag.Vegetarian);
					if(glutonFree.isSelected())pDietaryTags.add(DietaryTag.GlutonFree);
					
					MenuItem toBeAdded;
					
					//construct item and add all tags
					if(chooseSize.getValue()==null) {
						// this item is not sizable
						toBeAdded = new SingleItem(pName, pPrice, pFoodtype);
						pDietaryTags.forEach(tag -> ((SingleItem)toBeAdded).addDietaryTag(tag));
					}else {
						// this item is sizable
						toBeAdded = new SingleItemSizable(pName, pPrice, pFoodtype, chooseSize.getValue());
						pDietaryTags.forEach(tag -> ((SingleItemSizable)toBeAdded).addDietaryTag(tag));
					}
					
					menu.addItem(toBeAdded);
					refresh();
					
					} catch (Exception e) {
							chooseSize.setValue(null);
							name.clear();
							price.clear();
							System.out.println("Action Not Performed");
					}
				}
			});
		//Add all components 
		CommandCenter.getChildren().addAll(new Label("Name"),name,new Label("Price"),price,
    			new Label("Foodtype"),chooseFoodtype,
    			new Label("Size(Optional)"),chooseSize,
    			new Label("DietaryTags"),chooseDietaryTag,
    			ActionButton);
		
		//Add commandCenter to root
		root.setTop(CommandCenter);
    }
    private void removeButton(BorderPane root) {
    	VBox MenuEditer = new VBox();
    	Button RemoveButton = new Button("Remove Item");
    	RemoveButton.setMaxWidth(Double.MAX_VALUE);
    	RemoveButton.setOnAction(new EventHandler<ActionEvent>() 
    	{
    		@Override
			public void handle(ActionEvent pActionEvent)
			{
    			menu.removeItem(getSelected( (ListView<MenuItem>) ((VBox) root.getLeft()).getChildren().get(1)));
    			menu.removeItem(getSelected( (ListView<MenuItem>) ((VBox) root.getCenter()).getChildren().get(1)));
    			menu.removeItem(getSelected( (ListView<MenuItem>) ((VBox) root.getRight()).getChildren().get(1)));
    			refresh();
			}
		});
    	MenuEditer.getChildren().add(RemoveButton);
    	root.setBottom(MenuEditer);
    }
    
//----------Menu display can be customized here by changing the titles in createControl box and the views corresponding to each button----------------
    private HBox controls(BorderPane root, MenuView view) {
    	// Create controls
    	HBox controls = createControl("Drinks|Main|Snacks", "Cheapest|All|Combo", "Vegetarian|Vegan|GlutenFree");
    	
    	// Set up view for each button
    	((RadioButton)controls.getChildren().get(0)).setOnAction(new EventHandler<ActionEvent>() 
    	{
			@Override
			public void handle(ActionEvent pActionEvent)
			{StandardConfig(root, view, SortingStrategy.ByIncreasingPrice());}

    	});
    	
    	((RadioButton)controls.getChildren().get(1)).setOnAction(new EventHandler<ActionEvent>() 
    	{
			@Override
			public void handle(ActionEvent pActionEvent)
			{CheapestandExpensivest(root, view, 5);}
    	});
    	
    	((RadioButton)controls.getChildren().get(2)).setOnAction(new EventHandler<ActionEvent>() 
    	{
			@Override
			public void handle(ActionEvent pActionEvent)
			{DietaryTypes(root, view, SortingStrategy.ByIncreasingPrice());}
    	});
    	
    	return controls;
    }
    
    private Button specialButton(BorderPane root){
    	Button special = new Button("Put on Special");
    	special.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent arg0)
			{
				
				
				MenuItem aItem = null;
				
				if(getSelected( (ListView<MenuItem>) ((VBox) root.getLeft()).getChildren().get(1)) !=null) {
					aItem = getSelected( (ListView<MenuItem>) ((VBox) root.getLeft()).getChildren().get(1));
				}else if(getSelected( (ListView<MenuItem>) ((VBox) root.getCenter()).getChildren().get(1)) !=null){
					aItem =  getSelected( (ListView<MenuItem>) ((VBox) root.getCenter()).getChildren().get(1));
				}else if(getSelected( (ListView<MenuItem>) ((VBox) root.getRight()).getChildren().get(1)) != null) {
					aItem = getSelected( (ListView<MenuItem>) ((VBox) root.getRight()).getChildren().get(1));
				}
				
				if(aItem == null)return;
				
				SpecialDecorator specialDecorator = new SpecialDecorator(0.9,aItem);
				menu.removeItem(aItem);
				menu.addItem(specialDecorator);
				refresh();
			}
		});
    	
    	return special;
    	
    }
    
    
    

	 private void refresh()
    {
    	StandardConfig(root, view, SortingStrategy.ByIncreasingPrice());
		((RadioButton)controls.getChildren().get(0)).setSelected(true);
    }
}
