package application;

import java.util.HashMap;

public class Platform {
	
	private static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
	private static HashMap<String, Employee2> employees = new HashMap<String, Employee2>();
	private static HashMap<Integer, Table> tables = new HashMap<Integer, Table>();
	private static String loggedIn;
	
	//-------------------------ORDERS-----------------------------
	
	// add order object to store
	public static void putOrder(Order order, int order_id) {
		orders.put(order_id, order);
	}
	
	// get specific order 
	public static Order getOrder(int order_id) {
		return orders.get(order_id);
	}
	
	//-------------------------EMPLOYEES-----------------------------	

	// get specific employee
	public static Employee2 getEmployee(String username) {
		return employees.get(username);
	}
	
	// get all employee
	public static HashMap<String, Employee2> getAllEmployee() {
		return employees;
	}
	
	
	// add employee object to store
	public static void putEmployee(Employee2 employee, String username) {
		employees.put(employee.getEmployeeUsername(), employee);
	}
	
	//-------------------------TABLES-----------------------------

	// add specific table
	public static void putTable(int table_id, Table table) {
		tables.put(table_id, table);
	}
	
	// get specific table
	public static Table getTable(int table_id) {
		return tables.get(table_id);
	}
	
	//--------------------------------------------------------------

	public static void loggedInUser(String type) {
		loggedIn = type;
	}
	
	public static String whosLoggedIn() {
		return loggedIn;
	}
}
