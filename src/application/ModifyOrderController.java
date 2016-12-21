package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModifyOrderController implements Initializable {
	
	private int orderID = Variables.getOrder();

	@FXML private Label lblOrderNumber;
	@FXML private TableView<ItemBuffer> tvItemTable;
	@FXML private TableColumn<ItemBuffer, String> item;
	@FXML private TableColumn<ItemBuffer, Integer> price;
	@FXML private TableColumn<ItemBuffer, String> quantity;
	
	// List of all of items in the order
	public ObservableList<ItemBuffer> itemList = FXCollections.observableArrayList(Platform.getOrder(orderID).getMoreOrderContents());

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// display order id on the modify page 
		lblOrderNumber.setText("Order " + orderID);
		lblOrderNumber.setVisible(true);
		
		// populate the table with all of the items 
		tvItemTable.setItems(itemList);
		
		// assign the columns
		item.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("item"));
		price.setCellValueFactory(new PropertyValueFactory<ItemBuffer, Integer>("price"));
		quantity.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("quantity"));

	}
	
	public void deleteItem(ActionEvent event) {
		
		Order order = Platform.getOrder(orderID);
		
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
	}
}
