package application;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Variables {
	
	public static Integer order;
	
	//------------CONFORMATION BOX-----------
	public static TableView<Orders> allOrders;
	public static Orders orderSelected;
	public static ObservableList<Orders> masterData;
	public static int visibleIndex;
	public static int sourceIndex;
	//---------------------------------------
	
	public static ObservableList<Orders> getMasterData() {
		return masterData;
	}

	public static void setMasterData(ObservableList<Orders> masterData) {
		Variables.masterData = masterData;
	}

	public static int getVisibleIndex() {
		return visibleIndex;
	}

	public static void setVisibleIndex(int visibleIndex) {
		Variables.visibleIndex = visibleIndex;
	}

	public static int getSourceIndex() {
		return sourceIndex;
	}

	public static void setSourceIndex(int sourceIndex) {
		Variables.sourceIndex = sourceIndex;
	}
	
	public static TableView<Orders> getAllOrders() {
		return allOrders;
	}

	public static void setAllOrders(TableView<Orders> allOrders) {
		Variables.allOrders = allOrders;
	}

	public static Orders getOrderSelected() {
		return orderSelected;
	}

	public static void setOrderSelected(Orders orderSelected) {
		Variables.orderSelected = orderSelected;
	}

	public static int getOrder() {
		return order;
	}

	public static void setOrder(int order) {
		Variables.order = order;
	}
}
