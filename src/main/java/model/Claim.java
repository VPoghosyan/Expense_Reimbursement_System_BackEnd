package model;

public class Claim {
	public String userName;
	public String fName;
	public String lName;
	public String email;
	public String claimStatus;
	public String claimSubmitDate;
	public String claimDescription;
	public String submitTime;
	
	
	public Claim(String userName,  String fName,
	 String lName, String email, String claimStatus, String claimSubmitDate,
			String claimDescription,String submitTime) {
		super();
		this.userName = userName;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.claimStatus = claimStatus;
		this.claimSubmitDate = claimSubmitDate;
		this.claimDescription = claimDescription;
		this.submitTime = submitTime;
	}
	public Claim(String claimStatus,  String submitTime) {
		super();
		
		this.claimStatus = claimStatus;
		this.submitTime = submitTime;
	}
	
	
	


	public Claim() {
		super();
	}




	@Override
	public String toString() {
		return "Claim [userName=" + userName + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", claimStatus=" + claimStatus + ", claimSubmitDate=" + claimSubmitDate + ", claimDescription="
				+ claimDescription + ", submitTime=" + submitTime + "]";
	}




	
	
	
	
	
	
}
