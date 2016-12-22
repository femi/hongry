package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class ConfirmBoxController implements Initializable {
	
	public TableView<Orders> tvOrderTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}
	
	public void deleteOrder(ActionEvent event)  {
		
		// create a list to hold all of the orders 
		ObservableList<Orders> allOrders;
		
		//create order object 
		Orders orderSelected;
		
		// get all of the current orders in the TableView
		allOrders = Variables.getAllOrders().getItems();
		
		// put the current order selected into this variable 
		orderSelected = Variables.getOrderSelected();
		
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


	


}
