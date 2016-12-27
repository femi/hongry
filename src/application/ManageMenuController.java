package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * 
 * This class controls the manage menu page.
 * 
 * @author femi
 *
 */
public class ManageMenuController implements Initializable {
	
	@FXML private TextField txtItem, txtPrice;
	@FXML private TableView<ItemObject> tvItems;
	@FXML private TableColumn<ItemObject, String> itemName;
	@FXML private TableColumn<ItemObject, Integer> itemPrice;
	@FXML private Button btnAdd;
	@FXML private Button btnDelete;
	
	// list containing all of the current items in the menu
	public ObservableList<ItemObject> items = FXCollections.observableArrayList(Items.getItemObjects().values());
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// disable the delete button / there's a small bug 
		btnDelete.setDisable(true);
		
		// add menu items to the tableview
		tvItems.setItems(items);
		
		// assign values to the table columns
		itemName.setCellValueFactory(new PropertyValueFactory<ItemObject, String>("name"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<ItemObject, Integer>("price"));
		
	}

	/**
	 * 
	 * Allows user to add a new item to the restaurant menu
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void addNewItem(ActionEvent event) throws Exception {
		
		// get the name of the new item 
		String item = txtItem.getText();
		int price = Integer.parseInt(txtPrice.getText());
		
		// store the item in the Items class
		Items.addItem(item, price);
		
		// add the item to the tableview 
		tvItems.getItems().add(Items.getItemObjects().get(item));
		
	}
	
	
	/**
	 * 
	 * Allows user to delete item from restaurant menu
	 * 
	 * @param event
	 */
	public void deleteItem(ActionEvent event) {
		
		// create a list to hold all of the Items 
		ObservableList<ItemObject> allItems;
				
		//create item object 
		ItemObject itemSelected;
				
		// get all of the current items in the TableView
		allItems = tvItems.getItems();
				
		// put the current item selected into this variable 
		itemSelected = tvItems.getSelectionModel().getSelectedItem();
				
		// remove this item from the table view 
		allItems.remove(itemSelected);
		
		//---------------------------------------------------------
		
		// remove item object
		Items.removeItem(itemSelected.getName());
		
	}
	
	/**
	 * 
	 * Returns the user to the homepage.
	 * 
	 * @param event
	 * @throws IOException when the FXML page cannot be loaded 
	 */
	public void home(ActionEvent event) throws IOException {
		
		// go back to home screen 
		Platform.getScene().home();
		
	}

}
