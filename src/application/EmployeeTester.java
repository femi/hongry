package application;

public class EmployeeTester {

	public static void main(String[] args) {
		
		Employee employee1 = new Employee("Staff");
		Employee employee2 = new Employee("Manager");
		
		employee1.setName("Jake", "Thompson");
		employee1.setPassword("hello");
		employee1.setUsername("Jake");
		System.out.println(employee1.getUsername());
		
		employee2.setName("John", "Doe");
		employee2.getEmployeeNumber();
		employee2.getEmployeetype();
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		//employee1.setPassword("Boom");
		

	}

	
}
