package application;

public class ItemBuffer {
	
	private String quantity;
	private int price;
	private String item;
	
	
	public ItemBuffer(String item, int price, String quantity) {
		this.quantity = quantity;
		this.price = price;
		this.item = item;
	}

	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}

}
