package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * 
 * This controller class controls the homepage of the application. 
 * 
 * @author femi
 *
 */
public class HomepageController implements Initializable {

	@FXML private Label lblPlatformTotal;
	
	// Table buttons 
	@FXML private Button btn1 = new Button("1");
	@FXML private Button btn2 = new Button("2");
	@FXML private Button btn3 = new Button("3");
	@FXML private Button btn4 = new Button("4");
	@FXML private Button btn5 = new Button("5");
	@FXML private Button btn6 = new Button("6");
	@FXML private Button btn7 = new Button("7");
	@FXML private Button btn8 = new Button("8");
	@FXML private Button btn9 = new Button("9");
	
	@FXML private Button menuManager = new Button();
	@FXML private Button staffManager = new Button();
	
	// An arraylist containing all of the table buttons
	public ArrayList<Button> allButtons = new ArrayList<Button>();
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Displays the current taking on the platform
		lblPlatformTotal.setText("£" + Platform.getTotal() + ".00");
		
		// Add buttons to arraylist 
		addButtons();
		
		// Change colour of buttons based on table number 
		setButtonColour(allButtons);
		
		// "Staff" don't get access to the menu and staff management 
		if (Platform.getLoggedIn().getEmployeeType().equals("Staff")) {
			menuManager.setDisable(true);
			staffManager.setDisable(true);
		}
		
		// Implementing order previews
		
//		for (Button button : allButtons) {
//			button.hoverProperty().addListener( (e) -> {
//				int tableNumber = Integer.parseInt(button.getText());
//				Tables table = Platform.getTable(tableNumber);
//				Orders order = Platform.getOrder(table.getOrderID());
//				System.out.println(button.getText());
//			});
//		}
		
	}
	
	// ----------------------------------------------------------------------------------------------------------

	/**
	 * 
	 * Sets the colour of the buttons to reflect the status of the order.
	 * 
	 * @param allButtons contains buttons used in restaurant map
	 */
	public void setButtonColour(ArrayList<Button> allButtons) {
		
		Collection<Tables> allTables = Platform.getAllTables().values();
		ArrayList<Integer> availableTables = new ArrayList<Integer>();
		
		// get all tables that do not hve orders
		for (Tables table : allTables ) {
			if ( table.getOrderID() == 0) {
				availableTables.add(table.tableNumber);
			}
		}
		
		// If the button does not have an order set the colour of the button red
		for (Button button : allButtons ) {
			if ( !availableTables.contains(Integer.parseInt(button.getText()))) {
				button.setStyle("-fx-color: #F06767;}");
			}
		}
	}
	
	/**
	 * Adds the restaurant map buttons to a list
	 */
	public void addButtons() {
		allButtons.removeAll(allButtons);
		allButtons.add(btn1);
		allButtons.add(btn2);
		allButtons.add(btn3);
		allButtons.add(btn4);
		allButtons.add(btn5);
		allButtons.add(btn6);
		allButtons.add(btn7);
		allButtons.add(btn8);
		allButtons.add(btn9);	
	}
	
	/**
	 * 
	 * @param event occurs when button clicked 
	 * @throws Exception
	 */
	public void modifyOrder(ActionEvent event) throws Exception {
		
		Tables table;
		Orders order;
		
		// Get the table number from the button object, limited to buttons 1 - 9 
		String selectedButton = event.getSource().toString().substring(35, 36);
		
		try {
			
			table = Platform.getTable(Integer.parseInt(selectedButton));
			order = Platform.getOrder(table.getOrderID());
			
			// set the current order
			Variables.setOrder(order.getOrderID());
			
			// select the current order and go to the modify page
			goToModifyPage();
			
			//display the order in the terminal
			order.displayOrder();
			
		} catch (Exception e) {
			System.out.println("This table is currently empty.");
			goToOrder();
		}
	}
	
	
	/**
	 * 
	 * Creates a new scene that points to the modify order page 
	 * when a button is clicked.
	 * 
	 * @throws IOException if the required FXML page cannot be loaded 
	 */
	public void goToModifyPage() throws IOException {
	
		Stage primaryStage = Main.getStage();
		FXMLLoader loader =  new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/application/ModifyOrder.fxml").openStream());
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
	}
	
	/**
	 * 
	 * Creates a new scene that points to the new order page 
	 * when a button is clicked.
	 * 
	 * @throws IOException if the required FXML page cannot be loaded 
	 */
	public void goToOrder() throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewOrder.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	// ----------------------------------------------------------------------------------------------------------

	
	/**
	 * 
	 * Creates a new scene that points to the menu page 
	 * when a button is clicked.
	 * 
	 * @param event
	 * @throws IOException if the required FXML page cannot be loaded 
	 */
	public void newOrder(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewMenuItem.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	/**
	 * 
	 * Creates a new scene that points to the new order page 
	 * when a button is clicked.
	 * 
	 * @throws IOException if the required FXML page cannot be loaded 
	 */
	public void goToOrder(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewOrder.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * 
	 * Creates a new scene that points to the manage orders page  
	 * when a button is clicked.
	 * 
	 * @param event
	 * @throws IOException if the required FXML page cannot be loaded 
	 */
	public void goToOrderManager(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageOrder.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	
	/**
	 * 
	 * Creates a new scene that points to the manage employees page  
	 * when a button is clicked.
	 * 
	 * @param event
	 * @throws IOException if the required FXML page cannot be loaded 
	 */
	public void goToEmployeeManager(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageEmployees.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	
	/**
	 * 
	 * Creates a new scene that points to the main login page
	 * when a button is clicked.
	 * 
	 * @param event
	 * @throws IOException if the required FXML page cannot be loaded 
	 */
	public void logout(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
