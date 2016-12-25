package application;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Orders {
	
	//-------------------------------VARIBLES------------------------------------
	
	private static int orderCount;
	private int orderID;
	private int tableNumber;
	private String comments = "";
	private String timeOfOrder;
	private String itemOrderedString;
	private int orderTotal;
	private int experimentalOrderTotal;
	
	// item objects in the order [obj1, obj2, obj3]
	private ArrayList<ItemBuffer> moreOrderContents = new ArrayList<ItemBuffer>();
	
	// key value store of items e.g. {Salmon : 2}
	private HashMap<String, Integer> orderContents = new HashMap<String, Integer>();
	
	//------------------------------CONSTRUCTOR----------------------------------

	public Orders(int tableNumber) {
		
		this.orderID = ++orderCount; // Ensures the order starts at 1 instead of 0
		this.tableNumber = tableNumber;
		this.setTime(); // get the time of order
		System.out.println("ORDER " + orderID + " CREATED");
		}
	
	//--------------------------------METHODS-------------------------------------
	

	public void addItemBuffer(ItemBuffer item) {
		moreOrderContents.add(item);
		// update the order string when item added 
		itemsOrderedString();
	}
	
	public void addMultipleItemBuffer(ArrayList<ItemBuffer> items) {
		for (ItemBuffer item : items ) {
			addItemBuffer(item);
		}
	}
	
	public void removeItemBuffer(ItemBuffer item) {
		moreOrderContents.remove(item);
		// update the order string when item removed
		itemsOrderedString();
	}
	
	public ArrayList<ItemBuffer> getMoreOrderContents() {
		return this.moreOrderContents;
	}
	
	public int getExperimentalOrderTotal() {
		int total = 0;
		for (ItemBuffer item : moreOrderContents) {
			total += item.getPrice();
		}
		
		this.experimentalOrderTotal = total;
		return experimentalOrderTotal;
	}
	
	//---------------------------------------------------------------------------------	
	
	// add one item to an order 
	public void addOrderItem(String item, int quantity) {

		if (this.orderContents.containsKey(item) == true) {
			int currentQuantity = this.orderContents.get(item);
			this.orderContents.put(item, currentQuantity + quantity);
		}

		else { this.orderContents.put(item, quantity); }
	}
		
	public void addMultipleOrderItems(HashMap<String, Integer> order) {
		
		for (Map.Entry<String, Integer> pair : order.entrySet()) {
			String foodItem = pair.getKey();
			int quantityToAdd = pair.getValue();
			this.addOrderItem(foodItem, quantityToAdd);
			
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
		System.out.println("TABLE: " + this.tableNumber);
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
	
	// removes an item from an order 
	public void removeOrderItem(String item) {
		orderContents.remove(item);
	}
	
	//-----------------------------------------------------------------------------
	
	public String getComments() {
		return comments;
	}
	
	// add comments to the order 
	public void addComments(String comment) {
		this.comments = comment;
	}
	
	//-----------------------------------------------------------------------------

	// get the order id
	public int getOrderID() { 
		return this.orderID;
	}
	
	// set the order id
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	
	public int getOrderTotal() {
		int total = 0;

		// getting price using ItemBuffer objects in `moreOrderContents` instead of `orderContents`
		for (ItemBuffer item : moreOrderContents) {
			total += item.getPrice();
		}
		
		this.orderTotal = total;
		return orderTotal;
	}
	
	//-----------------------------------------------------------------------------
	
	private void setTime() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.timeOfOrder = dateFormat.format(date); //2016/11/16 12:08:43
	}
	
	public void setTimeOfOrder(String timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}
	
	public String getTimeOfOrder() {
		return timeOfOrder;
	}
	
	//-----------------------------------------------------------------------------
	
	public int getTableNumber() {
		return tableNumber;
	}
	
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	//-----------------------------------------------------------------------------
	
	private void itemsOrderedString() {
		String itemString = "";
		for (ItemBuffer item : moreOrderContents) {
			itemString +=  item.getItem() + " ";
		}
		this.itemOrderedString = itemString;	
	}
	
	public String getItemOrderedString() {
		return itemOrderedString;
	}


}