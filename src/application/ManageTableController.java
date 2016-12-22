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
import javafx.stage.Stage;

public class ManageTableController implements Initializable {
	
	@FXML private Button btn1 = new Button("1");
	@FXML private Button btn2 = new Button("2");
	@FXML private Button btn3 = new Button("3");
	@FXML private Button btn4 = new Button("4");
	@FXML private Button btn5 = new Button("5");
	@FXML private Button btn6 = new Button("6");
	@FXML private Button btn7 = new Button("7");
	@FXML private Button btn8 = new Button("8");
	@FXML private Button btn9 = new Button("9");
	
	// A list of all of the buttons
	public ArrayList<Button> allButtons = new ArrayList<Button>();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		addButtons();
		setButtonColour(allButtons);
		
	}
	
	
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
			if ( availableTables.contains(Integer.parseInt(button.getText()))) {
				button.setStyle("-fx-color: #F06767;}");
			}
		}
		
	}
	
	
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
	
	public void modifyOrder(ActionEvent event) throws Exception {
		
		Tables table;
		Orders order;
		
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
			// TODO: handle exception
			System.out.println("This table is currently empty.");
			goToOrder();

		}
	}
	
	
	public void goToModifyPage() throws IOException {
	
		Stage primaryStage = Main.getStage();
		FXMLLoader loader =  new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/application/ModifyOrder.fxml").openStream());
		ModifyOrderController controller = (ModifyOrderController)loader.getController();
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
	}
	
	public void goToOrder() throws Exception {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewOrder.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	
	public void Home(ActionEvent event) {
		// get the primary stage from the main class
		Stage primaryStage = Main.getStage();
		
		// set the scene to the main home screen 
		primaryStage.setScene(MainPageController.getHomeScene());
	}


}
