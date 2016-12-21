package application;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Orders {
	
	//-------------------------------VARIBLES------------------------------------
	
	private static int orderCount;
	private int orderID;
	private int tableNumber;
	private HashMap<String, Integer> orderContents = new HashMap<String, Integer>();
	private String comments = "";
	private String timeOfOrder;
	private int orderTotal;
	private ArrayList<ItemBuffer> moreOrderContents = new ArrayList<ItemBuffer>();
	
	//------------------------------CONSTRUCTOR----------------------------------

	public Orders(int tableNumber) {
		
		this.orderID = orderCount + 1; // Ensures the order starts at 1 instead of 0
		this.orderIncrement(); // Increments by 1 each time an order is created
		this.tableNumber = tableNumber;
		this.setTime(); // get the time of order
		System.out.println("ORDER " + orderID + " CREATED");
		}
	
	//--------------------------------METHODS-------------------------------------
	
	
	//--------------------------------EXPRIMENTAL-------------------------------------
	//---------------------------------------------------------------------------------

	public void addItemBuffer(ItemBuffer item) {
		moreOrderContents.add(item);
	}
	
	public void addMultipleItemBuffer(ArrayList<ItemBuffer> items) {
		for (ItemBuffer item : items ) {
			addItemBuffer(item);
		}
		System.out.println(moreOrderContents);
	}
	
	public void removeItemBuffer(ItemBuffer item) {
		moreOrderContents.remove(item);
	}
	
	public ArrayList<ItemBuffer> getMoreOrderContents() {
		return this.moreOrderContents;
	}

	//---------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------
	
	
	public int getTableNumber() {
		return tableNumber;
	}
	
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	public String getTimeOfOrder() {
		return timeOfOrder;
	}
	
	// add one item to an order 
	public void addOrderItem(String item, int quantity) {

		if (this.orderContents.containsKey(item) == true) {
			int currentQuantity = this.orderContents.get(item);
			this.orderContents.put(item, currentQuantity + quantity);
			
			// EXPERIMENT
			addItemBuffer(new ItemBuffer(item, Items.getItemPrice(item), "1"));
			// EXPERIMENT
		}

		else { this.orderContents.put(item, quantity);
			// EXPERIMENT
			addItemBuffer(new ItemBuffer(item, Items.getItemPrice(item), "1"));
			// EXPERIMENT
		}
	}
	
	//-----------------------------------------------------------------------------
	
	// add multiple items // re-factored original version 
	public void addMultipleOrderItems(ArrayList<ArrayList<String>> order) {
		
		for (ArrayList<String> pair : order) {
			String foodItem = pair.get(0);
			int quantityToAdd = Integer.parseInt(pair.get(1));
			this.addOrderItem(foodItem, quantityToAdd);

		}
	}
	
	public void addMultipleOrderItems2(HashMap<String, Integer> order) {
		
		for (Map.Entry<String, Integer> pair : order.entrySet()) {
			String foodItem = pair.getKey();
			int quantityToAdd = pair.getValue();
			this.addOrderItem(foodItem, quantityToAdd);
			
		}
	}
	
	//-----------------------------------------------------------------------------
	
	private void setTime() {
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.timeOfOrder = dateFormat.format(date); //2016/11/16 12:08:43
		
	}
	
	//-----------------------------------------------------------------------------

	// modify one order 
	public void modifyOrder(String item, int quantityToReplace) {
		
		if (this.orderContents.containsKey(item) == true) {
			this.orderContents.replace(item, quantityToReplace);
		}
		else {this.orderContents.put(item, quantityToReplace);} 
	}
	
	//-----------------------------------------------------------------------------

	// add comments to the order 
	public void comments(String comment) {
		this.comments += comment + " || ";
	}
	
	//-----------------------------------------------------------------------------

	// modify order // re-factored original version 
	public void modifyMultipleOrders(ArrayList<ArrayList<String>> itemPairs) {

		for (ArrayList<String> pair : itemPairs) {
			String foodItem = pair.get(0);
			int quantityToReplace = Integer.parseInt(pair.get(1));
			this.modifyOrder(foodItem, quantityToReplace);
		}
	}

	//-----------------------------------------------------------------------------

	// Displays all of the items in the order with
	public void displayOrder() {
		System.out.println("---------------------------");
		for (Map.Entry<String, Integer> entry : this.orderContents.entrySet()) {
			System.out.println(entry.getKey() + " | " + "x " +  entry.getValue() + " | £" + Items.getItemPrice(entry.getKey()) );
		}
		System.out.println("---------------------------");
		System.out.println("ORDER TIME: " + this.timeOfOrder);
		System.out.println("COMMENTS: " + this.comments);
		System.out.println("---------------------------");
		System.out.println("ORDER TOTAL: £" + this.getOrderTotal());
		System.out.println("---------------------------");
	}
	
	//-----------------------------------------------------------------------------

	// removes multiple items from an order 
	public void removeMultipleOrderItems(ArrayList<String> items) {
		for (String item : items) {
			orderContents.remove(item);
		}
	}
	
	public String getComments() {
		return comments;
	}
	
	//----------------------------------------------------------------------------

	// removes an item from an order 
	public void removeOrderItem(String item) {
		orderContents.remove(item);
	}
	
	//-----------------------------------------------------------------------------

	// increase the order count
	public void orderIncrement() { 
		orderCount += 1; 
	}

	//-----------------------------------------------------------------------------

	// get the order id
	public int getOrderID() { 
		return this.orderID;
	}
	
	//-----------------------------------------------------------------------------

	public int getOrderTotal() {
		int total = 0;
		for (Map.Entry<String, Integer> entry : this.orderContents.entrySet()) {
			total += entry.getValue() * Items.getItemPrice(entry.getKey());
		}
		this.orderTotal = total;
		return orderTotal;
	}
	

	
	//-----------------------------------------------------------------------------

	public void writeToFile() {
		// Function should modify the contents of the json file
	}
	
	//-----------------------------------------------------------------------------
	
	public void saveOrder() {
		// This method should save the current order to the Order file 
		// if the order already exists then the order is replaced
		// We could call this method anytime something new is added to the order
	}
	
	//-----------------------------------------------------------------------------

	public void deleteExistingOrder() {
		// Delete and order that has been completed
	}
	
}