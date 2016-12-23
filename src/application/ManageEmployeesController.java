package application;

import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageEmployeesController implements Initializable {
	
	@FXML private TableView<Employees> tvEmployeeTable;
	@FXML private TableColumn<Employees, Integer> id;
	@FXML private TableColumn<Employees, String> firstname;
	@FXML private TableColumn<Employees, String> lastname;
	@FXML private TableColumn<Employees, String> username;
	@FXML private TableColumn<Employees, String> employeeType;
	@FXML private TextArea log;
	
	public static boolean answer;
	public static Stage window = new Stage();
	

	
	// List of all of the employees that are in the platform 
	public ObservableList<Employees> employees = FXCollections.observableArrayList(Platform.getAllEmployee().values());

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tvEmployeeTable.setItems(employees);
		
		id.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("employeeNumber"));
		firstname.setCellValueFactory(new PropertyValueFactory<Employees, String>("firstName"));
		lastname.setCellValueFactory(new PropertyValueFactory<Employees, String>("lastName"));
		username.setCellValueFactory(new PropertyValueFactory<Employees, String>("username"));
		employeeType.setCellValueFactory(new PropertyValueFactory<Employees, String>("employeeType"));

	}
	
	public void deleteEmployee(ActionEvent event) {
		
		// create a list to hold all of the employees 
		ObservableList<Employees> allEmployees;
		
		//create employee object 
		Employees employeeSelected;
		
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
	
	public void Home(ActionEvent event) throws IOException {
		
		// go to homepage 
		Platform.getScene().home();
		
	}
	
	public void goToNewEmployeePage(ActionEvent event) throws Exception {
		
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewEmployee.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void showLog(ActionEvent event) {
		
		// create a list to hold all of the employees 
		//ObservableList<Employee2> allEmployees;
		
		//create employee object 
		Employees employeeSelected;
		
		// get all of the current employees in the TableView
		//allEmployees = tvEmployeeTable.getItems();
		
		// put the current employee selected into this variable 
		employeeSelected = tvEmployeeTable.getSelectionModel().getSelectedItem();
		
		log.setText(employeeSelected.getLog());
		
		
	}
	
}
