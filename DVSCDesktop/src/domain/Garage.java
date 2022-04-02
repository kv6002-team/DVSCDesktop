package domain;

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
		this.gi = connection.getGarageInfo(this.ID);
	}
	
	@Override
	public String toString() {
		return garageName;
	}

}
