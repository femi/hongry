package application;

import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	// This is a static main screen 
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		
		Main.stage = primaryStage;
		
		try {
			// BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// getter for the stage object 
	public static Stage getStage() {
		return stage;
	}
	

	public static void main(String[] args) throws IOException {
		initialise();
		//ExportTester.readRecords();
		launch(args);
	}

	public static void initialise() {
		
		// NEW EMPLOYEE

		Employees test = new Employees("Manager", "Derek", "Jones", "", "");
		test.addToLog("Employee Created");
		test.addToLog("Employee Boom");
		test.addToLog("Employee creted new order 1");
		test.addToLog("Employee creted new order 2");
		test.addToLog("Employee creted new order 3");
		test.addToLog("Employee creted new order 4");
		test.addToLog("Employee creted new order 5");
		test.addToLog("Employee creted new order 6");
		test.addToLog("Employee creted new order 7");
		Employees manager = new Employees("Manager", "Derek", "Jones", "Derek", "password");
		manager.addToLog("Employee Created");
		manager.addToLog("Employee Boom");
		manager.addToLog("Employee creted new order 1");
		manager.addToLog("Employee creted new order 2");
		Employees staff = new Employees("Staff", "Barry", "Flynn", "Barry", "password");
		staff.addToLog("Employee Created");
		staff.addToLog("Employee Boom");
		staff.addToLog("Employee creted new order 1");
		staff.addToLog("Employee creted new order 2");
		
		Platform.putEmployee(manager, manager.getEmployeeUsername());
		Platform.putEmployee(staff, staff.getEmployeeUsername());
		Platform.putEmployee(test, test.getEmployeeUsername());
		
		// §System.out.println(Platform.getAllEmployee());

		// NEW ITEMS 

		Items.addItem("Salmon with Popped Cabbage", 20);
		Items.addItem("Chicken a la Creme", 15);
		Items.addItem("King Prawn Salad", 11);
		Items.addItem("Slow Roasted Beef Brisket", 11);
		Items.addItem("Water", 3);
		Items.addItem("Wine", 7);
		
		
		// NEW TABLES 
		
		for (int i = 0; i < 9; i++) {
			Tables table = new Tables();
			Platform.putTable(table.tableNumber, table);
		}
		
		// NEW ORDERS 
		
		Random rand = new Random();
		
		for (int i = 0; i < 5; i++) {
			Orders newOrder = new Orders(i+1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(5)].toString(), 1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(5)].toString(), 1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(5)].toString(), 1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(5)].toString(), 1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(5)].toString(), 1);
			Platform.putOrder(newOrder, newOrder.getOrderID());
			Platform.getTable(newOrder.getOrderID()).orderID = newOrder.getOrderID(); // update table in platform
		}
	}
}
