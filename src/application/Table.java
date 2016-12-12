package application;

public class Table {
	
	//-------------------------------VARIBLES------------------------------------
	
	private static int tableCount = 0;
	public int tableNumber;
	public int orderID;
	
	//--------------------------------METHODS------------------------------------

	public Table() {
		this.tableNumber = ++tableCount;
		System.out.println("TABLE " + tableNumber  +  " CREATED");
	}
	
	//--------------------------------METHODS------------------------------------
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}	
}
