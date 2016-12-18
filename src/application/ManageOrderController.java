package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

}
