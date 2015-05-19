package siusMedicines.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Patient> patients;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Doctor> doctors;
	
	public User() {
		
	}

	public User(String username, String password, String userRole,
			boolean enabled, Set<Patient> patients, Set<Doctor> doctors) {
		super();
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.enabled = enabled;
		this.patients = patients;
		this.doctors = doctors;
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

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", userRole=" + userRole + ", enabled=" + enabled + "]";
	}
	
	
}
