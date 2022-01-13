package controller;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import DBConnection.EmployeeDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import model.Claim;
import model.Employee;


public class ContEmployee {
	
	
	public static Connection getConnection() throws SQLException, IOException {
        Connection conn;

        FileInputStream propinput=new FileInputStream("C:\\Users\\vahan\\OneDrive\\Desktop\\VP\\Employment\\Revature\\Projects\\Project0_app_prop\\application.properties");
        Properties props =new Properties();
        props.load(propinput);
        String url = (String) props.get("URL");
        String username = (String) props.get("username");
        String password = (String) props.get("password");

        return DriverManager.getConnection(url, username, password);
    }
	
	
	public static Handler roleUpdate = ctx -> {
		
    	Employee emp = ctx.bodyAsClass(Employee.class);
    	EmployeeDAO.roleUpdate(emp.userName,emp.employeeRole);
    	System.out.println(emp.toString());
		
	    };
	    
    public static Handler newClaim = ctx -> {
    	
    	Claim nClaim = ctx.bodyAsClass(Claim.class);
    	System.out.println(nClaim.toString());
    	EmployeeDAO.newClaim(nClaim.userName, nClaim.fName, nClaim.lName,
    			nClaim.email,nClaim.claimStatus, nClaim.claimSubmitDate,
    			nClaim.claimDescription,nClaim.submitTime);
    	
    	
    };
    
    
    public static Handler getClaims = ctx -> {
    	
    	Claim nClaim = ctx.bodyAsClass(Claim.class);
    	ArrayList<Claim> allClaims = EmployeeDAO.getClaims(nClaim.userName);
    	
    	ctx.json(allClaims);
		return;
    	
    	
    };
    
    public static Handler claimDecision = ctx -> {
    	
    	Claim nClaim = ctx.bodyAsClass(Claim.class);
    	EmployeeDAO.claimDecision(nClaim.submitTime, nClaim.claimStatus);
    	
    	
    	return;
    	
    	
    };
	    
	    
	    
	    

	public static void employeeLogIn(Context ctx) throws SQLException {
		//use ContStudents::getStudents to connect to db using uName and uPass as arguments
		Employee emp = ctx.bodyAsClass(Employee.class);
		//String[] seperUandP = ctx.pathParam("logIn").split(" ");
		ArrayList<Employee> empInq = EmployeeDAO.employeeLogIn(emp.userName,emp.password);
		
		if(empInq.size()>0) {
			ctx.json(empInq.get(0));
			return;
		} 
		System.out.println("somehow I got here");
		ctx.json(new Employee());
		
		//empInq.size()>0? ctx.json(empInq.get(0)): ctx.json(new Employee());
		
        
    }
	
	public static Handler getEmps = ctx -> {
		
		Employee emp = ctx.bodyAsClass(Employee.class);
		
		ArrayList<Employee> empInq = EmployeeDAO.getEmployees(emp.fName);
		
		ctx.json(empInq);
		return;
		
	};
	
	
	
	
}
