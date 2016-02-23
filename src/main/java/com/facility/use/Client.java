package com.facility.use;

public class Client {
	
	private int clientID;
	private String clientName;
	
	public Client(int clientID, String clientName) {
		setClientID(clientID);
		setClientName(clientName);
	}
	
	public void setClientID(int clientIDNumber) {
		clientID = clientIDNumber;
	}
	
	public void setClientName(String clientFullName) {
		clientName = clientFullName;
	}
	
	public int getClientID() {
		return clientID;
	}
	
	public String getClientName() {
		return clientName;
	}
}
