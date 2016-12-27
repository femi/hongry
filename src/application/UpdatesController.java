package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdatesController {
	
	@FXML private TextField inputNumber;
	@FXML private Label message;
	
	/**
	 * 
	 * Checks to see if the number that the person has entered is valid.
	 * If the number is incorrect the scene displays a relevant error message.
	 * If the number is valid the user's phone number is added to the platform.
	 * 
	 * @param event
	 */
	public void confirm(ActionEvent event) {
		String enteredNumber = inputNumber.getText();
		
		try {
			
			if (enteredNumber.length() != 13 || !enteredNumber.substring(0, 1).equals("+")) {
				throw new Exception();
			}
	
			// test to see if it is a number 
			Double.parseDouble(enteredNumber.substring(1));

			// set the number of the user
			Messages.setNumber(enteredNumber);
		
			// Close the stage 
			ManageOrderController.getWindow().close();
			
		} catch (Exception e) {
			// show user the error message 
			message.setVisible(true);
			message.setText("Invalid. e.g +447867662716");
		}

	}
	
	/**
	 * 
	 * Closes the window when an even occurs.
	 * 
	 * @param event
	 */
	public void cancel(ActionEvent event) {
		// Close the stage 
		ManageOrderController.getWindow().close();
	}

}
