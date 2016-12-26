package application;

import java.util.HashMap;

/**
 * 
 * This static class contains method that aim to keep track of items.
 * The class stores the current items in the entire platform and 
 * enables the ability to add or remove more items.
 * 
 * @author femi
 *
 */
public class Items {
	
	//-------------------------------VARIBLES-------------------------------------
	
	public static HashMap<String, Integer> items = new HashMap<String, Integer>();
	public static HashMap<String, ItemBuffer> itemObjects = new HashMap<String, ItemBuffer>();
	
	
	//--------------------------------METHODS-------------------------------------
	
	/**
	 * 
	 * Creates a new item and adds it to a HashMap, also creates and stores 
	 * a corresponding Item object.
	 * 
	 * @param this is the name of the item being added
	 * @param this is the price of the item being added
	 */
	public static void addItem(String name, int price) {
		items.put(name, price);
		System.out.println("ITEM ADDED: " + name + " £" + price);

		// add object too
		itemObjects.put(name, new ItemBuffer(name, price, "1"));
	}
		
	/**
	 * 
	 * Removes an existing item from a hashmap given the name of the item that
	 * needs to be removed. Also removes the corresponding Item object from storage.
	 * 
	 * @param name of the item that needs to be removed
	 */
	public static void removeItem(String name) {
		items.remove(name);
		
		// remove object too
		itemObjects.remove(name);
		
		System.out.println("ITEM REMOVED: " + name);
	}
	
	//-----------------------------------------------------------------------------
	
	
	/**
	 * Gets the list of the item objects created 
	 * @return itemObjects the list of item objects stored 
	 */
	public static HashMap<String, ItemBuffer> getItemObjects() {
		return itemObjects;
	}

	/**
	 * Gets the price of an item given the name of it 
	 * @param name of the item
	 * @return the price of the item 
	 */
	public static int getItemPrice(String name) {
		return items.get(name);
	}
	
}