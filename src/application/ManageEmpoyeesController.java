package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageEmpoyeesController implements Initializable {
	
//	@FXML private Label lblNewEmployeeStatus;
//	
//	@FXML private TextField txtUsername;
//	@FXML private TextField txtFirstname;
//	@FXML private TextField txtLastname;
//	@FXML private TextField txtType;
//	@FXML private TextField txtPassword;
	@FXML private TableView<Employee2> tvEmployeeTable;
	@FXML private TableColumn<Employee2, Integer> id;
	@FXML private TableColumn<Employee2, String> firstname;
	@FXML private TableColumn<Employee2, String> lastname;
	@FXML private TableColumn<Employee2, String> username;
	@FXML private TableColumn<Employee2, String> employeeType;

	
	// List of all of the employees that are in the platform 
	public ObservableList<Employee2> employees = FXCollections.observableArrayList(Platform.getAllEmployee().values());

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tvEmployeeTable.setItems(employees);
		
		id.setCellValueFactory(new PropertyValueFactory<Employee2, Integer>("employeeNumber"));
		firstname.setCellValueFactory(new PropertyValueFactory<Employee2, String>("firstName"));
		lastname.setCellValueFactory(new PropertyValueFactory<Employee2, String>("lastName"));
		username.setCellValueFactory(new PropertyValueFactory<Employee2, String>("username"));
		employeeType.setCellValueFactory(new PropertyValueFactory<Employee2, String>("employeeType"));

	}
	
	public void deleteEmployee(ActionEvent event) {
		
		// create a list to hold all of the employees 
		ObservableList<Employee2> allEmployees;
		
		//create employee object 
		Employee2 employeeSelected;
		
		// get all of the current employees in the TableView
		allEmployees = tvEmployeeTable.getItems();
		
		// put the current employee selected into this variable 
		employeeSelected = tvEmployeeTable.getSelectionModel().getSelectedItem();
		
		// remove this employee from the table view 
		allEmployees.remove(employeeSelected);
		
		//---------------------------------------------------------
		
		// Cleanup
		// remove the employee from the platform 
		Platform.removeEmployee(employeeSelected.getEmployeeUsername());
		
	}
	
	public void Home(ActionEvent event) {
		
		// get the primary stage from the main class
		Stage primaryStage = Main.getStage();
		
		// set the scene to the main home screen 
		primaryStage.setScene(MainController.getHomeScene());
	}
	
	public void goToNewEmployeePage(ActionEvent event) throws Exception {
		
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewEmployee.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
//	
//	public void addEmployee(ActionEvent event) throws Exception {
//		
//		String firstname = txtFirstname.getText();
//		String lastname = txtLastname.getText();
//		String username = txtUsername.getText();
//		String password = txtPassword.getText();
//		String type = txtType.getText();
//		
//		Employee2 employee = new Employee2(type, firstname, lastname, username, password);
//		Platform.putEmployee(employee, employee.getEmployeeUsername());
//		lblNewEmployeeStatus.setText("Employee " + firstname + " " + lastname + " has been created.");
//		lblNewEmployeeStatus.setVisible(true);
//
//	}
	

}
