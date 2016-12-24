package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employees {

	//-------------------------------VARIBLES------------------------------------

	private String employeeType;
	private static int employeeCount = 0;
	private String firstName;
	private String lastName;
	private int employeeNumber;
	private String password;
	private String username;
	private String log = "";

	//------------------------------CONSTRUCTOR----------------------------------

	public  Employees(String employeeType, String firstname, String lastname, String username, String password) {
		this.employeeType = employeeType;
		this.firstName = firstname;
		this.lastName = lastname;
		this.password = password;
		this.username = username.toLowerCase();
		employeeNumber = ++employeeCount;
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

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getUsername() {
		return this.username;
	}


	//-----------------------------------------------------------------------------

	// return the employee type 
	public String getEmployeeType() {
		return this.employeeType;
	}

	public String getEmployeeUsername() {
		return this.username;
	}

	//-----------------------------------------------------------------------------

	public String getLog() {
		return this.log;
	}

	//-----------------------------------------------------------------------------

	public void addToLog(String logMessage) {
		this.log += logMessage + "  " + getTime() +  "\n" ;
	}

	private String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date); //2016/11/16 12:08:43
	}

	// allows the user to get password
	public String getPassword() {
		return this.password;
	}
}
