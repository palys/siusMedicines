package siusMedicines.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "spetialization")
	private String spetialization;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "username")
	private User user;
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Prescription> prescriptions;

	public Doctor() {
		
	}

	public Doctor(Long id, String name, String surname, String phoneNumber,
			String email, String spetialization, User user,
			Set<Prescription> prescriptions) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.spetialization = spetialization;
		this.user = user;
		this.prescriptions = prescriptions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpetialization() {
		return spetialization;
	}

	public void setSpetialization(String spetialization) {
		this.spetialization = spetialization;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", surname=" + surname
				+ ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", spetialization=" + spetialization + "]";
	}
}
