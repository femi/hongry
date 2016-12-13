package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


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
		
		Items.addItem("Salmon", 10);
		Items.addItem("Chicken", 10);
//		Items.addItem("Water", 3);
//		Items.addItem("Wine", 7);
		
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
