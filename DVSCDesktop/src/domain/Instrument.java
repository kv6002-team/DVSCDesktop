package domain;

import java.util.Date;

/**
 * 
 * @author Callum
 *
 */
public class Instrument {

	private String instrumentName;
	private String serialNum;
	private Date expiryDate;
	
	public enum CheckStatus{
		SATISFACTORY,
		UNSATISFACTORY, 
	};
	
	private CheckStatus status;
		
	public Instrument(String instrumentName, String serialNum, Date expiryDate, CheckStatus status){
		this.instrumentName = instrumentName;
		this.serialNum = serialNum;
		this.expiryDate = expiryDate;
		this.status = status;
	}
	
	public String getInstrumentName(){
		return this.instrumentName;
	}
	
	public String getSerialNum(){
		return this.serialNum;
	}
	
	public Date getExpiryDate(){
		return this.expiryDate;
	}
	
	public CheckStatus getCheckStatus(){
		return this.status;
	}
	
	public void setInstrumentName(String instrumentName){
		this.instrumentName = instrumentName;
	}
	
	public void setSerialNum(String serialNum){
		this.serialNum = serialNum;
	}
	
	public void setCheckStatus(CheckStatus status){
		this.status = status;
	}
}
