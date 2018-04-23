package net.laskarisn.configmanager.various;

import java.io.Serializable;

public class CustomResponse implements Serializable{

	private static final long serialVersionUID = -6651942773001548141L;

	private String reason;

	public CustomResponse() {
		
	}
	
	public CustomResponse(String reason) {
		this.reason = reason;
	}
	
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	
}
