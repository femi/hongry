package application;

import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * This class contains the main driver class for the application.
 * The methods in this class open the login page of the application, 
 * and initialise the application with dummy data.
 * 
 * @author femi
 *
 */
public class Main extends Application {
	
	// This is a static main screen 
	private static Stage stage;

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		
		Main.stage = primaryStage;
		
		try {
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
	

	/**
	 * 
	 * Seeds the application with dummy data and then launches it.
	 * 
	 * @param args
	 * @throws IOException if FXML file cannot be fount 
	 */
	public static void main(String[] args) throws IOException {
		initialise();
		launch(args);
	}

	/**
	 * 
	 * Creates dummy data for the application, allowing the user to use 
	 * the application with pre-generated data.
	 * 
	 */
	public static void initialise() {
		
		// NEW EMPLOYEE
		
		Employees test = new Employees("Manager", "Derek", "Jones", "", "");
		Platform.setLoggedIn(test); // log to this employee initially
		Employees manager = new Employees("Staff", "Derek", "Jones", "Derek", "password");
		Employees staff = new Employees("Staff", "Barry", "Flynn", "Barry", "password");
		Employees jake = new Employees("Staff", "Jake", "Bowers", "Jake", "password");
		Employees albert = new Employees("Staff", "Albert", "Devon", "Albert", "password");

		Platform.putEmployee(manager, manager.getUsername());
		Platform.putEmployee(staff, staff.getUsername());
		Platform.putEmployee(test, test.getUsername());
		Platform.putEmployee(jake, jake.getUsername());
		Platform.putEmployee(albert, albert.getUsername());
		

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
			
			// Initialise new order    
			Orders newOrder = new Orders(i+1);
			
			// Initialise order items  
			newOrder.addItemBuffer(Items.itemObjects.get(Items.itemObjects.keySet().toArray()[rand.nextInt(8)]));
			newOrder.addItemBuffer(Items.itemObjects.get(Items.itemObjects.keySet().toArray()[rand.nextInt(8)]));
			newOrder.addItemBuffer(Items.itemObjects.get(Items.itemObjects.keySet().toArray()[rand.nextInt(8)]));
			newOrder.addItemBuffer(Items.itemObjects.get(Items.itemObjects.keySet().toArray()[rand.nextInt(8)]));
			Platform.putOrder(newOrder, newOrder.getOrderID());
			Platform.getTable(newOrder.getOrderID()).orderID = newOrder.getOrderID(); // update table in platform
		}
	}
}
