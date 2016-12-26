package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an employee of the restaurant management application. 
 * An employee can be of many types which grants them access to 
 * different parts of the system.
 *  
 * @author femi
 *
 */
public class Employees {

	private String employeeType;
	private static int employeeCount = 0;
	private String firstName;
	private String lastName;
	private int employeeNumber;
	private String password;
	private String username;
	private String log = "";

	/**
	 * 
	 * Creates a new Employee with with a given first name, last 
	 * name, username and password and employee type.
	 * 
	 * @param employeeType 
	 * @param firstname 
	 * @param lastname 
	 * @param username
	 * @param password
	 */
	public  Employees(String employeeType, String firstname, String lastname, String username, String password) {
		this.employeeType = employeeType;
		this.firstName = firstname;
		this.lastName = lastname;
		this.password = password;
		this.username = username.toLowerCase();
		employeeNumber = ++employeeCount;
		System.out.println("EMPLOYEE CREATED" + " | TYPE: " + this.employeeType.toUpperCase() + " | " + employeeNumber );

	}

	/**
	 * Gets the number of the employee.
	 * @return this employee's number 
	 */
	public int getEmployeeNumber() {
		return this.employeeNumber;
	}

	/**
	 * Gets the first name of this employee.
	 * @return this employee's first name 
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Gets the last name of this employee.
	 * @return this employee's last name 
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Gets the username of this employee.
	 * @return this employee's username 
	 */
	public String getUsername() {
		return this.username;
	}


	//-----------------------------------------------------------------------------

	/**
	 * Gets the type of this employee.
	 * @return this employee's type
	 */
	public String getEmployeeType() {
		return this.employeeType;
	}

	//-----------------------------------------------------------------------------

	/**
	 * Gets a log of the this employee's actions in the platform
	 * @return a log of this employee's actions
	 */
	public String getLog() {
		return this.log;
	}

	//-----------------------------------------------------------------------------

	/**
	 * Add messages to this employee's log.
	 * @param logMessage the message that is added to this employee's log
	 */
	public void addToLog(String logMessage) {
		this.log += logMessage + "  " + getTime() +  "\n" ;
	}
	
	
	/**
	 * Gets the current date and time of an order.
	 * @return the current date and time
	 */
	private String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date); //2016/11/16 12:08:43
	}

	/**
	 * Return the current password of this employee.
	 * @return this employee's password
	 */
	public String getPassword() {
		return this.password;
	}
}
