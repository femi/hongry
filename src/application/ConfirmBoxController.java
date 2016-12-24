package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class ConfirmBoxController implements Initializable {
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void deleteOrderConformation(ActionEvent event)  {
				
		//create order object 
		Orders orderSelected;
		
		// put the current order selected into this variable 
		orderSelected = Variables.getOrderSelected();
		
		// remove this order from the table view 
		//allOrders.remove(orderSelected);
		Variables.getMasterData().remove(Variables.getSourceIndex());
		
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