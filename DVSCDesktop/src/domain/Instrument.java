package domain;

import java.util.Date;

/**
 * 
 * @author Callum
 *
 */
public class Instrument {
	
	public enum CheckStatus{
		checked_satisfactory,
		checked_unsatisfactory,
		unchecked
	};
	
	private int ID;
	private String instrumentName;
	private String serialNum;
	private Date checkDate;
	private Date statusExpiryDate;
	private CheckStatus status;
		
	public Instrument(int ID, String instrumentName, String serialNum, Date checkDate, Date expiryDate, CheckStatus status){
		this.instrumentName = instrumentName;
		this.serialNum = serialNum;
		this.statusExpiryDate = expiryDate;
		this.status = status;
	}
	
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
}
