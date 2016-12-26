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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * This class controls the manage order page.
 * 
 * @author femi
 *
 */
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
		
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
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
		orderTotal.setCellValueFactory(new PropertyValueFactory<Orders, String>("orderTotalObjects"));
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
	
	/**
	 * 
	 * Takes the user to the delete confirmation window.
	 * 
	 * @param event
	 * @throws IOException if the FXML page cannot be loaded 
	 */
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

	/**
	 * 
	 * Takes the user to the modify order page.
	 * 
	 * @param event
	 * @throws IOException if the FXML page cannot be loaded 
	 */
	public void modifyOrder(ActionEvent event) throws IOException {
		
		//create order object 
		Orders orderSelected;
				
		// put the current order selected into this variable 
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
				
		Variables.setOrder(orderSelected.getOrderID());
		
		Stage primaryStage = Main.getStage();
		FXMLLoader loader =  new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/application/ModifyOrder.fxml").openStream());
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
	}
	
	/**
	 * 
	 * Closes the selected order in the TableView, the order is closed
	 * by setting the order's table to 0
	 * 
	 * @param event
	 */
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

	/**
	 * Gets the window of the delete confirmation box.
	 * @return the window of the delete confirmation box
	 */
	public static Stage getWindow() {
		return window;
	}
	
	
	/**
	 * 
	 * Allows a user to export multiple orders that have been selected from 
	 * a TableView to a CSV file. The user can select the directory of the 
	 * CSV file that they want to export
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void exportSelectedItems(ActionEvent event) throws IOException { 
		
		String path;
		
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		
		final File chosenDirectory = directoryChooser.showDialog(Main.getStage());
		
		if (chosenDirectory != null) {
			
			path = chosenDirectory.getAbsolutePath() + "/order_data.csv/";
			System.out.println(path);
			
			// create order object 
			ObservableList<Orders> ordersSelected;
			
			// put the current order selected into this variable 
			ordersSelected = tvOrderTable.getSelectionModel().getSelectedItems();
			
			// export selected items to csv
			Platform.exportToFile(ordersSelected, path);
		}
		
		else {
			System.out.println("NO DIRECTORY SELECTED");
		}
		
			
	}
	
	/**
	 * 
	 * Returns the user to the homepage.
	 * 
	 * @param event
	 * @throws IOException if the FXML page cannot be loaded
	 */
	public void Home(ActionEvent event) throws IOException {
		
		// go to homepage 
		Platform.getScene().home();
	}
	
	/**
	 * 
	 * Allows the user to import a CSV of orders into the platform which 
	 * are the populated into the TableView.
	 * 
	 * @param event
	 * @throws IOException if a file cannot be found
	 */
	public void importOrders(ActionEvent event) throws IOException {
		
		// create new file 
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Upload CSV");
		
		// only allow CSVs to be uploaded
		fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("CSV", "*.csv"));
		
		// show the file chooser in the main stage 
		File file = fileChooser.showOpenDialog(Main.getStage());
		String path = "";
		
		try {
			if (file != null) {
				path = file.getPath();
				System.out.println(path);
			}
			
			// parse the records from the selected CSV
			Platform.parseCSV(path);
			
			// reload the current page
			Platform.getScene().manageOrder();
			
			}
		
		catch (FileNotFoundException e) {
			System.out.println("NO FILE SELECTED");
		}
	}

}
