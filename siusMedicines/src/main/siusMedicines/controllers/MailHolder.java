package siusMedicines.controllers;

public class MailHolder {

	private String address;
	
	private String subject;
	
	private String message;
	
	public MailHolder(String address, String subject, String message) {
		super();
		this.address = address;
		this.subject = subject;
		this.message = message;
	}
	
	public MailHolder() {
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
