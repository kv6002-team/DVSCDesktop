package domain;

import java.util.Date;

/**
 * Instrument Domain Class.
 * 
 * @author Callum
 *
 */
public class Instrument {
	
	public enum CheckStatus{
		CHECKED_SATISFACTORY("checked_satifactory"),
		CHECKED_UNSATISFACTORY("checked_unsatisfactory"),
		UNCHECKED("unchecked");
		
		public final String value;
		private CheckStatus(String value) {
			this.value = value;
		}
		
		// Method to get CheckStatus from String.
		public static CheckStatus of(String value) {
			switch (value) {
			case "checked_satisfactory" : return CHECKED_SATISFACTORY;
			case "checked and satisfactory" : return CHECKED_SATISFACTORY;
			case "checked_unsatisfactory" : return CHECKED_UNSATISFACTORY;
			case "checked and unsatisfactory" : return CHECKED_UNSATISFACTORY;
			case "unchecked" : return UNCHECKED;
			default : throw new IllegalArgumentException("Invalid CheckStatus value");
			}
		}
		
		// Method to convert CheckStatus to String.
		public static String checkStatusToString(CheckStatus status) {
			switch(status) {
			case CHECKED_SATISFACTORY : return "checked and satisfactory";
			case CHECKED_UNSATISFACTORY : return "checked and unsatisfactory";
			case UNCHECKED : return "unchecked";
			default : throw new IllegalArgumentException("Invalid CheckStatus value");
			}
		}
	};
	
	private int ID;
	private String instrumentName;
	private String serialNum;
	private Date checkDate;
	private Date statusExpiryDate;
	private CheckStatus status;
	
	// Constructor for Instrument with ID.
	public Instrument(int ID, String instrumentName, String serialNum, Date checkDate, Date expiryDate, CheckStatus status){
		this.ID = ID;
		this.instrumentName = instrumentName;
		this.serialNum = serialNum;
		this.checkDate = checkDate;
		this.statusExpiryDate = expiryDate;
		this.status = status;
	}
	
	// Constructor for Instrument without ID.
	public Instrument(String instrumentName, String serialNum, Date checkDate, Date expiryDate, CheckStatus status) {
		this.instrumentName = instrumentName;
		this.serialNum = serialNum;
		this.checkDate = checkDate;
		this.statusExpiryDate = expiryDate;
		this.status = status;
	}
	
	/* Instrument Getters and Setters
	 * -------------------------------------------------- */
	
	public int getInstrumentID() {
		return this.ID;
	}
	
	public String getInstrumentName() {
		return this.instrumentName;
	}
	
	public String getSerialNum() {
		return this.serialNum;
	}
	
	public Date getCheckDate() {
		return this.checkDate;
	}
	
	public Date getStatusExpiryDate() {
		return this.statusExpiryDate;
	}
	
	public CheckStatus getCheckStatus() {
		return this.status;
	}
	
	public void setInstrumentID(int ID) {
		this.ID = ID;
	}
	
	public void setInstrumentName(String instrumentName){
		this.instrumentName = instrumentName;
	}
	
	public void setSerialNum(String serialNum){
		this.serialNum = serialNum;
	}
	
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public void setStatusExpiryDate(Date statusExpiryDate) {
		this.statusExpiryDate = statusExpiryDate;
	}
	
	public void setCheckStatus(CheckStatus status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		return instrumentName;
	}
}
