package application;

/**
 * 
 * This class allows users to create table objects which can be
 * used to add orders to on the restaurant management system.
 * 
 * @author femi
 *
 */
public class Tables {
		
	private static int tableCount = 0;
	public int tableNumber;
	public int orderID = 0;
	
	/**
	 * Creates new Table object.
	 */
	public Tables() {
		this.tableNumber = ++tableCount;
		System.out.println("TABLE " + tableNumber  +  " CREATED");
	}
		
	/**
	 * Sets the table the current order id of the table .
	 * @param orderID
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	
	/**
	 * Gets the current order ID of the table.
	 * @return
	 */
	public int getOrderID() {
		return orderID;
	}
	
}
