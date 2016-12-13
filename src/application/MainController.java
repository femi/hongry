package application;

import java.io.IOException;
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

	
	public void Login(ActionEvent event) throws Exception {
		
		Employee2 e2 = new Employee2("Staff", "Gazza", "Berry", "Gazz", "password");
		Platform.putEmployee(e2, e2.getEmployeeNumber());
		System.out.println(Platform.getAllEmployee());

		
		//platform.putEmployee(e, 2);
		//platform.putEmployee(e, 3);
		//System.out.println(platform.getEmployee(1));
		//System.out.println(platform.getAllEmployee());
		
		String username = txtUsername.getText();
		String password = txtPassword.getText();

		if (username.equals("") && password.equals("") ) {
			
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
		}
		
	}

}
