package domain;

import java.util.ArrayList;
import java.util.Date;

import connection.Connection;

/**
 * Garage Domain Class
 * 
 * @author Callum
 */

public class Garage {
	
	private GarageInfo gi;
	private String garageName;
	private GarageInfo ogGarageInfo;
	private String ogGarageName;
	private int ID;
	
	private Connection connection = new Connection();
	
	// Constructor for basic Garage
	public Garage(String garageName, int ID){
		this.garageName = garageName;
		this.ID = ID;
		this.ogGarageName = garageName;
	}
	
	// Constructor for full Garage.
	public Garage(String vts, String ownerName, String garageName, String emailAddress, String telephoneNum, Date paidUntil) {
		ArrayList<Instrument> instrumentList = null;
		this.garageName = garageName;
		this.gi = new GarageInfo(vts, ownerName, emailAddress, telephoneNum, paidUntil, instrumentList);
	}

	/* Garage Getters and Setters
	 * -------------------------------------------------- */
	
	public String getGarageName() {
		return garageName;
	}
	
	public String getOGGarageName() {
		return ogGarageName;
	}
	
	public Integer getGarageID(){
		return ID;
	}

	public GarageInfo getGarageInfo(){
		return gi;
	}
	
	public GarageInfo getOGGarageInfo() {
		return ogGarageInfo;
	}

	public void setGarageID(int ID) {
		this.ID = ID;
	}
	
	public void setGarageName(String garageName) { 
		this.garageName = garageName;
	}
	
	public void addGarageInfo() {
		this.gi = connection.getGarage(this.ID);
		this.ogGarageInfo = new GarageInfo(
				gi.getVts(),
				gi.getOwnerName(),
				gi.getEmailAddress(),
				gi.getTelephoneNum(),
				gi.getPaidUntil(),
				gi.getInstrumentList()
				);
	}
	
	public void setGarageInfo(GarageInfo gi) {
		this.gi = gi; 
	}
	
	@Override
	public String toString() {
		return garageName;
	}

}
