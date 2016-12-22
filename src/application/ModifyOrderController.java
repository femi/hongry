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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModifyOrderController implements Initializable {
	
	private int orderID = Variables.getOrder();
	private Orders ordermate = Platform.getOrder(orderID);

	@FXML private Label lblOrderNumber, lblTotal;
	@FXML private TableView<ItemBuffer> tvItemTable;
	@FXML private TableColumn<ItemBuffer, String> item;
	@FXML private TableColumn<ItemBuffer, Integer> price;
	@FXML private TableColumn<ItemBuffer, String> quantity;
	@FXML private TextArea txtComments;
	
	// List of all of items in the order
	public ObservableList<ItemBuffer> itemList = FXCollections.observableArrayList(Platform.getOrder(orderID).getMoreOrderContents());

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
		item.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("item"));
		price.setCellValueFactory(new PropertyValueFactory<ItemBuffer, Integer>("price"));
		quantity.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("quantity"));
		
		// Display comments and special messages
		txtComments.setText(Platform.getOrder(orderID).getComments());

	}
	
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
	
	
	public void Home(ActionEvent event) {
		
		// Apply changes to comments and special messages
		Platform.getOrder(orderID).comments(txtComments.getText());
		
		// get the primary stage from the main class
		Stage primaryStage = Main.getStage();
		
		// set the scene to the main home screen 
		primaryStage.setScene(MainPageController.getHomeScene());
		
	
	}
	
	
}
