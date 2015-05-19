package siusMedicines.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "user_role")
	private String userRole;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@OneToOne
	@JoinColumn(name = "user")
	private Patient patient;
	
	@OneToOne
	@JoinColumn(name = "user")
	private Doctor doctor;
	
	public User() {
		
	}

	public User(String username, String password, String userRole,
			boolean enabled, Patient patient, Doctor doctor) {
		super();
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.enabled = enabled;
		this.patient = patient;
		this.doctor = doctor;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", userRole=" + userRole + ", enabled=" + enabled + "]";
	}
	
	
}
