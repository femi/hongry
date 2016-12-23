package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scenes {
	
		public void home() throws IOException {
			
			Stage primaryStage = Main.getStage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Homepage.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		}
		
		public void manageOrder() throws IOException {
			
			Stage primaryStage = Main.getStage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/ManageOrder.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		}
		
		public void manageEmployees() throws Exception {
			
			Stage primaryStage = Main.getStage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/ManageEmployees.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}

}
