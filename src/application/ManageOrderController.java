package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageOrderController implements Initializable  {

	private static Stage window;
	@FXML private TableView<Orders> tvOrderTable;
	@FXML private TableColumn<Orders, Integer> id;
	@FXML private TableColumn<Orders, Integer> tableNumber;
	@FXML private TableColumn<Orders, String> date;
	@FXML private TableColumn<Orders, String> orderTotal;
	@FXML private TableColumn<Orders, String> itemsOrdered;
	@FXML private Button delete;
	@FXML private Button modify;
	@FXML private TextField filterField;
	private int sourceIndex;
	private int visibleIndex;
	SortedList<Orders> sortedData;
	
	// List of all of the orders that are in the platform 
	public ObservableList<Orders> orders = FXCollections.observableArrayList(Platform.getAllOrders().values());
	public FilteredList<Orders> filteredData = new FilteredList<>(orders, p -> true);
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		// allows user to select multiple items
		tvOrderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// listens to see if an item in the table is selected 
		tvOrderTable.getSelectionModel().selectedItemProperty().addListener((order) -> {
			// if an item is selected then we enable the delete and modify buttons
			if ( !tvOrderTable.getSelectionModel().isEmpty() ) {
				modify.setDisable(false);
				delete.setDisable(false);
			}
			
			else {
				modify.setDisable(true);
				delete.setDisable(true);
			}
		});
		
		// put all of the orders into the TableView 
		tvOrderTable.setItems(orders);
		
		// assign the object variables to the table columns
		id.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("orderID"));
		tableNumber.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("tableNumber"));
		date.setCellValueFactory(new PropertyValueFactory<Orders, String>("timeOfOrder"));
		orderTotal.setCellValueFactory(new PropertyValueFactory<Orders, String>("experimentalOrderTotal"));
		itemsOrdered.setCellValueFactory(new PropertyValueFactory<Orders, String>("itemOrderedString"));
				 
	
		// SEARCH FINCTIONALITY with some help from http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
	
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(order -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }
	                // Compare first name and last name of every person with filter text.
	                String lowerCaseFilter = newValue.toLowerCase();

	                if ((order.getTableNumber() + "").contains(lowerCaseFilter)) {
	                    return true; // Filter matches table number
	                } 
	               
	                else if ((order.getOrderID() + "").toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches order id
	                } 
	                
	                else if ((order.getItemOrderedString()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matched items in order
	                }
	                
	                return false; // Does not match.
	            });
	        });
		 
		 sortedData = new SortedList<>(filteredData);
		 sortedData.comparatorProperty().bind(tvOrderTable.comparatorProperty());		 
		 tvOrderTable.setItems(sortedData);

	}
	
	public void deleteConformation(ActionEvent event ) throws IOException {
		
		visibleIndex = tvOrderTable.getSelectionModel().getSelectedIndex();
		sourceIndex = sortedData.getSourceIndexFor(orders, visibleIndex);
		
		Variables.setMasterData(orders);
		Variables.setSourceIndex(sourceIndex);
		Variables.setVisibleIndex(visibleIndex);
		
		Variables.setOrderSelected(tvOrderTable.getSelectionModel().getSelectedItem());
		Variables.setAllOrders(tvOrderTable);
		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageConfirmBox.fxml"));
		Scene scene = new Scene(root, 300, 200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

	public void modifyOrder(ActionEvent event) throws IOException {
		
		//create order object 
		Orders orderSelected;
				
		// put the current order selected into this variable 
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
		
		//System.out.println(Platform.getOrder(orderSelected.getOrderID()).getMoreOrderContents());
		
		Variables.setOrder(orderSelected.getOrderID());
		
		Stage primaryStage = Main.getStage();
		FXMLLoader loader =  new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/application/ModifyOrder.fxml").openStream());
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
	}
	
	public void closeOrder(ActionEvent event) {
		
		//create order object 
		Orders orderSelected;
						
		// put the current order selected into this variable 
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
		
		// set the order table to 0 
		if (orderSelected.getTableNumber() != 0) {
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		}
		
		orderSelected.setTableNumber(0);
		
		// refreshes the table to update the available ones
		tvOrderTable.refresh();
		
	}

	public static Stage getWindow() {
		return window;
	}
	
	public void exportSelectedItems(ActionEvent event) throws IOException { 
		
		// create order object 
		ObservableList<Orders> ordersSelected;
		
		// put the current order selected into this variable 
		ordersSelected = tvOrderTable.getSelectionModel().getSelectedItems();
		
		// export selected items to csv
		Platform.exportToFile(ordersSelected);
				
	}
	
	//--------------------------------------------------------------------------------------------
	
	public void Home(ActionEvent event) throws IOException {
		
		// go to homepage 
		Platform.getScene().home();
	}
	
	public void importOrders(ActionEvent event) throws IOException {
		
	
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Upload CSV");
		
		// only allow CSVs to be uploaded
		fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("CSV", "*.csv"));

		File file = fileChooser.showOpenDialog(Main.getStage());
		String path = "";
		
		try {
		
			if (file != null) {
				path = file.getPath();
				System.out.println(path);
			}
		
			Platform.readRecords(path);
			Platform.getScene().manageOrder();
			}
		
		catch (FileNotFoundException e) {
			System.out.println("NO FILE SELECTED");
		}
	}

}
