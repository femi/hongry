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

		// Try to get the employee using the username they entered
		Employees employee = Platform.getEmployee(username);

		String user = employee.getUsername();
		String pass = employee.getPassword();
		String type = employee.getEmployeeType();

		if (username.equals(user) && password.equals(pass) && type.equals("Manager")) {

			lblStatus.setText("Login Success");

			// go to homepage 
			Platform.getScene().Home();

		}

		else {
			lblStatus.setText("Login Failed");
			lblStatus.setVisible(true);
		}
	}
}
