package DBConnection;


import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.Claim;
import model.Employee;




public class EmployeeDAO {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static float transferAmountStatic = 0;
	
    public EmployeeDAO(){
        
    }
    
    
    public static StringBuilder jointAccTracker = new StringBuilder();
    
    
    
    
  //Establish connection with the db
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
    
    
    //Login info query method
    public static ArrayList<Employee> employeeLogIn(String userN, String passW) throws SQLException {
    	ArrayList<Employee> empInq = new ArrayList<Employee>();
    	try(Connection conn = getConnection()){
            System.out.println("Connection successful");
            
    		PreparedStatement statement = conn.prepareStatement("Select * from "
    				+ "MifflinEmployees where username=? and userPassword=? LIMIT 1");
            
    		statement.setString(1, userN);
    		statement.setString(2, passW);
    		
            ResultSet results = statement.executeQuery();
            
            
            while(results.next()){

            empInq.add(new Employee(results.getString("fName"),results.getString("lName"),
            		results.getString("employeeRole"),results.getString("email"),
            		results.getString("username"), results.getString("profilePic")));
            	       	
            }
            
            
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
    	//System.out.println(userSensInfo.size());
    	//System.out.println(userSensInfo.get(0).printUserSensitive());//test
        return empInq;
    }
    
    //Get Employees
    public static ArrayList<Employee> getEmployees(String fName) throws SQLException {
    	ArrayList<Employee> empInq = new ArrayList<Employee>();
    	try(Connection conn = getConnection()){
    		System.out.println("Connection successful");
    		
    		PreparedStatement statement = conn.prepareStatement("Select * from "
    				+ "MifflinEmployees where LOWER(fname) like LOWER(?)");
    		
    		statement.setString(1, fName);
    		System.out.println(statement);//test
    		
    		ResultSet results = statement.executeQuery();
    		
    		
    		while(results.next()){
    			
    			empInq.add(new Employee(results.getString("fName"),results.getString("lName"),
    					results.getString("employeeRole"),results.getString("email"),
    					results.getString("username"), results.getString("profilePic")));
    			
    		}
    		
    		
    	}catch(Exception e){
    		//e.printStackTrace();
    		System.out.println(e.getMessage());
    	}
    	
    	
    	return empInq;
    }
    
    
    
    
    
    //Role update
    public static void roleUpdate(String userN, String newRole) throws SQLException {
    	
    	try(Connection conn = getConnection()){
    		System.out.println("Connection successful");
    		System.out.println(userN+":"+newRole);//test
    		PreparedStatement statement = conn.prepareStatement("update MifflinEmployees set"
    				+ " employeeRole=? where username=?");
    		
    		statement.setString(2, userN);
    		statement.setString(1, newRole);
    		System.out.println(statement.toString());//test
    		ResultSet results = statement.executeQuery();
    	
    		
    	}catch(Exception e){
    		//e.printStackTrace();
    		System.out.println(e.getMessage());
    	}
    	
    	//System.out.println(userSensInfo.size());
    	//System.out.println(userSensInfo.get(0).printUserSensitive());//test
    	
    }
    
    //Submit a new claim
    
	public static void newClaim(String userName,String fName,
			 String lName, String email, String claimStatus, String claimSubmitDate,
			String claimDescription,String submitTime) throws SQLException {
	    	
	    	try(Connection conn = getConnection()){
	    		System.out.println("Connection successful");
	    		
	    		PreparedStatement statement = conn.prepareStatement("insert into claims VALUES"
	    		+ "(?, ?, ?, ?, ?, ?, ?, ?)");
	    		
	    		statement.setString(1, userName);
	    		statement.setString(2, fName);
	    		statement.setString(3, lName);
	    		statement.setString(4, email);
	    		statement.setString(5, claimDescription);
	    		statement.setString(6, claimSubmitDate);
	    		statement.setString(7, claimStatus);
	    		statement.setString(8, submitTime);
	    		System.out.println(statement.toString());//test
	    		ResultSet results = statement.executeQuery();
	    	
	    		
	    	}catch(Exception e){
	    		//e.printStackTrace();
	    		System.out.println(e.getMessage());
	    	}
	    	
	    	//System.out.println(userSensInfo.size());
	    	//System.out.println(userSensInfo.get(0).printUserSensitive());//test
	    	
	    }
	
	
	//Get claims
	
	//Login info query method
    public static ArrayList<Claim> getClaims(String userN) throws SQLException {
    	ArrayList<Claim> claims = new ArrayList<Claim>();
    	try(Connection conn = getConnection()){
            System.out.println("Connection successful");
            PreparedStatement statement;
            if( userN.equals("mScott1965")) {
            	 statement = conn.prepareStatement("Select * "
            	    	+ "from claims");
            } else {
            	
            	statement = conn.prepareStatement("Select * "
            			+ "from claims where username=?");
            	
            	statement.setString(1, userN);
            }
            
            System.out.println(statement);
            ResultSet results = statement.executeQuery();
            
            
            while(results.next()){

            	claims.add(new Claim(results.getString("username"),
    results.getString("fName"),results.getString("lName"),results.getString("email"),
    results.getString("status"),results.getString("submitDate"),results.getString("description"),
            		results.getString("submitTime")));
            	       	
            }
            
            
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    	return claims;
    }
    
    //Claim Decision
    
    public static void claimDecision(String submitTime, String newStatus) throws SQLException {
    	
    	try(Connection conn = getConnection()){
    		System.out.println("Connection successful");
    		
    		PreparedStatement statement = conn.prepareStatement("update claims set"
    				+ " status=? where submitTime=?");
    		
    		statement.setString(2, submitTime);
    		statement.setString(1, newStatus);
    		System.out.println(statement.toString());//test
    		ResultSet results = statement.executeQuery();
    	
    		
    	}catch(Exception e){
    		//e.printStackTrace();
    		System.out.println(e.getMessage());
    	}
    	
    	//System.out.println(userSensInfo.size());
    	//System.out.println(userSensInfo.get(0).printUserSensitive());//test
    	
    }
    
    
    /*
    
    //Submit a new application
    public void submitNewApp( boolean jointAccount, int appNum) throws SQLException {
    	

    	
    	if(jointAccount) System.out.println("\n"+"Applicant "+ appNum);
    	System.out.println("\n"+"_______________________________________");
		System.out.println("Please fill out the following and follow format: ");
		String fName = inputVerifier("[A-Za-z]+", "" ,"First Name");
		
		String lName = inputVerifier("[A-Za-z]+", "" ,"Last Name");
		
		String email = inputVerifier("[A-Za-z0-9]+@[a-z]+\\.\\w+", "(Enter a valid email)" ,"Email");
		
		String dob = inputVerifier("^(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$", "(Format YYYY-MM-DD)" ,"DOB");
		
		
    	ArrayList<User> userSensInfo = new ArrayList<User>();
    
    	while(true) {
    		StringBuilder wayOut = new StringBuilder();
    		wayOut.setLength(0);
			
		
    	try(Connection conn = getConnection()){
    		String ssn = inputVerifier("([0-9]){3}-[0-9]{2}-[0-9]{4}", "(Format ex. 123-45-6789)" ,"SSN");

    		System.out.println("\n"+"Format rules for Username and Password are: use only letters and numbers, min. length 6 characters");

    		//System.out.print("Pick a Username: ");
    		String userN = inputVerifier("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", "" ,"Username");
    		//System.out.print("Pick a Password: ");
    		String passW = inputVerifier("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", "" ,"Password");
    		
    		
    		
    		//Hasher in action
    		sha1Hasher hasher = new sha1Hasher();
    		String accountId = hasher.encryptThisString(userN);
    		
    		
    		//Record the username of the applicant 1 in joint accounts
    		//and store it in a static variable so that you can pass it
    		//to applicant 2
    		if (appNum==1) {
        		jointAccTracker.setLength(0);
        		jointAccTracker.append(accountId);
        	}
    		
    		
    		
    		//Populate customerAccount table first
    		
//    		PreparedStatement statement0 = conn.prepareStatement("insert into "
//    				+ "customerAccounts values(?)");
//    		statement0.setString(1, accountId);
    		
            
    		PreparedStatement statement = conn.prepareStatement("insert into "
    				+ "bankCustomers values(?,?,?,?,?,?,?,?,?,?)");
            
    		statement.setString(1, userN);
    		statement.setString(2, passW);
    		statement.setString(3, fName);
    		statement.setString(4, lName);
    		statement.setString(5, dob);
    		statement.setString(6, email);
    		statement.setString(7, appNum==2? jointAccTracker.toString(): accountId);//if the 
    		//account is joint, then "jointAccTracker" will be used as an accountId of applicant
    		//2, because it has the accountId of the applicant 1
    		statement.setBoolean(8, jointAccount);
    		statement.setString(9, "pending");
    		statement.setString(10, ssn);
    		statement.execute();
    		

   
            
        }catch(Exception e){
        	if(e.getMessage().contains("duplicate key")) {
        		
        		System.out.println(e.getMessage().contains("username")? "Username taken, try again": "SSN already in the database");
        		wayOut.setLength(0);
        		wayOut.append("duplicate");
        		
        	} 
        	
            
        }
    	finally {
			if(wayOut.length()==0) {
				//System.out.println("you got it");
				
				break;
			}
		}
    	
    	}
        
 
        System.out.println("Thank You for applying");
    }
    
    
    //get financial info of a specific account
    public void getFinancialInfo(String userNameOrAccId, String searchBy, String whoIsAsking) {
    	
    	String searchStatement = null;
    	
    	if (searchBy.equals("userName")) {
    		searchStatement = "where username='"+userNameOrAccId;
    	}else {
    		searchStatement = "where accountid='"+userNameOrAccId;
    	}
    	System.out.println("_______________________________________");
    	ArrayList<User> specificUser =
				getSendUserInfo(searchStatement+"' and applicationStatus='approved'", "get bankCustomers");
    	
		
		if (specificUser.size()==0) {
			System.out.println("This application is still under review 🚧");
		} else {
			
			if(whoIsAsking.equals("admin")) {
				ArrayList<User> userAccountInfo = 
						getSendUserInfo(specificUser.get(0).accountId, "get Account");//we use the accountId here to request financial info from customerAccounts for a specific account 
				
				for(User u: userAccountInfo) {
					String accountType=specificUser.get(0).jointAccount?  "Joint": "Single";
					System.out.println("Account owner full name: "+specificUser.get(0).fName+" "+specificUser.get(0).lName);
					System.out.println("Accound ID: "+u.accountId);
					System.out.println("Account type: "+ accountType);
					System.out.println("Contact: "+ specificUser.get(0).email+"\n");
					System.out.println("Current balance: $"+u.currentBalance);
					System.out.println("Last transaction: $"+u.mostRecentTransact+"\n");
				}
				if(specificUser.size()>1) {
					ArrayList<User> userAccountInfo2 = 
							getSendUserInfo(specificUser.get(1).accountId, "get Account");
					System.out.println("\n "+"Account Co-owner");
					for(User u: userAccountInfo2) {
						
						System.out.println("Full Name: "+specificUser.get(1).fName+" "+specificUser.get(1).lName);
						System.out.println("Contact: "+ specificUser.get(1).email+"\n");
					}
				}
				financialDecision(userAccountInfo.get(0).currentBalance,userAccountInfo.get(0).mostRecentTransact,userAccountInfo.get(0).accountId, "admin");
			} else {
				ArrayList<User> userAccountInfo = 
						getSendUserInfo(specificUser.get(0).accountId, "get Account");//we use the accountId here to request financial info from customerAccounts for a specific account 
				
				for(User u: userAccountInfo) {
					System.out.println("Accound ID: "+u.accountId);
					System.out.println("Current balance: $"+u.currentBalance);
					System.out.println("Last transaction: $"+u.mostRecentTransact);
				}
				financialDecision(userAccountInfo.get(0).currentBalance,userAccountInfo.get(0).mostRecentTransact,userAccountInfo.get(0).accountId,"");

			}
		}
		System.out.println("_______________________________________");
    }
    
    
    //Admin review 
    public ArrayList<User> getSendUserInfo(String tabelStatement, String action) {
    	ArrayList<User> requestedUserInfo = new ArrayList<User>();
    	try(Connection conn = getConnection()){
            //System.out.println("Connection successful");
            
    		if(action.equals("get bankCustomers")) {//gets customer from bankCustomers table 
    			
    
    			PreparedStatement statement = conn.prepareStatement("Select * from bankCustomers "
    					+ tabelStatement);
    			
    			ResultSet results = statement.executeQuery();
                while(results.next()){

                	requestedUserInfo.add(new User(results.getString("fname"),results.getString("lname"),
                			results.getString("accountId"), results.getString("username"), results.getString("dob"),
                			results.getString("email"), results.getString("applicationStatus"), 
                			results.getBoolean("jointAccount"), results.getString("ssn")));
                	       	
                }
    			
    		} else if(action.equals("populate customerAccounts")) {//after a pending application is approved this action sends it to customerAccounts table
    			PreparedStatement statement = conn.prepareStatement(tabelStatement);
    			
    			statement.executeQuery();
    		} else if (action.equals("get Account")) {//gets financial info from customerAccounts table
    			
    			
    			PreparedStatement statement = conn.prepareStatement("SELECT * FROM customerAccounts "
    					+ "WHERE accountid='"+tabelStatement+"'");
    			
    			ResultSet results =statement.executeQuery();
    			while(results.next()){
	    			requestedUserInfo.add(new User(results.getFloat("currentBalance"),results.getFloat("mostRecentTransact"),
	    					results.getString("accountId")));
	    			
    			}
    			
    		} else if(action.equals("getTransactions")) {
    			//System.out.println("you are in transact");//test
    			PreparedStatement statement = conn.prepareStatement("SELECT transactionsHistory FROM transactions "
    					+ "WHERE accountid='"+tabelStatement+"'");
    			
    			ResultSet results =statement.executeQuery();
    			while(results.next()){
	    			requestedUserInfo.add(new User(results.getArray("transactionsHistory")));
	    			
    			}
    		}
    	  
            
        }catch(Exception e){
            //System.out.println(e.getMessage());//test
        }
        
 
        return requestedUserInfo;
    }
    
    //Admin pending application decision
    public String adminDecision(String decision, String uName) {
    	ArrayList<User> userSensInfo = new ArrayList<User>();
    	
    	
    	try(Connection conn = getConnection()){
    		String query = null;
    		if(decision.equals("approved")) {
    			query = "Update bankCustomers "
    					+ "SET applicationStatus='"+ decision+"'"+" WHERE username='"+uName+"'";
                
        		
    		} else {
    			query="DELETE FROM bankCustomers "+ "WHERE username='"+uName+"'";
    			
    					
    		}
    		PreparedStatement statement = conn.prepareStatement(query);
    		statement.executeQuery();
    
            
        }catch(Exception e){
        	//if(!e.getMessage().equals("No results were returned by the query.")) e.printStackTrace();
            
            
        }
        
 
        return "Application "+ decision + (decision.equals("approved")? " ✅": " ❌")+"\n";
    }
    
    
    public void transferFunds(String accId) {
    	
    	String otherAccId = inputVerifier("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", "" ,"Receiving Account ID");
    	ArrayList<User> transferFrom = getSendUserInfo(accId, "get Account");
    	depositWithdraw( "Withdrawal",  transferFrom.get(0).currentBalance,  transferFrom.get(0).mostRecentTransact,  transferFrom.get(0).accountId, 0);
    	
    	ArrayList<User> transferTo = getSendUserInfo(otherAccId, "get Account");
    	depositWithdraw( "Deposit", transferTo.get(0).currentBalance,  transferTo.get(0).mostRecentTransact,  transferTo.get(0).accountId, transferAmountStatic);
    	
    }
    
    
    
    public void depositWithdraw(String action, Float currentBalance, Float lastTransaction, String accId, float transferAmount) {
    	float amount = 0;
    	float newBalance = 0;
    	float latestTransaction =  0;
    	
    	while(true) {
    		
    		boolean number = true;
    		try {
    			if(transferAmount==0) {
    				
    				Scanner scanner = new Scanner(System.in);
    				System.out.println("Amount: ");
    				amount = scanner.nextFloat();
    			}else amount=transferAmount;

    	        } 
    	        catch (InputMismatchException ex) {
    	           System.out.println("Invalid input, try again");
    	           number=false;
    	        }
    			if (number) {
    				if(amount>0) {
    					if(action.equals("Deposit")) {
    						newBalance = currentBalance + amount;
    						latestTransaction = amount;
    						//System.out.println(newBalance);//test
    						
    						break;
    						
    					} else {
    						if(amount<= currentBalance) {
    							newBalance = currentBalance - amount;
    							latestTransaction = -amount;
    							//System.out.println(newBalance);//test
    							break;
    						}
    						else System.out.println("Overdraft Warning! try a different amount");
    					}
    				} else System.out.println("Enter a positive number, try again");
    				
    			}
    		
    	}
    	transferAmountStatic = amount;
    	sendTransactionHistory(accId,action,latestTransaction+"");//transaction record sent
    	try(Connection conn = getConnection()){
            //System.out.println("Connection successful");
            
    		PreparedStatement statement = conn.prepareStatement("update customerAccounts set currentbalance=?,"
    				+ " mostRecentTransact=? where accountid='"+ accId + "'");
            
    		statement.setFloat(1, newBalance);
    		statement.setFloat(2, latestTransaction);
    		
    		
            ResultSet results = statement.executeQuery();
            
            
            
        }catch(Exception e){
            //e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    	
    }
    
    public void financialDecision(Float currentBalance, Float lastTransaction, String accId, String whoIsIt) {
    	
    	System.out.println("_______________________________________"+"\n");
    	System.out.println("What would you like to do next");
    	System.out.println("1. Deposit funds.");
    	System.out.println("2. Withdraw funds.");
    	System.out.println("3. Transfer funds to another account.");
    	System.out.println("4. View transaction history.");
    	System.out.println("5. Close this account.");
    	System.out.println("6. "+ (whoIsIt.equals("admin")? "Go back to admin menu": "Log out."));
    	int option = inputChecker();
    	String action = "";
    	switch (option) {
		case 1:
			action = "Deposit";
			depositWithdraw(action, currentBalance,lastTransaction, accId,0);
			
			break;
		case 2:
			action = "Withdrawal";
			depositWithdraw(action, currentBalance,lastTransaction, accId,0);
			break;
		case 3:
			action = "Transfer";
			transferFunds(accId);
			
			break;
		case 4:
			
			getTransactionHistory(accId);
			break;
		case 5:
			
			closeAccount(accId);
			break;
			
		default:
			return;
		}
    	
    	System.out.println( action.length()==0? "": action+ " complete.");
    	
    }
    
    public static int inputChecker() {
    	int inputInt = 0;
    	while(true) {
			
			StringBuilder excepOccured = new StringBuilder();
        	try {
        		Scanner scanner = new Scanner(System.in);
        		excepOccured.setLength(0);
                System.out.println("\n"+"Enter option: ");
                inputInt = scanner.nextInt();  
                

             } 
             catch (InputMismatchException ex) {
                System.out.println("Invalid input, try again ");
                excepOccured.append("it did");
             }
        	finally {
				if(excepOccured.isEmpty()) break;
			}
        	
        }
    	return inputInt;
    }
    
    public static void getTransactionHistory(String accId) {
    	System.out.println("\n"+"_______________________________________");
    	System.out.println("Transaction history:"+"\n");
    	UserDAO tHistoryUser = new UserDAO();
    	ArrayList<User> userTranHis = tHistoryUser.getSendUserInfo(accId, "getTransactions");
    	
    	if(userTranHis.size()>0) {
    		String sTest;
        	sTest=userTranHis.get(0).transactionHistory.toString();
        	String sTest0 = sTest.substring(1,sTest.length()-1);
        	String[] tTest1 = sTest0.split(",");
        	for(String s: tTest1) {
    		System.out.println(s.substring(1,s.length()-1));
        	}
    	} else System.out.println("The account doesn't exist");
    }
    
    public void sendTransactionHistory(String accId, String action, String lastTransaction ) {
    	
    		String timeStamp0 = new SimpleDateFormat("HH:mm:ss  dd.MM.yyyy").format(new Date());
        	String transHis = "'{\""+action+" | "+ lastTransaction+" | "+timeStamp0+"\"}'";
        	ArrayList<User> sendUserTranHis = getSendUserInfo("UPDATE transactions SET transactionsHistory = transactionsHistory || "+transHis+" where accountid='"+accId+"'", "populate customerAccounts");
        	
    	
    }
    
    public void closeAccount(String accId) {
    	getSendUserInfo("delete from bankCustomers where accountid='"+accId+"'", "populate customerAccounts");
    	getSendUserInfo("delete from customerAccounts where accountid='"+accId+"'", "populate customerAccounts");
    	getSendUserInfo("delete from transactions where accountid='"+accId+"'", "populate customerAccounts");
    }
    
    */
    public static void main(String[] args) throws SQLException {
    	
//    	EmployeeDAO testUser = new EmployeeDAO();
//    	float firstF = 10;
//    	float secondF = 0;
//    	testUser.depositWithdraw("deosit",firstF,secondF,"7323a5431d1c31072983a6a5bf23745b655ddf59");
//    	
//    	testUser.transferFunds("7323a5431d1c31072983a6a5bf23745b655ddf59");
//    	ArrayList<User> userTranHis = testUser.getSendUserInfo("7323a5431d1c31072983a6a5bf23745b655ddf59", "getTransactions");
//    	System.out.println(userTranHis.size());
//    	String sTest;
//    	 
//    	
//    	sTest=userTranHis.get(0).transactionHistory.toString();
//    	String sTest0 = sTest.substring(1,sTest.length()-1);
//    	String[] tTest1 = sTest0.split(",");
//    	for(String s: tTest1) {
//		System.out.println(s.substring(2,s.length()-2));
//	}
//empList    	
//    	testUser.closeAccount("7323a5431d1c31072983a6a5bf23745b655ddf59");
    	
//    	testUser.inputVerifier("[A-Za-z0-9]+@[a-z]+\\.\\w+", "(Enter a valid email)" ,"Email");
    	/*"dSchrute1975", "dunder1975"
    	for(Employee e: testUser.employeeLogIn("mScott1965", "dunder1965")) {
    		System.out.println(e.toString());
    	}
    	 */
    	//testUser.roleUpdate("mSct1965", "bbb");
    	/*
    	for(Claim c: testUser.getClaims("mScott1965")) {
    		System.out.println(c.toString());
    	}
    	*/
//    	testUser.claimDecision("00:00:00", "approved");
//    	testUser.getEmployees("m%");
    	
    }
    
}

