package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * This class allows the user to call a method in this class create 
 * and go to a specific scene.
 * 
 * @author femi
 *
 */
public class SceneController {
	
		/**
		 * 
		 * Goes to homepage. 
		 * 
		 * @throws IOException if FXML file cannot be loaded
		 */
		public void home() throws IOException {
			
			Stage primaryStage = Main.getStage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Homepage.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		}
		
		/**
		 * 
		 * Goes to manage order page.
		 * 
		 * @throws IOException if FXML file cannot be loaded 
		 */
		public void manageOrder() throws IOException {
			
			Stage primaryStage = Main.getStage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/ManageOrder.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		}
		
		/**
		 * 
		 * Goes to manage employees page,
		 * 
		 * @throws IOException if FXML file cannot be loaded 
		 */
		public void manageEmployees() throws IOException {
			
			Stage primaryStage = Main.getStage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/ManageEmployees.fxml"));
			Scene scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}

}
