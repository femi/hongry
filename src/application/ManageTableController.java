package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManageTableController {
	
	@FXML private Button btn1 = new Button("1");
	@FXML private Button btn2 = new Button("2");
	@FXML private Button btn3 = new Button("3");
	@FXML private Button btn4 = new Button("4");
	@FXML private Button btn5 = new Button("5");
	@FXML private Button btn6 = new Button("6");
	@FXML private Button btn7 = new Button("7");
	@FXML private Button btn8 = new Button("8");
	@FXML private Button btn9 = new Button("9");
	
	
	public void modifyOrder(ActionEvent event) throws IOException {
		
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
	
	
	public void Home(ActionEvent event) {
		// get the primary stage from the main class
		Stage primaryStage = Main.getStage();
		
		// set the scene to the main home screen 
		primaryStage.setScene(MainPageController.getHomeScene());
	}

}
