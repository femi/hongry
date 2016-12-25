package application;

import java.io.IOException;
import javafx.event.ActionEvent;

public class ModifyConfirmBoxController  {
	
	
	public void deleteOrderConformation(ActionEvent event) throws IOException  {
				
		Orders orderSelected = Variables.getOrderSelected();
		
		// set the table number of the order to 0 (free table)
		// we need this if statement because some orders mat be closed i.e. table = 0
		if (orderSelected.getTableNumber() != 0) {
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		}
		
		// remove the table number from the order object 
		orderSelected.setTableNumber(0);

		// remove the order from the platform 
		Platform.removeOrder(orderSelected.getOrderID());
		
		// close the modal 
		ModifyOrderController.getWindow().close();
		
		// go to homepage  
		Platform.getScene().home();
	
	}
	
	public void cancel(ActionEvent event) {
		ModifyOrderController.getWindow().close();
	}
	
}