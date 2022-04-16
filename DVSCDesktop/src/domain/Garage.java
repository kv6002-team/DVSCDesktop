package domain;

import java.util.ArrayList;
import java.util.Date;

import connection.Connection;

/**
 * @author Callum
 */

public class Garage {
	
	private GarageInfo gi;
	private String garageName;
	private int ID;
	
	private Connection connection = new Connection();
	
	public Garage(String garageName, int ID){
		this.garageName = garageName;
		this.ID = ID;
	}
	
	public Garage(String vts, String ownerName, String garageName, String emailAddress, String telephoneNum, Date paidUntil) {
		ArrayList<Instrument> instrumentList = null;
		this.garageName = garageName;
		this.gi = new GarageInfo(vts, ownerName, emailAddress, telephoneNum, paidUntil, instrumentList);
	}

	public String getGarageName() {
		return garageName;
	}
	
	public int getGarageID(){
		return ID;
	}

	public GarageInfo getGarageInfo(){
		return gi;
	}

	public void setGarageID(int ID){
		this.ID = ID;
	}
	
	public void setGarageName(String garageName){
		this.garageName = garageName;
	}
	
	public void addGarageInfo(){
		this.gi = connection.getGarage(this.ID);
	}
	
	@Override
	public String toString() {
		return garageName;
	}

}
