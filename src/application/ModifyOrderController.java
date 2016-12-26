package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author femi
 *
 */
public class ModifyOrderController implements Initializable {
	
	private static Stage window;
	private int orderID = Variables.getOrder();
	private Orders ordermate = Platform.getOrder(orderID);

	@FXML private Label lblOrderNumber, lblTotal;
	@FXML private TableView<ItemBuffer> tvItemTable;
	@FXML private TableColumn<ItemBuffer, String> item;
	@FXML private TableColumn<ItemBuffer, Integer> price;
	@FXML private TableColumn<ItemBuffer, String> quantity;
	@FXML private TextArea txtComments;
	@FXML private ComboBox<String> cbItems;
	@FXML private TextField txtQuantity;
	
	public HashMap<String, Integer> orderList2 = new HashMap<String, Integer>();
	
	// List of all of items in the order
	public ObservableList<ItemBuffer> itemList = FXCollections.observableArrayList(Platform.getOrder(orderID).getMoreOrderContents());
	
	// List to store all of the items in the order 
	public ArrayList<ItemBuffer> exprimentOrderList = ordermate.getMoreOrderContents();
	
	// Contains a list of items that are available for the user to select from 
	ObservableList<String> dropdownList = FXCollections.observableArrayList(Items.items.keySet());
	
	//-
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// display order id on the modify page 
		lblOrderNumber.setText("Order " + orderID);
		lblOrderNumber.setVisible(true);
		
		//lblTotal.setVisible(true);
		lblTotal.setText("£" + ordermate.getExperimentalOrderTotal() + ".00" );
		
		// populate the table with all of the items 
		tvItemTable.setItems(itemList);
		
		// assign the columns
		item.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("name"));
		price.setCellValueFactory(new PropertyValueFactory<ItemBuffer, Integer>("price"));
		quantity.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("quantity"));
		
		// Display comments and special messages
		txtComments.setText(Platform.getOrder(orderID).getComments());
		
		// add all items (foods) to the combo list
		cbItems.setItems(dropdownList);
		txtQuantity.setText("1");
		txtQuantity.setDisable(true);
		cbItems.getSelectionModel().selectFirst();
		

	}
	
	/**
	 * 
	 * Allows the user to delete an item from their order, the method 
	 * deletes the selected item object from the order and removes it 
	 * from the order's TableView.
	 * 
	 * @param event
	 */
	public void deleteItem(ActionEvent event) {
		

		Orders order = Platform.getOrder(orderID);
		
		// create a list to hold all of the Items 
		ObservableList<ItemBuffer> allItems;
				
		//create order object 
		ItemBuffer itemSelected;
				
		// get all of the current orders in the TableView
		allItems = tvItemTable.getItems();
				
		// put the current order selected into this variable 
		itemSelected = tvItemTable.getSelectionModel().getSelectedItem();
				
		// remove this order from the table view 
		allItems.remove(itemSelected);
		
		
		//---------------------------------------------------------
		
		// remove item from order object
		order.removeItemBuffer(itemSelected);
		
		// set the total again
		lblTotal.setText("£" + order.getExperimentalOrderTotal() + ".00" );
	}
	
	
	/**
	 * 
	 * Returns the user to the homepage.
	 * 
	 * @param event
	 * @throws IOException if the FXML page cannot be loaded
	 */
	public void Home(ActionEvent event) throws IOException {
		
		// Apply changes to comments and special messages
		Platform.getOrder(orderID).addComments(txtComments.getText());
		
		// go to homepage 
		Platform.getScene().home();
	
	}
	
	/**
	 * 
	 * Takes the user to the delete confirmation modal window
	 * 
	 * @param event
	 * @throws IOException if the FXML page cannot be loaded 
	 */
	public void deleteConformation(ActionEvent event) throws IOException {
		
		Variables.setOrderSelected(ordermate);

		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/application/ModifyConfirmBox.fxml"));
		Scene scene = new Scene(root, 300, 200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
	
	/**
	 * 
	 * Gets the window for the delete confirmation box.
	 * 
	 * @return the delete confirmation window 
	 */
	public static Stage getWindow() {
		return window;	
	}
	
	
	/**
	 * 
	 * Allows user to add an item to their existing order.
	 * 
	 * @param event
	 */
	public void addItem(ActionEvent event) {
		
		// get the selected item from the drop down menu 
		String text = cbItems.getSelectionModel().getSelectedItem();
		int quantity2 = Integer.parseInt(txtQuantity.getText()); //hashmap version
				
		
		if (orderList2.containsKey(text)) {
			orderList2.put(text, orderList2.get(text) + quantity2);
		}
		
		else {
			orderList2.put(text, quantity2);
		}
		
		// Allow user to see what has been added to their order
		ItemBuffer item = new ItemBuffer(text, Items.getItemPrice(text), quantity2+"");

		// add the item to the order
		exprimentOrderList.add(item);

		// update the total label
		lblTotal.setText("£" + ordermate.getExperimentalOrderTotal() + ".00");
	
		// add the selected item to the TableView
		tvItemTable.getItems().add(item);
		
	}
	
	
}
