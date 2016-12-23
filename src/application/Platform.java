package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Platform {
	
	private static HashMap<Integer, Orders> orders = new HashMap<Integer, Orders>();
	private static HashMap<String, Employees> employees = new HashMap<String, Employees>();
	private static HashMap<Integer, Tables> tables = new HashMap<Integer, Tables>();
	private static Employees loggedIn;

	private static SceneController scene = new SceneController();
	
	public static ObservableList<String> tablesOlist = FXCollections.observableArrayList();
	
	//-------------------------ORDERS-----------------------------
	
	// add order object to store
	public static void putOrder(Orders order, int order_id) {
		orders.put(order_id, order);
		
		// Logging
		Platform.getLoggedIn().addToLog("EMPLOYEE CREATED ORDER: " + order_id);
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
		
		// Logging
		Platform.getLoggedIn().addToLog("EMPLOYEE REMOVED EMPLOYEE: " + username);
	}
	
	public static Employees getLoggedIn() {
		return loggedIn;
	}

	public static void setLoggedIn(Employees loggedIn) {
		Platform.loggedIn = loggedIn;
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


	
	public static int getTotal() {
		int total = 0;
		for (Orders order : getAllOrders().values()) {
			total += order.getOrderTotal();
		}
		return total;
	}
	

	public static void removeOrder(Integer orderID) {
		orders.remove(orderID);
		System.out.println("ORDER " + orderID + " REMOVED FROM STORE");
		
		// Logging
		Platform.getLoggedIn().addToLog("EMPLOYEE REMOVED ORDER: " + orderID);
	}
	
	
	//-------------------------EXPORT---------------------------------
	
	public static void exportToFile(ObservableList<Orders> orders) throws IOException {
		
		// get all of the current orders in the platform 
		//Collection<Orders> orders = Platform.getAllOrders().values();
		
		// use this to hold all of the line
		ArrayList<String []> lines = new ArrayList<String []>();
		
		for (Orders order : orders) {
			
			String orderID;
			String tableID;
			String date;
			String items = "";
			String line = "";
			String [] record;
			
			orderID = order.getOrderID() + "";
			tableID = order.getTableNumber() + "";
			date = order.getTimeOfOrder();
			
			for (ItemBuffer item : order.getMoreOrderContents()) {
				items += item.getItem() + "-";
			}
			
			line = orderID + "," + tableID + "," + date + "," + items;
			System.out.println(line);
			record = line.split(",");
			lines.add(record);
		}
		
		System.out.println(lines.toString());
		
		createCSV(lines);
		
		// Logging
		Platform.getLoggedIn().addToLog("EMPLOYEE CREATED EXPORT");
	}
	
	
	private static void createCSV(ArrayList<String[]> records) throws IOException {
		
	    String csv = "./data/orders.csv";
	    CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
	    
	    for (String [] record : records) {
	    	writer.writeNext(record);
	    }
	    
	    writer.close();

	}

	public static SceneController getScene() {
		return scene;
	}

	public static void setScene(SceneController scene) {
		Platform.scene = scene;
	}
	
	public static void readRecords(String path) throws IOException {
		
		CSVReader reader = new CSVReader(new FileReader(path));
		
		String[] record = null;
		
		while ((record = reader.readNext()) != null) {
			
			// create new order and set table number to 0
			Orders order = new Orders(0);
			
			// set the order id
			order.setOrderID(Integer.parseInt(record[0]));
			// set the time of the order
			order.setTimeOfOrder(record[2]);

			// split the list of items into individual ones
			String[] items = record[3].split("-");
			
			for (int i = 0; i < items.length; i++) {
				order.addItemBuffer(new ItemBuffer(items[i], Items.getItemPrice(items[i]), "1"));
			}
			
			Platform.putOrder(order, order.getOrderID());

		}

		// Logging
		Platform.getLoggedIn().addToLog("EMPLOYEE IMPORTED ORDERS");
	}
	
}
