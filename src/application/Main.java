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
		Platform.setLoggedIn(test); // log to this employee initially
		Employees manager = new Employees("Staff", "Derek", "Jones", "Derek", "password");
		Employees staff = new Employees("Staff", "Barry", "Flynn", "Barry", "password");

		Platform.putEmployee(manager, manager.getEmployeeUsername());
		Platform.putEmployee(staff, staff.getEmployeeUsername());
		Platform.putEmployee(test, test.getEmployeeUsername());
		

		// NEW ITEMS 

		Items.addItem("Salmon with Popped Cabbage", 20);
		Items.addItem("Chicken a la Creme", 15);
		Items.addItem("King Prawn Salad", 11);
		Items.addItem("Slow Roasted Beef Brisket", 11);
		Items.addItem("Water", 3);
		Items.addItem("Wine", 7);
		Items.addItem("Lobster", 50);
		Items.addItem("Strawberry Cheescake", 13);
		Items.addItem("Chocolate Milkshake", 8);
		
		
		// NEW TABLES 
		
		for (int i = 0; i < 9; i++) {
			Tables table = new Tables();
			Platform.putTable(table.tableNumber, table);
		}
		
		// NEW ORDERS 
		
		Random rand = new Random();
		
		for (int i = 0; i < 5; i++) {
			Orders newOrder = new Orders(i+1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(8)].toString(), 1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(8)].toString(), 1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(8)].toString(), 1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(8)].toString(), 1);
			Platform.putOrder(newOrder, newOrder.getOrderID());
			Platform.getTable(newOrder.getOrderID()).orderID = newOrder.getOrderID(); // update table in platform
		}
	}
}
