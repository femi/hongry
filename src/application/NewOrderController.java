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

	public ArrayList<ItemBuffer> exprimentOrderList = new ArrayList<ItemBuffer>();
	
	//---------------------------------------------------------------------------------	
	//---------------------------------------------------------------------------------

	
	

	// Contains a list of current items and quantity to be added to a users order [[Salmon, 2], [Steak, 1]]
	public ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
	
	// Contains a list of items that are available for the user to select from 
	ObservableList<String> olist = FXCollections.observableArrayList(Items.items.keySet());
	
	//ObservableList<String> tablesOlist = FXCollections.observableArrayList();
	
	public ObservableList<String> hasOrders(HashMap<Integer, Tables> map) {
		ObservableList<String> tablesOlist = FXCollections.observableArrayList();
		for (Map.Entry<Integer, Tables> table : map.entrySet()) {
			if (table.getValue().orderID == 0) {
				tablesOlist.add(table.getKey().toString());
			}
		}
		return tablesOlist;
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		txtQuantity.setText("1");
		txtQuantity.setDisable(true);
		
		// create a TableView of the items that are currently in a persons order 
		orderTable.setItems(itemList);
		
		// assign the variables to the columns in the TableView
		quantityColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, Integer>("price"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("item"));
		
		// add all items (foods) to the combo list
		cbItems.setItems(olist);
		
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
		
		//order.addMultipleOrderItems(orderList); // add all items to order
		order.addMultipleOrderItems2(orderList2); // add all items to order // hashmap version
		
		// add text from text area to the order
		order.comments(txtComments.getText());
		
		
		//----------------------------------EXPERIMENTAL-----------------------------------
		//---------------------------------------------------------------------------------

		order.addMultipleItemBuffer(exprimentOrderList);
		exprimentOrderList.removeAll(exprimentOrderList);
		
		//---------------------------------------------------------------------------------	
		//---------------------------------------------------------------------------------

		
		
		// add the order to platform
		Platform.putOrder(order, order.getOrderID()); 
		
		// display order receipt in console 
		order.displayOrder(); 
		
		// update table with order number in platform
		Platform.getTable(table).orderID = order.getOrderID(); 
		
		// CLEANUP: clear order list file
		orderList.removeAll(orderList);
		
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
		String quantity = txtQuantity.getText();
		int quantity2 = Integer.parseInt(txtQuantity.getText()); //hashmap version
				
		// Add the selected item to a list that will be added to the order
		ArrayList<String> order = new ArrayList<String>();
		order.add(text); 
		order.add(quantity);
		orderList.add(order); // add item and quantity pair to orderList
		
		// USE HASHMAP INSTEAD
		
		////---------
		if (orderList2.containsKey(text)) {
			orderList2.put(text, orderList2.get(text) + quantity2);
		}
		
		else {
			orderList2.put(text, quantity2);
		}
		//-------------
		
		// Allow user to see what has been added to their order
		//ItemBuffer item = new ItemBuffer(text, Items.getItemPrice(text), orderList2.get(text).toString());
		ItemBuffer item = new ItemBuffer(text, Items.getItemPrice(text), quantity);
		
		
		//----------------------------------EXPERIMENTAL-----------------------------------
		//---------------------------------------------------------------------------------
		
		exprimentOrderList.add(item);
		
		//---------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------

		
		// add the the price of the item to the current total
		subTotal += Items.getItemPrice(text) * Integer.parseInt(quantity);
		
		// update the total label
		total.setText("" + subTotal + ".00");
	
		// add the selected item to the TableView
		orderTable.getItems().add(item);
		
		// if the order is empty do not allow the user to add a table 
		if (!orderTable.getItems().isEmpty()) {
			cbTables.setDisable(false);
		}	
	}	
}