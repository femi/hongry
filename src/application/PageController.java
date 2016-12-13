package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PageController implements Initializable {
	
	@FXML
	private ComboBox<String> cbItems;
	
	@FXML
	private TextArea txtTextArea;
	
	@FXML
	private TextField txtQuantity;
	
	public ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();

	ObservableList<String> olist = FXCollections.observableArrayList(Items.items.keySet());
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cbItems.setItems(olist);
	}
	
	public void makeOrder(ActionEvent event) {
		Order order = new Order(1);
		order.addMultipleOrderItems(orderList);
		System.out.println("Items Added.");
	}
	
	public void addItem(ActionEvent event) {
		String text = cbItems.getSelectionModel().getSelectedItem();
		String quantity = txtQuantity.getText();
		ArrayList<String> order = new ArrayList<String>();
		
		order.add(text);
		order.add(quantity);
		orderList.add(order);
		
		//System.out.println(text);
		txtTextArea.appendText(text + "    " + quantity + "    £" + Items.getItemPrice(text) * Integer.parseInt(quantity) + "\n");
	}

}
