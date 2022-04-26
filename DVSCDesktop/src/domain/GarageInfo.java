package domain;

import java.util.Date;
import java.util.ArrayList;

/**
 * GarageInfo Domain Class
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
	private ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
	
	// Constructor for GarageInfo
	public GarageInfo(String vts, String ownerName, String emailAddress, String telephoneNum, Date paidUntil, ArrayList<Instrument> instrumentList){
		this.vts = vts;
		this.ownerName = ownerName;
		this.emailAddress = emailAddress;
		this.telephoneNum = telephoneNum;
		this.paidUntil = paidUntil;
		this.instrumentList = instrumentList;
	}
	
	/* GarageInfo Getters and Setters
	 * -------------------------------------------------- */
	
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
	
	public ArrayList<Instrument> getInstrumentList(){
		return this.instrumentList;
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
	
	public void setInstrumentList(ArrayList<Instrument> instrumentList){
		this.instrumentList = instrumentList;
	}
}


