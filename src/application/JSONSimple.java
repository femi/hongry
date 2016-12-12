package application;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.ItemList;

public class JSONSimple {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
 
		JSONObject object = new JSONObject();
	

		ItemList food = new ItemList();
		food.add(0, "Chicken");
		food.add(1, "Chicken");
		food.add(2, "Chicken");
		
		JSONObject order_1 = new JSONObject();
		order_1.put("order_1", food);
		
		JSONObject order_2 = new JSONObject();
		order_2.put("order_2", food);
		
		JSONArray orders = new JSONArray();
		orders.add(order_1);
		orders.add(order_2);
		
				
		object.put("orders", orders);

		
		
		
		JSONObject obj = new JSONObject();
		obj.put("order_id", "234");
		obj.put("table_id", "4");
 
		JSONArray company = new JSONArray();
		company.add("Compnay: eBay");
		company.add("Compnay: Paypal");
		company.add("Compnay: Google");
		obj.put("Company List", company);
 
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("/Users/femi/Drive/work/PROGRAMMING/RBMSCoursework/data/datafile1.txt")) {
			file.write(object.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + object);
		}
	}
}
