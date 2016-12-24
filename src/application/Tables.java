package application;

public class Tables {
		
	private static int tableCount = 0;
	public int tableNumber;
	public int orderID = 0;
	
	//------------------------------CONSTRUCTOR----------------------------------

	public Tables() {
		this.tableNumber = ++tableCount;
		System.out.println("TABLE " + tableNumber  +  " CREATED");
	}
	
	//--------------------------------METHODS------------------------------------
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public int getOrderID() {
		return orderID;
	}
	
}
