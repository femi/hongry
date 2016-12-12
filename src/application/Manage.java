package application;

import java.util.HashMap;

public class Manage {
	
	public static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
	public static HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>();
	public static HashMap<Integer, Table> tables = new HashMap<Integer, Table>();
	
	public Manage() {
		System.out.println("NEW PLATFROM INITIATED");
	}
	
	//-------------------------ORDERS-----------------------------
	
	// add order object to store
	public void putOrder(Order order, int order_id) {
		orders.put(order_id, order);
	}
	
	// get specific order 
	public Order getOrder(int order_id) {
		return orders.get(order_id);
	}
	
	//-------------------------EMPLOYEES-----------------------------	

	// get specific employee
	public Employee getEmployee(int employee_id) {
		return employees.get(employee_id);
	}
	
	
	// add employee object to store
	public void putEmployee(Employee employee, int employee_id) {
		employees.put(employee_id, employee);
	}
	
	//-------------------------TABLES-----------------------------

	// add specific table
	public void putTable(int table_id, Table table) {
		tables.put(table_id, table);
	}
	
	// get specific table
	public Table getTable(int table_id) {
		return tables.get(table_id);
	}
	
	//--------------------------------------------------------------
	
	
//	// This function gets all of the orders from the function
//	public HashMap<Integer, Order> getAllOrders() {
//		return orders;
//	}
//	
	
//	public Manage(Order order, int order_id) {
//	orders.put(order_id, order);
//}
//
	
}