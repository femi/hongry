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
		
		String firstname = txtFirstname.getText();
		String lastname = txtLastname.getText();
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String type = txtType.getText();
		
		Employee2 employee = new Employee2(type, firstname, lastname, username, password);
		Platform.putEmployee(employee, employee.getEmployeeUsername());
		lblNewEmployeeStatus.setText("Employee " + firstname + " " + lastname + " has been created.");
		lblNewEmployeeStatus.setVisible(true);
		
		MainController.goHome(); 

	}
}