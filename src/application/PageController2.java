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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PageController2 implements Initializable {
	
	@FXML 
	private Label total;
	
	public int subTotal = 0;
	
	@FXML
	private ComboBox<String> cbItems, cbTables;
	
	@FXML
	private TextField txtQuantity;
	
	@FXML
	private Button btnOrder;
	
	public int table;
	
	@FXML
	private TableView<ItemBuffer> orderTable;
	
	@FXML
	private TableColumn<ItemBuffer, String> quantityColumn;
	
	@FXML
	private TableColumn<ItemBuffer, String> itemColumn;
	
	@FXML
	private TableColumn<ItemBuffer, Integer> priceColumn;
	

	public ObservableList<ItemBuffer> itemList = FXCollections.observableArrayList();
	

	public HashMap<String, Integer> orderList2 = new HashMap<String, Integer>();
	
	
	
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
		
		orderTable.setItems(itemList);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, Integer>("price"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("item"));
		
		// add all items (foods) to the combo list
		cbItems.setItems(olist);
		// add current available tables to combo list
		//System.out.println(Platform.getAllTables());
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
		//order.addMultipleOrderItems(orderList); // add all items to order
		order.addMultipleOrderItems2(orderList2); // add all items to order // hashmap version
		Platform.putOrder(order, order.getOrderID()); // add order to platform
		order.displayOrder(); // displays order receipt in console 
		
		Platform.getTable(table).orderID = order.getOrderID(); // update table with order number in platform
		
		//cleanup, clear order list file and text area 
		orderList.removeAll(orderList);
	//	txtTextArea.clear();
		table = 0;
		MainController.goHome(); // go to homepage
	}
	
	public void changeCombo(ActionEvent event) {
		btnOrder.setDisable(false);
	}
	
	public void addItem(ActionEvent event) {
		
		
		String text = cbItems.getSelectionModel().getSelectedItem();
		String quantity = txtQuantity.getText();
		int quantity2 = Integer.parseInt(txtQuantity.getText());
				
		// Add the selected item to a list that will be added to the order
		ArrayList<String> order = new ArrayList<String>();
		order.add(text); 
		order.add(quantity);
		orderList.add(order); // add item and quantity pair to orderList
		
		
		// Using hashmap instead
		
		////---------
		if (orderList2.containsKey(text)) {
			orderList2.put(text, orderList2.get(text) + quantity2);
		}
		
		else {
			orderList2.put(text, quantity2);
		}
		//-------------
		
		// Allow user to see what has been added to their order
//		ItemBuffer item = new ItemBuffer(text, Items.getItemPrice(text), orderList2.get(text).toString());
		ItemBuffer item = new ItemBuffer(text, Items.getItemPrice(text), quantity);
		
		subTotal += Items.getItemPrice(text) * Integer.parseInt(quantity);
		
		total.setText("" + subTotal + ".00");
		//total.setVisible(true);
		
		orderTable.getItems().add(item);
		
		// if the order is empty do not allow the user to add a table 
		if (!orderTable.getItems().isEmpty()) {
			cbTables.setDisable(false);
		}	
		
	}
	
}
