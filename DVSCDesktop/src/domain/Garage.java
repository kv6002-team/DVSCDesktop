package domain;

import java.util.Date;

/**
 * @author Callum
 */

public class Garage {
	
	private String vts;
	private String garageName;
	private String ownerName;
	private String emailAddress;
	private String telephoneNum;
	private Date paidUntil;
	
	public Garage(String vts, String garageName,String ownerName, String emailAddress, String telephoneNum, Date paidUntil){
		this.vts = vts;
		this.garageName = garageName;
		this.ownerName = ownerName; 
		this.emailAddress = emailAddress;
		this.telephoneNum = telephoneNum;
		this.paidUntil = paidUntil;
	}

	public String getVts() {
		return vts;
	}

	public String getGarageName() {
		return garageName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getTelephoneNum() {
		return telephoneNum;
	}

	public Date getPaidUntil() {
		return paidUntil;
	}
}
