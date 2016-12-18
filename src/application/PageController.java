package application;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PageController implements Initializable {
	
	@FXML
	private ComboBox<String> cbItems, cbTables;
	
	@FXML
	private TextArea txtTextArea;
	
	@FXML
	private TextField txtQuantity;
	
	@FXML
	private Button btnOrder;
	
	public int table;
	
	// Contains a list of current items and quantity to be added to a users order [[Salmon, 2], [Steak, 1]]
	public ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
	
	// Contains a list of items that are available for the user to select from 
	ObservableList<String> olist = FXCollections.observableArrayList(Items.items.keySet());
	
	//ObservableList<String> tablesOlist = FXCollections.observableArrayList();
	
	public ObservableList<String> hasOrders(HashMap<Integer, Table> map) {
		ObservableList<String> tablesOlist = FXCollections.observableArrayList();
		for (Map.Entry<Integer, Table> table : map.entrySet()) {
			if (table.getValue().orderID == 0) {
				tablesOlist.add(table.getKey().toString());
			}
		}
		return tablesOlist;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// add all items (foods) to the combo list
		cbItems.setItems(olist);
		// add current available tables to combo list
		System.out.println(Platform.getAllTables());
		cbTables.setItems(hasOrders(Platform.getAllTables()));
	}
	
	public void Home(ActionEvent event) {
		Stage primaryStage = Main.getStage();
		primaryStage.setScene(MainController.getHomeScene());
	}
	
	public void makeOrder(ActionEvent event) {
		
		// get the selected table number
		String tableNumber = cbTables.getSelectionModel().getSelectedItem(); 
		table = Integer.parseInt(tableNumber);
		
		Order order = new Order(table); // new order
		order.addMultipleOrderItems(orderList); // add all items to order
		Platform.putOrder(order, order.getOrderID()); // add order to platform
		order.displayOrder(); // displays order receipt in console 
		
		Platform.getTable(table).orderID = order.getOrderID(); // update table with order number in platform
		
		//cleanup, clear order list file and text area 
		orderList.removeAll(orderList);
		txtTextArea.clear();
		table = 0;
		MainController.goHome(); // go to homepage
	}
	
	public void changeCombo(ActionEvent event) {
		btnOrder.setDisable(false);
	}
	
	public void addItem(ActionEvent event) {
		
		//System.out.println(hasOrders(Platform.getAllTables()));
		String text = cbItems.getSelectionModel().getSelectedItem();
		String quantity = txtQuantity.getText();

		ArrayList<String> order = new ArrayList<String>();
		order.add(text); 
		order.add(quantity);
		orderList.add(order); // add item and quantity pair to orderList
		
		// add items to the text area so user can see what they added to their order
		txtTextArea.appendText(text + "    " + quantity + "    £" + Items.getItemPrice(text) * Integer.parseInt(quantity) + "\n");
		
		
		// if the order is empty do not allow the user to add a table 
		if (!txtTextArea.getText().isEmpty()) {
			cbTables.setDisable(false);
		}
		
	}
	
}
