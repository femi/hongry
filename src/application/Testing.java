package application;

public class Testing {

	public static void main(String[] args) {		
		
		// PROGRAM MANAGER
		
		Manage platform = new Manage();
		
		// EMPLOYEES
		
		Employee employee1 = new Employee("Manager");
		employee1.setName("Sam", "Johnson");
		employee1.setUsername("Sam");
		employee1.setPassword("password");
		platform.putEmployee(employee1, employee1.getEmployeeNumber());
		
		
		Employee employee2 = new Employee("Staff");
		employee2.setName("Betty", "Booty");
		employee2.setUsername("Betty");
		employee2.setPassword("password");
		platform.putEmployee(employee2, employee2.getEmployeeNumber());
		
		
		Employee employee3 = new Employee("Staff");
		employee3.setName("Jakob", "Fenson");
		employee3.setUsername("Jakob");
		employee3.setPassword("password");
		platform.putEmployee(employee2, employee2.getEmployeeNumber());
		
		// ITEMS 
		
		Items.addItem("Salmon", 10);
		Items.addItem("Chicken", 10);
		Items.addItem("Water", 3);
		Items.addItem("Wine", 7);
	
		// TABLES 
		
		Table table1 = new Table();
		platform.putTable(table1.tableNumber, table1);
		
		Table table2 = new Table();
		platform.putTable(table2.tableNumber, table2);
		
		Table table3 = new Table();
		platform.putTable(table3.tableNumber, table3);
		
		Table table4 = new Table();
		platform.putTable(table4.tableNumber, table4);
		
		Table table5 = new Table();
		platform.putTable(table5.tableNumber, table5);

		
		//---------------new order------------------
		
		Order orderA = new Order(1);
		table1.setOrderID(orderA.getOrderID());
		
		orderA.addOrderItem("Salmon", 5);
		orderA.addOrderItem("Chicken", 3);
		orderA.addOrderItem("Water", 2);
		
		platform.putOrder(orderA, orderA.getOrderID());
		
		//---------------modify order------------------
		
		Table table = platform.getTable(1);
		Order orderB = platform.getOrder(table.orderID);
		
		orderB.addOrderItem("Salmon", 5);
		orderB.addOrderItem("Water", 2);
		orderB.modifyOrder("Chicken", 0);
//		orderB.displayOrder();
		
		System.out.println(orderB.getOrderTotal());
		System.out.println(orderA.getOrderTotal());
		
		//---------------remove order------------------

		//Order orderC = platform.getOrder(table.orderID);
		//table.orderID = 0;
		
		//---------------------------------------------
		
//		Order order1 = new Order(1);
//		Order order2 = new Order(2);
//		Order order3 = new Order(3);
//		
//		order1.tableNumber = 1;
//		order1.addOrderItem("Salmon", 5);
//		order1.addOrderItem("Chicken", 3);
//		order1.addOrderItem("Water", 2);
//		System.out.println(order1.getOrderTotal());
//		
//		platform.putOrder(order1, order1.getOrderID());
//		platform.putOrder(order2, order2.getOrderID());
//		platform.putOrder(order3, order3.getOrderID());
//		
//		// Table object should have the order_id stored
//		
//		platform.getOrder(1).addOrderItem("Wine", 5);
//		platform.getOrder(1).addOrderItem("Wine", 5);
//		platform.getOrder(1).addOrderItem("Wine", 5);
//		platform.getOrder(1).addOrderItem("Wine", 100);
//		
//		order2.addOrderItem("Salmon", 5);
//		order3.addOrderItem("Chicken", 3);
//		
//		System.out.println(platform.getOrder(1).getOrderTotal());
//		System.out.println(platform.getOrder(2).getOrderTotal());
//		System.out.println(platform.getOrder(3).getOrderTotal());
		
	}
}
