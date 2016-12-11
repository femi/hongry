package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.opencsv.*;


public class Tester {
	
	
//	public static void addItem(HashMap<String, Integer> map, String item, int quantity) {
//
//		if (map.containsKey(item) == true) {
//			int currentQuantity = map.get(item);
//			map.put(item, currentQuantity + quantity);
//		}
//		
//		else { map.put(item, quantity);	}
//		
//	}
//	
//	
	public static void add(HashMap<String, Integer> map, String item, int quantity) {
		map.putIfAbsent(item, quantity);
	}
	
	public static void main(String[] args) throws IOException {

		// TESTING FOR ADDING ITEMS TO AN ORDER
		
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		ArrayList<String> item1 = new ArrayList<String>();
		ArrayList<String> item2 = new ArrayList<String>();
		ArrayList<String> item3 = new ArrayList<String>();
		item1.add("Salmon");
		item1.add("2");
		item2.add("Chicken");
		item2.add("5");
		item3.add("Salmon");
		item3.add("30");
		list.add(item1);
		list.add(item2);
		list.add(item3);
		
		System.out.println(list.toString());

		Order femi = new Order(10);
		femi.addMultipleItems(list);
		//System.out.println(femi.map.get("Salmon"));
		
		System.out.println(femi.map);
		//femi.removeItems("pooooo");
		System.out.println(femi.map);
		
		
		ArrayList<Order> test = new ArrayList<Order>();
		
		// TESTING FOR MODIFYING ITEMS ON AN ORDER
		
		ArrayList<ArrayList> listt = new ArrayList<ArrayList>();
		ArrayList<String> item1t = new ArrayList<String>();
		ArrayList<String> item2t = new ArrayList<String>();
		ArrayList<String> item3t = new ArrayList<String>();
		item1t.add("Salmon");
		item1t.add("1");
		item2t.add("Chicken");
		item2t.add("1");
		item3t.add("Salmon");
		item3t.add("1");
		listt.add(item1t);
		listt.add(item2t);
		listt.add(item3t);
		
		femi.modifyOrder(listt);
		System.out.println(femi.map.get("Salmon"));
		femi.displayOrder();
		
		ArrayList orders = femi.allOrders;
		Order dom = femi;
		test.add(dom);
		System.out.println(test.get(0).getOrderID());
		
		
		
		//femi.removeItems("Salmon");
		
		
		// TESTING FOR REMOVING ITEMS ON AN ORDER
		
		
		
		

		
//		HashMap<String, Integer> map = new HashMap<String, Integer>();

		
//		addItem(map, "Joseph", 1);
//		addItem(map, "Joseph", 6);
//		addItem(map, "Joseph", 1);
//		addItem(map, "Joseph", 1);
//		addItem(map, "Abe", 1);
//		addItem(map, "Drake", 1);
//		addItem(map, "Drake", 1);
//		addItem(map, "Drake", 3);
//		addItem(map, "Drake", 3);
//		addItem(map, "Drake", 3);
//		
//		System.out.println(map.get("Joseph"));
//		System.out.println(map.get("Abe"));
//		System.out.println(map.get("Drake"));
		
		
		
//		if (map.containsKey())
//		
//		map.put("hello", 1);
//		map.put("james", 1);
//		map.put("bob", 2);
//		map.put("hello", 1);
//		
		
//		System.out.println(map.get("jamesssss"));
//		

		
		
//		Order hello = new Order(5);
//		hello.updateOrder();
//		System.out.println(hello.getOrderID());
//		Order hello1 = new Order(3);
//		System.out.println(hello1.getOrderID());
//		hello1.updateOrder();
//		
//		System.out.println(hello.allOrders.get(1).get(1).getClass().);
	
		//Class x = hello1.getClass();
		//System.out.println(x.getName());
		
		/*
		
		for (int i = 0; i < 10; i++) {
			String a = "";
			Orders hellwo = new Orders(3);
			//System.out.println(hellwo.getOrderID());
		}
		*/
		
		// Every line is an array of the comma separated values 
		
		// This works well
		
		/*
		
		CSVReader reader = new CSVReader(new FileReader("/Users/femi/Desktop/items.csv"), ',' , '"' , 1);
		
		String[] nextLine;
	      while ((nextLine = reader.readNext()) != null) {
	         if (nextLine != null) {
	            //Verifying the read data here
	            System.out.println(Arrays.toString(nextLine));
	         }
	     }
	     */
		
		String csv = "/Users/femi/Drive/work/PROGRAMMING/RBMSCoursework/data/test.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv, true));		
		
		//Create record
		String [] record = "6,Julian,Miller,Denmark,50".split(",");
		//Write the record to file
		writer.writeNext(record);

		//close the writer
		writer.close();
		
		
	}
}
