package application;

import javafx.scene.control.TableView;

public class Variables {
	
	public static Integer order;
	
	//------------CONFORMATION BOX-----------
	public static TableView<Orders> allOrders;
	public static Orders orderSelected;
	//---------------------------------------
	
	
	
	
	public static TableView<Orders> getAllOrders() {
		return allOrders;
	}

	public static void setAllOrders(TableView<Orders> allOrders) {
		Variables.allOrders = allOrders;
	}

	public static Orders getOrderSelected() {
		return orderSelected;
	}

	public static void setOrderSelected(Orders orderSelected) {
		Variables.orderSelected = orderSelected;
	}

	public static int getOrder() {
		return order;
	}

	public static void setOrder(int order) {
		Variables.order = order;
	}
}
