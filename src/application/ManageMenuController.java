package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageMenuController implements Initializable {
	
	@FXML private TextField txtItem, txtPrice;
	@FXML private TableView<ItemBuffer> tvItems;
	@FXML private TableColumn<ItemBuffer, String> itemName;
	@FXML private TableColumn<ItemBuffer, Integer> itemPrice;
	@FXML private Button btnAdd;
	@FXML private Button btnDelete;

	public ObservableList<ItemBuffer> items = FXCollections.observableArrayList(Items.getItemObjects().values());
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnDelete.setDisable(true);
		
//		btnAdd.setDisable(true);
		
		tvItems.setItems(items);
		itemName.setCellValueFactory(new PropertyValueFactory<ItemBuffer, String>("item"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<ItemBuffer, Integer>("price"));
		
//		if (!txtItem.getText().isEmpty() || !txtPrice.getText().isEmpty() ) {
//			btnAdd.setDisable(false);
//		}
//	
		
	}

	public void addNewItem(ActionEvent event) throws Exception {
		
		String item = txtItem.getText();
		int price = Integer.parseInt(txtPrice.getText());
		Items.addItem(item, price);
		
		// add the item to the tableview 
		tvItems.getItems().add(Items.getItemObjects().get(item));
	
	}
	
	
	public void deleteItem(ActionEvent event) {
		

		// create a list to hold all of the Items 
		ObservableList<ItemBuffer> allItems;
				
		//create order object 
		ItemBuffer itemSelected;
				
		// get all of the current orders in the TableView
		allItems = tvItems.getItems();
				
		// put the current order selected into this variable 
		itemSelected = tvItems.getSelectionModel().getSelectedItem();
				
		// remove this order from the table view 
		allItems.remove(itemSelected);
		
		//---------------------------------------------------------
		
		// remove item object
		Items.removeItem(itemSelected.getItem());
		
	}
	

	
	public void home(ActionEvent event) throws IOException {
		// go back to home screen 
		Platform.getScene().home();
	}



}
