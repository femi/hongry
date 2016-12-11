package application;
import java.util.*;

public class Order {

	public static ArrayList<Order> allOrders = new ArrayList<Order>();

	//-------------------------------VARIBLES------------------------------------

	private static int totalOrders = 0;
	private int orderID = 0;
	public int tableNumber;
	public HashMap<String, Integer> map = new HashMap<String, Integer>();
	public HashMap<String, Integer> prices = new HashMap<String, Integer>();
	//private ArrayList<String> items = new ArrayList<String>();

	//------------------------------CONSTRUCTOR----------------------------------

	public Order(int tableNumber) {
		this.orderID = totalOrders + 1; // Ensures the order starts at 1 instead of 0
		this.orderIncrement(); // Increments by 1 each time an order is created
		this.tableNumber = tableNumber;
		//updateOrder();
	}

	//--------------------------------METHODS-------------------------------------

	//	public void updateOrder() {
	//		Order.allOrders.add(this);
	//	}


	// add one item to an order 
	public void addItem(String item, int quantity) {

		if (this.map.containsKey(item) == true) {
			int currentQuantity = this.map.get(item);
			this.map.put(item, currentQuantity + quantity);
		}

		else { this.map.put(item, quantity);	}
	}

	// add multiple items to an order 
	public void addMultipleItems(ArrayList<ArrayList> itemPairs) {

		// Takes in an ArrayList that contains an ArrayList of the
		// item and the quantity to add to the order

		for (ArrayList<String> pair : itemPairs) {

			String foodItem = pair.get(0);
			int quantityToAdd = Integer.parseInt(pair.get(1));

			if (this.map.containsKey(foodItem) == true) {
				int currentQuantity = this.map.get(foodItem);
				this.map.put(foodItem, currentQuantity + quantityToAdd);
			}

			else { this.map.put(foodItem, quantityToAdd); }
		}	
	}

	// modify the contents of an order
	public void modifyOrder(ArrayList<ArrayList> itemPairs) {

		for (ArrayList<String> pair : itemPairs) {

			String foodItem = pair.get(0);

			int quantityToReplace = Integer.parseInt(pair.get(1));

			if (this.map.containsKey(foodItem) == true) {
				this.map.put(foodItem, quantityToReplace);
			}

			else { this.map.put(foodItem, quantityToReplace); }
		}
	}

	// returns the item price of a given item
	public int getPrice(String item) {

		int itemPrice = 0;

		for (String key : prices.keySet()) {
			if (key.equals(item)) {
				itemPrice = prices.get(key);
				break;
			}

			else {
				System.out.println("There is an error, the function could not retrieve a price");
			}
		}

		return itemPrice;
	}


	// Displays all of the items in the order with
	public void displayOrder() {
		for (Map.Entry<String, Integer> entry : this.map.entrySet()) {
			System.out.println(entry.getKey() + " | " + entry.getValue());
		}
	}

	// removes multiple items from an order 
	public void removeMultipleItems(ArrayList<String> items) {
		for (String item : items) {
			map.remove(item);
		}
	}

	// removes an item from an order 
	public void removeItem(String item) {
		map.remove(item);
	}

	// increase the order count
	public void orderIncrement() { 
		totalOrders += 1; 
	}

	// get the order id
	public int getOrderID() { 
		return this.orderID;
	}

	public void makeOrder() {
	}

	public void getOrderTotal() {
	}

	public void writeToFile() {
		// Function should modify the contents of the json file
	}
	
	public void saveOrder() {
		// This method should save the current order to the Order file 
		// if the order already exists then the order is replaced
		// We could call this method anytime something new is added to the order
	}
}