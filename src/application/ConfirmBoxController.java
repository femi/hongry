package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class ConfirmBoxController implements Initializable {
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}
	
	public void deleteOrderConformation(ActionEvent event)  {
		
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
		// we need this if statement because some orders mat be closed i.e. table = 0
		if (orderSelected.getTableNumber() != 0) {
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		}
		
		// remove the table number from the order object 
		orderSelected.setTableNumber(0);

		// remove the order from the platform 
		Platform.removeOrder(orderSelected.getOrderID());
		
		// Close the stage 
		ManageOrderController.getWindow().close();
	
	}
	
	public void cancel(ActionEvent event) {
		ManageOrderController.getWindow().close();
	}
	

}
