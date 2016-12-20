package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	
	//public static Scene manageEmployeesScene;
	
	@FXML
	private Label lblStatus, itemStatus, lblNewEmployeeStatus;
	
	@FXML
	private TextField txtUsername, txtPassword, txtItem, txtPrice;
	
	@FXML
	private TextField txtFirstname, txtLastname, txtNewPassword, txtNewUsername, txtType;

	private static Scene home; // This is the homepage
	
	public void Login(ActionEvent event) throws Exception {
		
		// get the user's username and password from the TextField
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		
		// Try to get the employee using the username they entered 
		Employee2 employee = Platform.getEmployee(username);
		
		String user = employee.getUsername();
		String pass = employee.getPassword();
		String type = employee.getEmployeeType();

		if (username.equals(user) && password.equals(pass) && type.equals("Manager")) {
			
			lblStatus.setText("Login Success");
			Stage primaryStage = Main.getStage(); // set stage to main stage 
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			home = scene;
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		
		else {
			lblStatus.setText("Login Failed");
			lblStatus.setVisible(true);
		}
	}

//----------------------------------------------------------------------------------------------------------
	
	// getter for main home scene
	public static Scene getHomeScene() {
		return home;
	}
	
	// sets the scene to the homepage 
	public static void goHome() {
		Main.getStage().setScene(MainController.home);
	}

//----------------------------------------------------------------------------------------------------------
	
	
	public void newOrder(ActionEvent event) throws Exception {
		
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/AddNewItems.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}
	
//	public void goToEmployees(ActionEvent event) throws Exception {
//		
//		Stage primaryStage = Main.getStage();
//		Parent root = FXMLLoader.load(getClass().getResource("/application/NewEmployee.fxml"));
//		Scene scene = new Scene(root, 900, 500);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(scene);
//		primaryStage.show();
//		
//	}
	
	public void goToOrder(ActionEvent event) throws Exception {
		
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Order2.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void goToOrderManager(ActionEvent event) throws Exception {
		
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageOrder.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public void goToEmployeeManager(ActionEvent event) throws Exception {
		
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageEmployees.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
//----------------------------------------------------------------------------------------------------------

	public void addNewItem(ActionEvent event) throws Exception {
		
		String item = txtItem.getText();
		int price = Integer.parseInt(txtPrice.getText());
		Items.addItem(item, price);
		String message = item + " has been added to your menu";
		lblStatus.setText(message);
		lblStatus.setVisible(true);
	
	}

//----------------------------------------------------------------------------------------------------------
	
//	public void addEmployees(ActionEvent event) throws Exception {
//		
//		String firstname = txtFirstname.getText();
//		String lastname = txtLastname.getText();
//		String username = txtUsername.getText();
//		String password = txtNewPassword.getText();
//		String type = txtType.getText();
//		Employee2 employee = new Employee2(type, firstname, lastname, username, password);
//		Platform.putEmployee(employee, employee.getEmployeeUsername());
//		lblNewEmployeeStatus.setText("Employee " + firstname + " " + lastname + " has been created.");
//		lblNewEmployeeStatus.setVisible(true);
//
//	}
}
