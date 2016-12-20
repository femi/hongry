package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageOrderController implements Initializable  {

	public static boolean answer;
	private static Stage window;
	@FXML private TableView<Order> tvOrderTable;
	@FXML private TableColumn<Order, Integer> id;
	@FXML private TableColumn<Order, Integer> tn;
	@FXML private TableColumn<Order, String> date;
	@FXML private TableColumn<Order, String> orderTotal;
	@FXML private Button yesButton;
	@FXML private Button noButton;
	
	// List of all of the orders that are in the platform 
	public ObservableList<Order> orders = FXCollections.observableArrayList(Platform.getAllOrders().values());
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// put all of the orders into the TableView 
		tvOrderTable.setItems(orders);
		
		// assign the object variables to the table columns
		id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
		tn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("tableNumber"));
		date.setCellValueFactory(new PropertyValueFactory<Order, String>("timeOfOrder"));
		orderTotal.setCellValueFactory(new PropertyValueFactory<Order, String>("orderTotal"));
		
	}
	
	public void deleteOrder(ActionEvent event)  {
		
	
		// create a list to hold all of the orders 
		ObservableList<Order> allOrders;
		
		//create order object 
		Order orderSelected;
		
		// get all of the current orders in the TableView
		allOrders = tvOrderTable.getItems();
		
		// put the current order selected into this variable 
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
		
		// remove this order from the table view 
		allOrders.remove(orderSelected);
		
		//---------------------------------------------------------
		
		// set the table number of the order to 0 (free table)
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		
		// remove the table number from the order object 
		orderSelected.setTableNumber(0);
		
		// remove the order from the platform 
		Platform.removeOrder(orderSelected.getOrderID());
		
		// Close the stage 
		//closeStage(window);
	
	}
	
	public void Home(ActionEvent event) {
		
		// get the primary stage from the main class
		Stage primaryStage = Main.getStage();
		
		// set the scene to the main home screen 
		primaryStage.setScene(MainController.getHomeScene());
	}
	
	
	public void deleteConformation(ActionEvent event ) throws IOException {
		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/application/DeleteConformation.fxml"));
		Scene scene = new Scene(root, 300, 200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
//		
//		yesButton = new Button();
//		noButton = new Button();
//		
//		yesButton.setOnAction(e -> {
//			answer = false;
//			window.close();
//		});
//		
//		noButton.setOnAction(e -> {
//			answer = false;
//			window.close();
//		});

	}
	
	public void yesButton() {
		answer = true;
		window.close();
	}
	
	public void noButton() {
		answer = false;
		window.close();
	}
	
//	public void closeOrder() {
//	}
//	
//	public void modifyOrder() {
//	}
	
}
