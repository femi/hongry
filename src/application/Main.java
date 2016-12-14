package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
		
	@Override
	public void start(Stage primaryStage) {
		
		// EMPLOYEE
		
		Employee2 manager = new Employee2("Manager", "Derek", "Jones", "Derek", "password");
		Employee2 staff = new Employee2("Staff", "Barry", "Flynn", "Barry", "password");
		Platform.putEmployee(manager, manager.getEmployeeUsername());
		Platform.putEmployee(staff, staff.getEmployeeUsername());
		System.out.println(Platform.getAllEmployee());
		
		// ITEMS 
		
		Items.addItem("Salmon", 20);
		Items.addItem("Chicken", 15);
		Items.addItem("Steak", 35);
		Items.addItem("Chips", 4);
		Items.addItem("Water", 3);
		Items.addItem("Wine", 7);
		
		// ITEMS 
		
		Table table1 = new Table();
		Platform.putTable(table1.tableNumber, table1);
		//System.out.println(table1.orderID);
		
		Table table2 = new Table();
		Platform.putTable(table2.tableNumber, table2);
		//System.out.println(table2.orderID);
		
		Table table3 = new Table();
		Platform.putTable(table3.tableNumber, table3);
		//System.out.println(table3.orderID);
		
		Table table4 = new Table();
		Platform.putTable(table4.tableNumber, table4);
		//System.out.println(table4.orderID);
		
		try {
//			BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
