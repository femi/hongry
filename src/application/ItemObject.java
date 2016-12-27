package application;

/**
 * 
 * This class is used for the generation of items on the menu at a restaurant.
 * Items are stored in Order objects and which contain the contents of an order.
 * 
 * @author femi
 *
 */
public class ItemObject {
	
	private String quantity;
	private int price;
	private String name;
	
	
	/**
	 * 
	 * This creates a new item object with a name, price and quantity.
	 * 
	 * @param name of the item 
	 * @param price of the 
	 * @param quantity of the item
	 */
	public ItemObject(String name, int price, String quantity) {
		this.quantity = quantity;
		this.price = price;
		this.name = name;
	}

	/**
	 * Gets the quantity of this item.
	 * @return the quantity of the item 
	 */
	public String getQuantity() {
		return quantity;
	}


	/**
	 * Sets the quantity of this item.
	 * @param quantity of this item
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	/**
	 * Gets the price of this item.
	 * @return the price of this item
	 */
	public int getPrice() {
		return price;
	}


	/**
	 * Sets the price of this item.
	 * @param price of this item
	 */
	public void setPrice(int price) {
		this.price = price;
	}


	/**
	 * Gets the name of this item.
	 * @return the name of this item
	 */
	public String getName() {
		return name;
	}


	/**
	 * Set the name of this item.
	 * @param name of this item
	 */
	public void setName(String name) {
		this.name = name;
	}

}
