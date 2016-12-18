package application;

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
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// getter for the stage object 
	public static Stage getStage() {
		return stage;
	}
	

	public static void main(String[] args) {
		initialise();
		launch(args);
	}

	public static void initialise() {
		
		// NEW EMPLOYEE

		Employee2 manager = new Employee2("Manager", "Derek", "Jones", "Derek", "password");
		Employee2 staff = new Employee2("Staff", "Barry", "Flynn", "Barry", "password");
		Platform.putEmployee(manager, manager.getEmployeeUsername());
		Platform.putEmployee(staff, staff.getEmployeeUsername());
		System.out.println(Platform.getAllEmployee());

		// NEW ITEMS 

		Items.addItem("Salmon with Popped Cabbage", 20);
		Items.addItem("Chicken a la Creme", 15);
		Items.addItem("King Prawn Salad", 11);
		Items.addItem("Slow Roasted Beef Brisket", 11);
		Items.addItem("Water", 3);
		Items.addItem("Wine", 7);
		

		// NEW TABLES 
		
		for (int i = 0; i < 10; i++) {
			Table table = new Table();
			Platform.putTable(table.tableNumber, table);
		}
		
		// NEW ORDERS 
		
		Random rand = new Random();
		
		for (int i = 0; i < 5; i++) {
			Order newOrder = new Order(i+1);
			newOrder.addOrderItem(Items.items.keySet().toArray()[rand.nextInt(5)].toString(), rand.nextInt(10));
			Platform.putOrder(newOrder, newOrder.getOrderID());
			Platform.getTable(newOrder.getOrderID()).orderID = newOrder.getOrderID(); // update table in platform
		}
				
	}
}
