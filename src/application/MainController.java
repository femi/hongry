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
	
	@FXML
	private Label lblStatus;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private TextField txtItem;
	@FXML
	private TextField txtPrice;
	@FXML
	private Label itemStatus;
	@FXML
	private TextField txtFirstname;
	@FXML
	private TextField txtLastname;
	@FXML
	private TextField txtNewPassword;
	@FXML
	private TextField txtNewUsername;
	@FXML
	private TextField txtType;
	@FXML
	private Label lblNewEmployeeStatus;

	
	public void Login(ActionEvent event) throws Exception {
		
		//----------------------------------------------------------------------------
//		Employee2 e2 = new Employee2("Staff", "Gazza", "Berry", "Gazz", "password");
//		Platform.putEmployee(e2, e2.getEmployeeUsername());
//		System.out.println(Platform.getAllEmployee());
		//----------------------------------------------------------------------------
		
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		
		Employee2 employee = Platform.getEmployee(username);
		String user = employee.getUsername();
		String pass = employee.getPassword();
		String type = employee.getEmployeetype();

		if (username.equals(user) && password.equals(pass) && type.equals("Manager")) {
			
			lblStatus.setText("Login Success");
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		}
		
		else {
			lblStatus.setText("Login Failed");
			lblStatus.setVisible(true);
		}
	}

//----------------------------------------------------------------------------------------------------------
	
	
	public void newOrder(ActionEvent event) throws Exception {
		
		//requestedItems.add("");
		//Order order = new Order(1);
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/AddNewItems.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void goToEmployees(ActionEvent event) throws Exception {
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewEmployee.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void goToOrder(ActionEvent event) throws Exception {
		
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Order.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//System.out.println(Items.list);
		
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
	
	public void addEmployees(ActionEvent event) throws Exception {
		
		String firstname = txtFirstname.getText();
		String lastname = txtLastname.getText();
		String username = txtUsername.getText();
		String password = txtNewPassword.getText();
		String type = txtType.getText();
		Employee2 employee = new Employee2(type, firstname, lastname, username, password);
		Platform.putEmployee(employee, employee.getEmployeeUsername());
		lblNewEmployeeStatus.setText("Employee " + firstname + " " + lastname + " has been created.");
		lblNewEmployeeStatus.setVisible(true);
		
	}
	
}
