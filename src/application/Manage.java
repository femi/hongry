package application;

import java.util.HashMap;

// We should make one manage object when the program is started
// Need to think how I'm saving and loading data 
// At the moment all of the is stored in objects in memory 
// However maybe it makes more sense to have it stored in files 
// And then loaded, modified and deleted by accessing a json file 

// Questions I need to ask the Lecturer?



// This function is used to manage all of the orders
// You can find an order here by passing 

public class Manage {
	
	public static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
	
	public Manage(Order order, int order_id) {
		orders.put(order_id, order);
	}
	
	// This function gets all of the orders from the function
	public HashMap<Integer, Order> getAllOrders() {
		return orders;
	}
	
	// This method gets a specific order 
	public Order getOrder(int order_id) {
		return this.getAllOrders().get(order_id);
	}
}
