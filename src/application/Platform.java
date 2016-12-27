package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.collections.ObservableList;

/**
 * 
 * This static class contains methods that allow the ability to 
 * store all of Orders, Tables and Employees objects in one space
 * which can be accessed freely across the platform.
 * 
 * @author femi
 *
 */
public class Platform {
	
	private static HashMap<Integer, Orders> orders = new HashMap<Integer, Orders>();
	private static HashMap<String, Employees> employees = new HashMap<String, Employees>();
	private static HashMap<Integer, Tables> tables = new HashMap<Integer, Tables>();
	private static Employees loggedIn; // stores  current employee logged into platform
	private static SceneController scene = new SceneController(); 
	
	//-------------------------ORDERS-----------------------------
	
	/**
	 *
	 * Adds an order to be stored in the platform HashTable.
	 * 
	 * @param order the current order
	 * @param order_id the order number 
	 */
	public static void putOrder(Orders order, int order_id) {
		orders.put(order_id, order);
		
		// Add to the log of the employee currently logged in
		Platform.getLoggedIn().addToLog("EMPLOYEE CREATED ORDER: " + order_id);
	}
	
	/**
	 * 
	 * Gets the order object given the order ID number.
	 * 
	 * @param orderID the order identifier
	 * @return the order object 
	 */
	public static Orders getOrder(int orderID) {
		return orders.get(orderID);
	}
	
	/**
	 * 
	 * Gets a hashmap of all of the orders in the platform 
	 * 
	 * @return a hashmap of the orders 
	 */
	public static HashMap<Integer, Orders> getAllOrders() {
		return orders;
	}
	
	/**
	 * 
	 * Gets the total cost of orders currently in the platform.
	 * 
	 * @return the total cost 
	 */
	public static int getTotal() {
		int total = 0;
		for (Orders order : getAllOrders().values()) {
			total += order.getOrderTotal();
		}
		return total;
	}
	
	/**
	 * 
	 * Removed an order from the platform given an orderID.
	 * 
	 * @param orderID of an order
	 */
	public static void removeOrder(Integer orderID) {
		orders.remove(orderID);
		System.out.println("ORDER " + orderID + " REMOVED FROM STORE");
		
		// Logging
		Platform.getLoggedIn().addToLog("EMPLOYEE REMOVED ORDER: " + orderID);
	}
	
	//-------------------------EMPLOYEES-----------------------------	

	/**
	 * 
	 * Returns an employee object given a username as a key
	 * 
	 * @param username of an employee
	 * @return employee object 
	 */
	public static Employees getEmployee(String username) {
		return employees.get(username);
	}
	
	/**
	 * 
	 * Gets a hashmap of all of the employees on the platform.
	 * 
	 * @return all of the employees 
	 */
	public static HashMap<String, Employees> getAllEmployee() {
		return employees;
	}
	
	/**
	 * 
	 * Adds an employee to the platform.
	 * 
	 * @param employee
	 * @param username 
	 */
	public static void putEmployee(Employees employee, String username) {
		employees.put(employee.getUsername(), employee);
	}
	
	/**
	 * 
	 * Removes an employee from the platform.
	 * 
	 * @param username
	 */
	public static void removeEmployee(String username) {
		employees.remove(username);
		System.out.println("EMPLOYEE " + username + " REMOVED FROM PLATFORM");
		
		// Add to the log of the employee currently logged in
		Platform.getLoggedIn().addToLog("EMPLOYEE REMOVED EMPLOYEE: " + username);
	}
	
	/**
	 * 
	 * Returns the employee that is currently logged in.
	 * 
	 */
	public static Employees getLoggedIn() {
		return loggedIn;
	}

	/**
	 * 
	 * Sets the current employee logged into the platform.
	 * 
	 * @param loggedIn employee logged into application
	 */
	public static void setLoggedIn(Employees loggedIn) {
		Platform.loggedIn = loggedIn;
	}

	//-------------------------TABLES-----------------------------

	
	/**
	 * 
	 * Adds a table to the platform given the table ID and Table object.
	 * 
	 * @param table_id table number 
	 * @param table object 
	 */
	public static void putTable(int table_id, Tables table) {
		tables.put(table_id, table);
	}
	

	/**
	 * 
	 * Returns a table given the table id.
	 * 
	 * @param table_id
	 * @return table
	 */
	public static Tables getTable(int table_id) {
		return tables.get(table_id);
	}
	
	/**
	 * 
	 * Returns a hashmap of all of the tables in the platform.
	 * 
	 * @return
	 */
	public static HashMap<Integer, Tables> getAllTables() {
		return tables;
	}
	
	//----------------------MANAGING SCENES-----------------------
	
	/**
	 * 
	 * Returns an instance of the Scene Controller.
	 * 
	 * @return
	 */
	public static SceneController getScene() {
		return scene;
	}

	/**
	 * 
	 * Sets the scene controller.
	 * 
	 * @param scene page that load FXML file
	 */
	public static void setScene(SceneController scene) {
		Platform.scene = scene;
	}

	//----------------------EXPORT + IMPORT-----------------------
	
	/**
	 * 
	 * Exports selected orders to a CSV file with a location path 
	 * selected by the user.
	 * 
	 * @param orders The selected orders that the user wants to export
	 * @param path This is the  path where user wants to export to 
	 * @throws IOException is called if file cannot be found
	 */
	public static void exportToFile(ObservableList<Orders> orders, String path) throws IOException {
				
		// use this to hold all of the lines
		ArrayList<String []> lines = new ArrayList<String []>();
		
		for (Orders order : orders) {
			
			String orderID, tableID, date;
			String items = "";
			String line = "";
			String [] record;
			
			orderID = order.getOrderID() + "";
			tableID = order.getTableNumber() + "";
			date = order.getTimeOfOrder();
			
			for (ItemObject item : order.orderItemObjects()) {
				items += item.getName() + "-";
			}
			
			line = orderID + "," + tableID + "," + date + "," + items;
			System.out.println(line);
			record = line.split(",");
			lines.add(record);
		}
		
		System.out.println(lines.toString());
		
		createCSV(lines, path);
		
		// Logging
		Platform.getLoggedIn().addToLog("EMPLOYEE EXPORTED ORDERS TO CSV");
	}
	

	/**
	 * 
	 * Create as CSV file of selected orders and allow them to be 
	 * written to a custom path.
	 * 
	 * @param records that will be created
	 * @param path that the file will be saved to 
	 * @throws IOException if no file is found 
	 */
	private static void createCSV(ArrayList<String[]> records, String path) throws IOException {
	    
	    // add orders to existing file
	    CSVWriter writer = new CSVWriter(new FileWriter(path));
	    
	    for (String [] record : records) {
	    	writer.writeNext(record);
	    }
	    
	    writer.close();
	}

	/**
	 * 
	 * The method parses a CSV file and creates Orders objects to be 
	 * added to the platform. The method populates the order with 
	 * information such a the date, table number (0 = closed) and the 
	 * items in the order
	 * 
	 * @param path This is where the file will be read from
	 * @throws IOException if the file cannot be found
	 */
	public static void parseCSV(String path) throws IOException {
		
		CSVReader reader = new CSVReader(new FileReader(path));
		
		String[] record = null;
		
		while ((record = reader.readNext()) != null) {
			
			// create new order object and set table number to 0
			// orders with tables set to 0 are closed
			Orders order = new Orders(0);
			
			// set the order id
			order.setOrderID(Integer.parseInt(record[0]));
			
			// set the time of the order
			order.setTimeOfOrder(record[2]);

			// split the list of items into individual ones
			String[] items = record[3].split("-");
			
			// create a new object for each item in the order 
			for (int i = 0; i < items.length; i++) {
				order.addItemBuffer(new ItemObject(items[i], Items.getItemPrice(items[i]), "1"));
			}
			
			// add the imported order to the platform 
			Platform.putOrder(order, order.getOrderID());
		}
		
		reader.close();

		// Logging
		Platform.getLoggedIn().addToLog("EMPLOYEE IMPORTED ORDERS");
	}
}