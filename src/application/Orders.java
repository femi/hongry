package application;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * Represents an order in the restaurant management application. 
 * An order can contain items which a user has selected, the order
 * also contains information about the time, total and table number.
 * 
 * @author femi
 *
 */
public class Orders {
	
	//-------------------------------VARIBLES------------------------------------
	
	private static int orderCount;
	private int orderID;
	private int tableNumber;
	private String comments = "";
	private String timeOfOrder;
	private String itemOrderedString;
	private int orderTotal;
	private int orderTotalObjects;
	

	// key value store of items e.g. {Salmon : 2}
	private HashMap<String, Integer> orderContents = new HashMap<String, Integer>();
	
	// item objects in the order [obj1, obj2, obj3]
	private ArrayList<ItemBuffer> orderItemObjects = new ArrayList<ItemBuffer>();
	
	//------------------------------CONSTRUCTOR----------------------------------

	/**
	 * 
	 * Creates a new order object that takes a table number when it is created.
	 * Once created the order is also assigned a order id and a order time.
	 * 
	 * @param tableNumber This is what table the order is currently on
	 */
	public Orders(int tableNumber) {
		
		this.orderID = ++orderCount; // Ensures the order starts at 1 instead of 0
		this.tableNumber = tableNumber;
		this.setTime(); // get the time of order
		System.out.println("ORDER " + orderID + " CREATED");
		}
	
	//--------------------------------METHODS-------------------------------------
	

	/**
	 * 
	 * Adds item object to be stored in the order and then populates the order string.
	 * 
	 * @param item
	 */
	public void addItemBuffer(ItemBuffer item) {
		orderItemObjects.add(item);
		// update the order string when item added 
		itemsOrderedString();
	}
	
	/**
	 * 
	 * Adds multiple items objects to be stored in the order.
	 * 
	 * @param items
	 */
	public void addMultipleItemBuffer(ArrayList<ItemBuffer> items) {
		for (ItemBuffer item : items ) {
			addItemBuffer(item);
		}
	}
	
	/**
	 * Removes an item object from the order and updates the order string.
	 * @param item
	 */
	public void removeItemBuffer(ItemBuffer item) {
		orderItemObjects.remove(item);
		// update the order string when item removed
		itemsOrderedString();
	}
	
	/**
	 * Returns a list of the order's item objects.
	 * @return
	 */
	public ArrayList<ItemBuffer> orderItemObjects() {
		return this.orderItemObjects;
	}
	
	/**
	 * Returns the total amount of an order.
	 * @return
	 */
	public int getOrderTotalObjects() {
		int total = 0;
		for (ItemBuffer item : orderItemObjects) {
			total += item.getPrice();
		}
		
		this.orderTotalObjects = total;
		return orderTotalObjects;
	}
	
	//---------------------------------------------------------------------------------	
	
	/**
	 * 
	 * Adds an order to a hashmap containing details about what items have been 
	 * ordered and how many of the same item the user has. The method takes in 
	 * the item name and the quantity that the user selected.
	 * 
	 * @param item The name of the item the user is selected 
	 * @param quantity The quantity the user selected 
	 */
	public void addOrderItem(String item, int quantity) {

		if (this.orderContents.containsKey(item) == true) {
			int currentQuantity = this.orderContents.get(item);
			this.orderContents.put(item, currentQuantity + quantity);
		}

		else { this.orderContents.put(item, quantity); }
	}
		
	/**
	 * 
	 * Adds multiple items to the user's order, the method takes in an 
	 * HashMap containing a key value store (Item : Quantity) of what and
	 * how many items a user wants to add to their order.
	 * 
	 * @param order
	 */
	public void addMultipleOrderItems(HashMap<String, Integer> order) {
		
		for (Map.Entry<String, Integer> pair : order.entrySet()) {
			String foodItem = pair.getKey();
			int quantityToAdd = pair.getValue();
			this.addOrderItem(foodItem, quantityToAdd);
			
		}
	}

	//-----------------------------------------------------------------------------

	// Displays all of the items in the order with
	/**
	 * 
	 * Displays details of an order in the console. The method displays
	 * details about the items and quantities ordered, time, table number,
	 * comments and the order total.
	 * 
	 */
	public void displayOrder() {
		System.out.println("---------------------------");
		for (Map.Entry<String, Integer> entry : this.orderContents.entrySet()) {
			System.out.println(entry.getKey() + " | " + "x " +  entry.getValue() + " | £" + Items.getItemPrice(entry.getKey()) );
		}
		System.out.println("---------------------------");
		System.out.println("ORDER TIME: " + this.timeOfOrder);
		System.out.println("TABLE: " + this.tableNumber);
		System.out.println("COMMENTS: " + this.comments);
		System.out.println("---------------------------");
		System.out.println("ORDER TOTAL: £" + this.getOrderTotal());
		System.out.println("---------------------------");
	}
	
	//-----------------------------------------------------------------------------

//	// removes multiple items from an order 
//	public void removeMultipleOrderItems(ArrayList<String> items) {
//		for (String item : items) {
//			orderContents.remove(item);
//		}
//	}
//	
//	// removes an item from an order 
//	public void removeOrderItem(String item) {
//		orderContents.remove(item);
//	}
	
	//-----------------------------------------------------------------------------
	
	/**
	 * Gets the comments from an order.
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments for an order. 
	 * @param comment
	 */
	public void addComments(String comment) {
		this.comments = comment;
	}
	
	//-----------------------------------------------------------------------------


	/**
	 * Gets the order id of this order. 
	 * @return orderID of this order 
	 */
	public int getOrderID() { 
		return this.orderID;
	}
	
	/**
	 * Set the orderID of this order.
	 * @param orderID
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	
	/**
	 * Get the total amount of the order.
	 */
	public int getOrderTotal() {
		int total = 0;

		// getting price using ItemBuffer objects in `oderContentsObjects` instead of `orderContents`
		for (ItemBuffer item : orderItemObjects) {
			total += item.getPrice();
		}
		
		this.orderTotal = total;
		return orderTotal;
	}
	
	//-----------------------------------------------------------------------------
	
	/**
	 * Set the time and date of the order.
	 */
	private void setTime() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.timeOfOrder = dateFormat.format(date); //2016/11/16 12:08:43
	}
	
	/**
	 * Set the time and date of the order.
	 * @param timeOfOrder
	 */
	public void setTimeOfOrder(String timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}
	
	
	/**
	 * Get the time of the order.
	 * @return
	 */
	public String getTimeOfOrder() {
		return timeOfOrder;
	}
	
	//-----------------------------------------------------------------------------
	
	/**
	 * Get the table number of the order.
	 * @return
	 */
	public int getTableNumber() {
		return tableNumber;
	}
	
	/**
	 * Set the table number of the order.
	 * @param tableNumber
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	//-----------------------------------------------------------------------------
	
	/**
	 * Set a string of all of the items that have been ordered.
	 */
	private void itemsOrderedString() {
		String itemString = "";
		for (ItemBuffer item : orderItemObjects) {
			itemString +=  item.getName() + " ";
		}
		this.itemOrderedString = itemString;	
	}
	
	/**
	 * Get a string of all of the items that have been ordered.
	 * @return
	 */
	public String getItemOrderedString() {
		return itemOrderedString;
	}


}