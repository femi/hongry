package application;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class ManageTableController {
	
	
	public void createTables() {
		for (int i = 0; i < 10; i++) {
		
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void Home(ActionEvent event) {
		// get the primary stage from the main class
		Stage primaryStage = Main.getStage();
		
		// set the scene to the main home screen 
		primaryStage.setScene(MainPageController.getHomeScene());
	}

}
