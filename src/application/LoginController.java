package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController {

	@FXML private Label lblStatus;
	@FXML private TextField txtUsername, txtPassword;
	@FXML private TextField txtFirstname, txtLastname, txtNewPassword, txtNewUsername, txtType;


	public void Login(ActionEvent event) throws Exception {

		// get the user's username and password from the TextField
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String user = "";
		String pass = "";
		String type = "";
		Employees employee = null;

		try {
		
		// Try to get the employee using the username they entered
		employee = Platform.getEmployee(username);
		
		// Get the password, username and type from the entered username
		user = employee.getUsername();
		pass = employee.getPassword();
		type = employee.getEmployeeType();
		
		} catch (NullPointerException e) {
			lblStatus.setText("Login Failed");
			System.out.println("Login Failed");
		}
		
		// Checks to see if username and password pair are correct and if they are of the correct employee type 
		if (username.equals(user) && password.equals(pass) && (type.equals("Manager") || type.equals("Staff")))  {

			lblStatus.setText("Login Success");
			
			// set the logged in user 
			Platform.setLoggedIn(employee);
			
			// go to homepage on login success 
			Platform.getScene().home();

		}

		else {
			lblStatus.setText("Login Failed");
			lblStatus.setVisible(true);
		}
	}
}
