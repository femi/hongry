package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class NewOrderController implements Initializable {
	
	@FXML private Label total;
	@FXML private ComboBox<String> cbItems, cbTables;
	@FXML private TextField txtQuantity;
	@FXML private Button btnOrder;
	@FXML private TableView<ItemBuffer> orderTable;
	@FXML private TableColumn<ItemBuffer, String> quantityColumn;
	@FXML private TableColumn<ItemBuffer, String> itemColumn;
	@FXML private TableColumn<ItemBuffer, Integer> priceColumn;
	@FXML private TextArea txtComments;
	
	public ObservableList<ItemBuffer> itemList = FXCollections.observableArrayList();
	public HashMap<String, Integer> orderList2 = new HashMap<String, Integer>();
	public int table;
	public int subTotal = 0;
	
	//----------------------------------EXPERIMENTAL-----------------------------------
	//---------------------------------------------------------------------------------
	
	// List to store all of the items the user would like to order
	public ArrayList<ItemBuffer> exprimentOrderList = new ArrayList<ItemBuffer>();
	
	//---------------------------------------------------------------------------------	
	//---------------------------------------------------------------------------------
	
	// Contains a list of items that are available for the user to select from 
	ObservableList<String> dropdownList = FXCollections.observableArrayList(Items.items.keySet());
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		txtQuantity.setText("1");
		txtQuantity.setDisable(true);
		
		// create a TableView of the items that are currently in a persons order 
		//orderTable.setItems(itemList);
		
		// assign the variables to the columns in the TableView
		quantityColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, Integer>("price"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("item"));
		
		// add all items (foods) to the combo list
		cbItems.setItems(dropdownList);
		
		// Show all of the tables that are currently available
		cbTables.setItems(hasOrders(Platform.getAllTables()));
			
	}
	
	public void Home(ActionEvent event) throws IOException {
		
		// go to homepage
		Platform.getScene().home();
	}
	
	public void makeOrder(ActionEvent event) throws IOException {
		
		// get the selected table number
		String tableNumber = cbTables.getSelectionModel().getSelectedItem(); 
		table = Integer.parseInt(tableNumber);
		
		// create a new order
		Orders order = new Orders(table); 
		
		// add all items to the order 
		order.addMultipleOrderItems2(orderList2); // add all items to order // hashmap version // removed this, duplicated items
		order.addMultipleItemBuffer(exprimentOrderList);
		
		// add text from text area to the order
		order.comments(txtComments.getText());
		
		// display order receipt in console 
		order.displayOrder(); 
		
		//--------------------------------------------------------
		
		// remove all items from the current order list 
		exprimentOrderList.removeAll(exprimentOrderList);
		
		// add the order to platform
		Platform.putOrder(order, order.getOrderID()); 
		
		// update table with order number in platform
		Platform.getTable(table).orderID = order.getOrderID(); 
		
		// set the current table to 0
		table = 0;
		
		// go to homepage
		Platform.getScene().home();
	}
	
	public void changeCombo(ActionEvent event) {
		btnOrder.setDisable(false);
	}
	
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

		// add the the price of the item to the current total
		subTotal += Items.getItemPrice(text) * (quantity2);
		
		// update the total label
		total.setText("" + subTotal + ".00");
	
		// add the selected item to the TableView
		orderTable.getItems().add(item);
		
		// if the order is empty do not allow the user to add a table 
		if (!orderTable.getItems().isEmpty()) {
			cbTables.setDisable(false);
		}	
	}
	
	public ObservableList<String> hasOrders(HashMap<Integer, Tables> map) {
		
		// a list that contains all of the tables that have orders
		ObservableList<String> tablesOlist = FXCollections.observableArrayList();
		for (Map.Entry<Integer, Tables> table : map.entrySet()) {
			if (table.getValue().orderID == 0) {
				tablesOlist.add(table.getKey().toString());
			}
		}
		return tablesOlist;
	}
}