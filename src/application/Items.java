package application;


import java.util.HashMap;

public class Items {
	
	//-------------------------------VARIBLES-------------------------------------
	
	public static HashMap<String, Integer> items = new HashMap<String, Integer>();
	public static HashMap<String, ItemBuffer> itemObjects = new HashMap<String, ItemBuffer>();
	
	
	//--------------------------------METHODS-------------------------------------
	
	// adds item to the hashmap
	public static void addItem(String name, int price) {
		items.put(name, price);
		System.out.println("ITEM ADDED: " + name + " £" + price);

		// remove object too
		itemObjects.put(name, new ItemBuffer(name, price, "1"));
	}
	
	//-----------------------------------------------------------------------------
	
	// removes item from the hashmap
	public static void removeItem(String name) {
		items.remove(name);
		
		// remove object too
		itemObjects.remove(name);
		
		System.out.println("ITEM REMOVED: " + name);
	}
	
	//-----------------------------------------------------------------------------
	
	public static HashMap<String, ItemBuffer> getItemObjects() {
		return itemObjects;
	}

	// gets the item price given the item name 
	public static int getItemPrice(String name) {
		return items.get(name);
	}
	
}