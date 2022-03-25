package domain;

import java.util.Date;

/**
 * 
 * @author Callum
 *
 */

public class GarageInfo {

	private String vts;
	private String ownerName;
	private String emailAddress;
	private String telephoneNum;
	private Date paidUntil;
	
	public GarageInfo(String vts, String ownerName, String emailAddress, String telephoneNum, Date paidUntil){
		this.vts = vts;
		this.ownerName = ownerName;
		this.emailAddress = emailAddress;
		this.telephoneNum = telephoneNum;
		this.paidUntil = paidUntil;
	}
	
	public String getVts(){
		return this.vts;
	}
	
	public String getOwnerName(){
		return this.ownerName;
	}
	
	public String getEmailAddress(){
		return this.emailAddress;
	}
	
	public String getTelephoneNum(){
		return this.telephoneNum;
	}
	
	public Date getPaidUntil(){
		return this.paidUntil;
	}

	public void setVts(String vts){
		this.vts = vts;
	}

	public void setOwnerName(String ownerName){
		this.ownerName = ownerName;
	}
	
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}

	public void setTelephoneNum(String telephoneNum){
		this.telephoneNum = telephoneNum;
	}
	
	public void setPaidUntil(Date paidUntil){
		this.paidUntil = paidUntil;
	}
}


