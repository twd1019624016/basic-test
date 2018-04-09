package digester;

public class Employee {

	private String firstName;
	private String lastName;
	public Employee() {
		System.out.println("creating employee");
	}
	public String getFirstName() {
		
		return firstName;
	}
	public void setFirstName(String firstName) {
		System.out.println("设置firstName");
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		System.out.println("设置lastNamee");
		this.lastName = lastName;
	}
	
	public void printName() {
		System.out.println("name is "+ firstName + "" + lastName);
	}
	
	
}
