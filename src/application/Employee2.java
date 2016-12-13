package application;

import java.util.Scanner;

public class Employee2 {
	
	//-------------------------------VARIBLES------------------------------------
	
		private String employeeType;
		private static int employeeCount = 0;
		private String firstName;
		private String lastName;
		private int employeeNumber;
		private String password;
		private String username;
		
		//------------------------------CONSTRUCTOR----------------------------------

		public  Employee2(String employeeType, String firstname, String lastname, String username, String password) {
			this.employeeType = employeeType;
			this.firstName = firstname;
			this.lastName = lastname;
			this.password = password;
			this.username = username.toLowerCase();
			employeeNumber = ++this.employeeCount;
			System.out.println("EMPLOYEE CREATED" + " | TYPE: " + this.employeeType.toUpperCase() + " | " + employeeNumber );
			
		}
		
		//--------------------------------METHODS-------------------------------------
		
		// sets the employee's firstname and lastname 
		public void setName(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
			//System.out.println(firstName + " " + lastName);
		}
		
		//-----------------------------------------------------------------------------

		// returns employee number 
		public int getEmployeeNumber() {
			return this.employeeNumber;
		}
		
		//-----------------------------------------------------------------------------

		// gets the employee's full name 
		public String getEmployeeName() {
			System.out.println(this.firstName + " " + this.lastName);
			return this.firstName + " " + this.lastName;
		}
		
		//-----------------------------------------------------------------------------

		// return the employee type 
		public String getEmployeetype() {
			if (this.employeeType.equals("Manager")) {
				return "Manager";
			}
			else {
				return "Staff";
			}
		}
		
		public String getEmployeeUsername() {
			return this.username;
		}
		
		//-----------------------------------------------------------------------------
		
//		// validates whether the employee's password is correct or not
//		private boolean vaildatePassword(String password) {
//			if (this.password.equals(password)) {
//				return true;
//			}
//			else { return false; }
//		}
		
		//-----------------------------------------------------------------------------
		
//		// allows the eployee to enter a password 
//		public String passwordEntry() {
//			Scanner input = new Scanner(System.in);
//			System.out.println("Please enter your new password: ");
//			password = input.nextLine();
//			input.close();
//			return password;
//		}
//		
		//-----------------------------------------------------------------------------
//		
//		// allows the user employee to set their username
//		public void setUsername(String username) {
//			this.username = username.toLowerCase();
//		}
//		
		//-----------------------------------------------------------------------------
		
		
		// allows the user to get username
		public String getUsername() {
			return this.username;
		}
		
		//-----------------------------------------------------------------------------
		
		// allows the user to get password
		public String getPassword() {
			return this.password;
		}
		
//		public void changePassword(String password) {
//			
//				if (password.equals(this.password)) {
//					//System.out.println("Please enter your new password");
//					String new_password = passwordEntry();
//					this.password = new_password;
//					System.out.println("Your password has been changed");
//				}
//				
//				else {
//					System.out.println("You have entered an incorrect password, please try again.");
//				}
//		}
		
		//-----------------------------------------------------------------------------
		
//		// set the password of an employee
//		public void setPassword(String password) {
//			
//			if (this.password ==  null) {
//				this.password = password;
//				//System.out.println("Your password has been set :D");
//			}
//			
//			else {
//				System.out.println("Please use the changePassword method, your password has already been set.");
//			}
//			
//		}
//		
//		public String getPassword() {
//			return this.password;
//		}
//		
		//-----------------------------------------------------------------------------

}
