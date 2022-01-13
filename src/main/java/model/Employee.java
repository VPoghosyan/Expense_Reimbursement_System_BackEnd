package model;

public class Employee {
	public String fName;
	public String lName;
	public String password;
	public String userName;
	public String employeeRole;
	public String email;
	public String profilePic;
	
	
	
	
	public Employee() {
		
		
	}
	public Employee(String fName) {
		this.fName = fName;
		
	}
	public Employee(String userName, String password) {
		this.userName = userName;
		this.password = password;
		
	}
	
	public Employee(String userName, String employeeRole, String email) {
		this.userName = userName;
		this.employeeRole = employeeRole;
		this.email = email;
		
	}
	

	public Employee(String fName, String lName, String employeeRole, String email, 
			String userName, String profilePic) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.employeeRole = employeeRole;
		this.email = email;
		this.userName = userName;
		this.profilePic = profilePic;
	}
	@Override
	public String toString() {
		return "Employee [userName=" + userName + ", employeeRole=" + employeeRole + ", email=" + email + "]";
	}


	
	
	
	
	
//	public static void main(String[] args) {
//			
//		Employee ms = new Employee("s","ds","s","ds","s","https://images.unsplash.com/photo-1552318415-cc99d956c134?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1336&q=80");
//		System.out.println(ms.profilePic);
//		
//	}
	
	
	
	
	
	
	
}
