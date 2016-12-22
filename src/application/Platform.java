package application;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Platform {
	
	private static HashMap<Integer, Orders> orders = new HashMap<Integer, Orders>();
	private static HashMap<String, Employees> employees = new HashMap<String, Employees>();
	private static HashMap<Integer, Tables> tables = new HashMap<Integer, Tables>();
	private static String loggedIn;
	
	public static ObservableList<String> tablesOlist = FXCollections.observableArrayList();
	
	//-------------------------ORDERS-----------------------------
	
	// add order object to store
	public static void putOrder(Orders order, int order_id) {
		orders.put(order_id, order);
	}
	
	// get specific order 
	public static Orders getOrder(int order_id) {
		return orders.get(order_id);
	}
	
	// get all orders
	public static HashMap<Integer, Orders> getAllOrders() {
		return orders;
	}
	
	//-------------------------EMPLOYEES-----------------------------	

	// get specific employee
	public static Employees getEmployee(String username) {
		return employees.get(username);
	}
	
	// get all employee
	public static HashMap<String, Employees> getAllEmployee() {
		return employees;
	}
	
	
	// add employee object to store
	public static void putEmployee(Employees employee, String username) {
		employees.put(employee.getEmployeeUsername(), employee);
	}
	
	public static void removeEmployee(String username) {
		employees.remove(username);
		System.out.println("EMPLOYEE " + username + " REMOVED FROM PLATFORM");
	}
	
	//-------------------------TABLES-----------------------------

	// add specific table
	public static void putTable(int table_id, Tables table) {
		tables.put(table_id, table);
	}
	
	// get specific table
	public static Tables getTable(int table_id) {
		return tables.get(table_id);
	}
	
	// get specific table
	public static HashMap<Integer, Tables> getAllTables() {
		return tables;
	}
	
	//--------------------------------------------------------------

	public static void loggedInUser(String type) {
		loggedIn = type;
	}
	
	public static String whosLoggedIn() {
		return loggedIn;
	}
	public static void removeOrder(Integer orderID) {
		orders.remove(orderID);
		System.out.println("ORDER " + orderID + " REMOVED FROM STORE");
	}
	
	
	//-------------------------EXPORT---------------------------------
	
	public static void exportOrder() {
		
	}
	
}
