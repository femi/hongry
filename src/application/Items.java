package application;

import java.util.HashMap;

public class Items {
	
	//-------------------------------VARIBLES-------------------------------------
	
	public static HashMap<String, Integer> items = new HashMap<String, Integer>();
	
	//--------------------------------METHODS-------------------------------------
	
	// adds item to the hashmap
	public static void addItem(String name, int price) {
		items.put(name, price);
		//this.addToFile();
	}
	
	//-----------------------------------------------------------------------------
	
	// removes item from the hashmap
	public static void removeItem(String name) {
		items.remove(name);
		//this.removeFromFile();
	}
	
	//-----------------------------------------------------------------------------
	
	// gets the item price given the item name 
	public static int getItemPrice(String name) {
		return items.get(name);
	}
	
	//-----------------------------------------------------------------------------
	
	private static void addToFile() {
		// This method should add new items to the file
	}
	
	//-----------------------------------------------------------------------------
	
	private static void removeFromFile() {
		// This method should remove the items from the file 
	}
	
}