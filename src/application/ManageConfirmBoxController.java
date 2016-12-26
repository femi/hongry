package application;

import javafx.event.ActionEvent;

/**
 * 
 * This class controls the deletion confirmation box page.
 * 
 * @author femi
 *
 */
public class ManageConfirmBoxController {
	
	
	/**
	 * 
	 * Gets the selected order and deletes it from the platform.
	 * 
	 * @param event
	 */
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
	
	/**
	 * 
	 * Closes the deletion confirmation box window
	 * 
	 * @param event
	 */
	public void cancel(ActionEvent event) {
		ManageOrderController.getWindow().close();
	}
	
}