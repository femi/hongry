package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageOrderController implements Initializable  {

	@FXML
	private TableView<Order> tvOrderTable;
	@FXML
	private TableColumn<Order, Integer> id;
	@FXML
	private TableColumn<Order, Integer> tn;
	@FXML
	private TableColumn<Order, String> date;
	@FXML
	private TableColumn<Order, String> orderTotal;
	
	private Stage window;
	
	// All of the current orders
	public ObservableList<Order> orders = FXCollections.observableArrayList(Platform.getAllOrders().values());
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tvOrderTable.setItems(orders);
		id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
		tn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("tableNumber"));
		date.setCellValueFactory(new PropertyValueFactory<Order, String>("timeOfOrder"));
		orderTotal.setCellValueFactory(new PropertyValueFactory<Order, String>("orderTotal"));
		
	}
	
	public void deleteOrder(ActionEvent event) {
		
		ObservableList<Order> allOrders;
		Order orderSelected;
		allOrders = tvOrderTable.getItems();
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
		allOrders.remove(orderSelected);
		
		//---------------------------------------------------------
		
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		orderSelected.setTableNumber(0);
		Platform.removeOrder(orderSelected.getOrderID());
		
		// Close the stage 
		//closeStage(window);
	
	}
	
	public void Home(ActionEvent event) {
		Stage primaryStage = Main.getStage();
		primaryStage.setScene(MainController.getHomeScene());
	}
	
	
	public void conformation(ActionEvent event) throws IOException {

		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/DeleteConformation.fxml"));
		Scene scene = new Scene(root, 300, 200);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
	
	public void closeOrder() {
		
	}
	
	public void modifyOrder() {
		
	}
	
}
