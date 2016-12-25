package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewEmployeeController {
	
	@FXML private Label lblNewEmployeeStatus;
	@FXML private TextField txtUsername;
	@FXML private TextField txtFirstname;
	@FXML private TextField txtLastname;
	@FXML private TextField txtType;
	@FXML private TextField txtPassword;
	
	public void addEmployee(ActionEvent event) throws Exception {
		
		// collect the new employee's details
		String firstname = txtFirstname.getText();
		String lastname = txtLastname.getText();
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String type = txtType.getText();
		
		// create new employee object 
		Employees employee = new Employees(type, firstname, lastname, username, password);
		
		// add employee to platform object
		Platform.putEmployee(employee, employee.getUsername());
		lblNewEmployeeStatus.setText("Employee " + firstname + " " + lastname + " has been created.");
		lblNewEmployeeStatus.setVisible(true);
		
		//go back 
		Platform.getScene().manageEmployees();
	}
}