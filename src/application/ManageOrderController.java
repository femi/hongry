package application;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import com.opencsv.CSVWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageOrderController implements Initializable  {

	public static boolean answer;
	private static Stage window;
	@FXML private TableView<Orders> tvOrderTable;
	@FXML private TableColumn<Orders, Integer> id;
	@FXML private TableColumn<Orders, Integer> tableNumber;
	@FXML private TableColumn<Orders, String> date;
	@FXML private TableColumn<Orders, String> orderTotal;
	@FXML private Button delete;
	@FXML private Button modify;
	
	// List of all of the orders that are in the platform 
	public ObservableList<Orders> orders = FXCollections.observableArrayList(Platform.getAllOrders().values());
	
	// tvOrderTable.getSelectionModel().getSelectedItems().size() != 1
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// allows user to select multiple items
		tvOrderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// listens to see if an item in the table is selected 
		tvOrderTable.getSelectionModel().selectedItemProperty().addListener((order) -> {
			// if an item is selected then we enable the delete and modify buttons
			if ( !tvOrderTable.getSelectionModel().isEmpty() ) {
				modify.setDisable(false);
				delete.setDisable(false);
			}
			
			else {
				modify.setDisable(true);
				delete.setDisable(true);
			}
		});
		
		
		// put all of the orders into the TableView 
		tvOrderTable.setItems(orders);
		
		// assign the object variables to the table columns
		id.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("orderID"));
		tableNumber.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("tableNumber"));
		date.setCellValueFactory(new PropertyValueFactory<Orders, String>("timeOfOrder"));
		//orderTotal.setCellValueFactory(new PropertyValueFactory<Orders, String>("orderTotal"));
		orderTotal.setCellValueFactory(new PropertyValueFactory<Orders, String>("experimentalOrderTotal"));
		
		//Variables.setOrderSelected(tvOrderTable.getSelectionModel().getSelectedItem());
		//orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
	
		
	}
	
//	public void deleteOrder(ActionEvent event)  {
//		
//		// create a list to hold all of the orders 
//		ObservableList<Orders> allOrders;
//		
//		//create order object 
//		Orders orderSelected;
//		
//		// get all of the current orders in the TableView
//		allOrders = tvOrderTable.getItems();
//		
//		// put the current order selected into this variable 
//		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
//		
//		// remove this order from the table view 
//		allOrders.remove(orderSelected);
//		
//		//---------------------------------------------------------
//
//		// set the table number of the order to 0 (free table)
//		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
//		
//		// remove the table number from the order object 
//		orderSelected.setTableNumber(0);
//
//		// remove the order from the platform 
//		Platform.removeOrder(orderSelected.getOrderID());
//		
//		// Close the stage 
//		//closeStage(window);
//	
//	}
	
	public void Home(ActionEvent event) {
		
		// get the primary stage from the main class
		Stage primaryStage = Main.getStage();
		
		// set the scene to the main home screen 
		primaryStage.setScene(MainPageController.getHomeScene());
	}
	
	
	public void deleteConformation(ActionEvent event ) throws IOException {
		
		Variables.setOrderSelected(tvOrderTable.getSelectionModel().getSelectedItem());
		Variables.setAllOrders(tvOrderTable);
		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/application/ConfirmBox.fxml"));
		Scene scene = new Scene(root, 300, 200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

	public void modifyOrder(ActionEvent event) throws IOException {
		
		//create order object 
		Orders orderSelected;
				
		// put the current order selected into this variable 
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
		
		//System.out.println(Platform.getOrder(orderSelected.getOrderID()).getMoreOrderContents());
		
		Variables.setOrder(orderSelected.getOrderID());
		
		Stage primaryStage = Main.getStage();
		FXMLLoader loader =  new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/application/ModifyOrder.fxml").openStream());
		ModifyOrderController controller = (ModifyOrderController)loader.getController();
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
	}
	
	public void closeOrder(ActionEvent event) {
		
		//create order object 
		Orders orderSelected;
						
		// put the current order selected into this variable 
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
		
		// set the order table to 0 
		if (orderSelected.getTableNumber() != 0) {
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		}
		
		orderSelected.setTableNumber(0);
		
		// refreshes the table to update the available ones
		tvOrderTable.refresh();
		
	}

	public static Stage getWindow() {
		return window;
	}
	
	public void exportSelectedItems(ActionEvent event) throws IOException { 
		
		//create order object 
		ObservableList<Orders> ordersSelected;
		
		// put the current order selected into this variable 
		ordersSelected = tvOrderTable.getSelectionModel().getSelectedItems();
		
		// export the data to csv
		Platform.exportToFile(ordersSelected);
		
		// to improve this, I could add a file chooser so the user can select .csv destination 
		
	}

	
}
