package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ExportTester {
	
	public static void main(String[] args) throws Exception {
		
		Main.initialise();
		//newRecords(exportToFile());
		//readRecords();

	}
	
	public static ArrayList<String []> exportToFile() {
		
		// get all of the curent orders in the platform 
		Collection<Orders> orders = Platform.getAllOrders().values();
		
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
		return lines;
	}

	
	public static void newRecord(String[] record) throws IOException {
		
	    String csv = "./data/orders.csv";
	    CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
	        
	    writer.writeNext(record);
	    writer.close();
		
	}
	
	
	public static void newRecords(ArrayList<String[]> records) throws IOException {
		
	    String csv = "./data/orders.csv";
	    CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
	    
	    for (String [] record : records) {
	    	writer.writeNext(record);
	    }
	    
	    writer.close();
		
	}
	
	public static void readRecords() throws IOException {
		
		CSVReader reader = new CSVReader(new FileReader("./data/orders.csv"));
		
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

		
	}
	
	
	
	
	
}


