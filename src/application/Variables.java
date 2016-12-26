package application;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * 
 * This static class is used to communicate variables across 
 * new scenes that require data from other scenes.
 * 
 * @author femi
 *
 */
/**
 * @author femi
 *
 */
public class Variables {
	
	public static Integer order;
	
	//------------CONFORMATION BOX-----------
	public static TableView<Orders> allOrders;
	public static Orders orderSelected;
	public static ObservableList<Orders> masterData;
	public static int visibleIndex;
	public static int sourceIndex;
	//---------------------------------------
	
	/**
	 * Get a list of all of the orders in the platform in the 
	 * form of an observable list.
	 * @return
	 */
	public static ObservableList<Orders> getMasterData() {
		return masterData;
	}

	/**
	 * Populate an observable list with all of the current orders
	 * in the platform.
	 * @param masterData all of the order in the platform 
	 */
	public static void setMasterData(ObservableList<Orders> masterData) {
		Variables.masterData = masterData;
	}

	/**
	 * Get the index of of the selected item in the TableView.
	 * @return
	 */
	public static int getVisibleIndex() {
		return visibleIndex;
	}

	/**
	 * Set the index of of the selected item in the TableView.
	 * @param visibleIndex
	 */
	public static void setVisibleIndex(int visibleIndex) {
		Variables.visibleIndex = visibleIndex;
	}

	/**
	 * Get the index of the source in the TableView.
	 * @return
	 */
	public static int getSourceIndex() {
		return sourceIndex;
	}

	/**
	 * Set the index of the source in the TableView.
	 * @param sourceIndex
	 */
	public static void setSourceIndex(int sourceIndex) {
		Variables.sourceIndex = sourceIndex;
	}
	
	/**
	 * Get a TableView which contains orders.
	 * @return
	 */
	public static TableView<Orders> getAllOrders() {
		return allOrders;
	}
	
	
	/**
	 * Set a TableView which contains orders.
	 * @param allOrders
	 */
	public static void setAllOrders(TableView<Orders> allOrders) {
		Variables.allOrders = allOrders;
	}

	/**
	 * Get a specific order.
	 * @return
	 */
	public static Orders getOrderSelected() {
		return orderSelected;
	}

	/**
	 * Set a specific order.
	 * @param orderSelected
	 */
	public static void setOrderSelected(Orders orderSelected) {
		Variables.orderSelected = orderSelected;
	}

	/**
	 * Get the order id of an order.
	 * @return
	 */
	public static int getOrder() {
		return order;
	}
	
	/**
	 * Set the order id of an order.
	 * @param order
	 */
	public static void setOrder(int order) {
		Variables.order = order;
	}
}
