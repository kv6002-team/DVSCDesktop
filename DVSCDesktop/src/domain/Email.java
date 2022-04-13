package domain;

import java.util.Date;

/**
 * 
 * @author Liam
 *
 */

public class Email {
	
	private int instrumentID;
	private Date sendDate;
	private String content;
	
	public Email(int instrumentID, Date sendDate) {
		this.instrumentID = instrumentID;
		this.sendDate = sendDate;
	}
	
	public int getInstrumentID() {
		return instrumentID;
	}
	
	public void setInstrumentID(int instrumentID) {
		this.instrumentID = instrumentID;
	}
	
	public Date getSendDate() {
		return sendDate;
	}
	
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
