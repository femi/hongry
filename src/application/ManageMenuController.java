package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ManageMenuController {
	
	@FXML private Label lblStatus;
	@FXML private TextField txtItem, txtPrice;

	public void addNewItem(ActionEvent event) throws Exception {

		String item = txtItem.getText();
		int price = Integer.parseInt(txtPrice.getText());
		Items.addItem(item, price);
		String message = item + " has been added to your menu";
		lblStatus.setText(message);
		lblStatus.setVisible(true);

	}

}
