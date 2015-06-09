package siusMedicines.model;

import java.sql.Date;
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
@Table(name = "patients")
public class Patient {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "pesel")
	private String pesel;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "birthdate")
	private Date birthdate;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "username")
	private User user;
	
	@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Prescription> prescriptions;

	public Patient() {
		
	}

	public Patient(Long id, String name, String surname, String pesel,
			String phoneNumber, Date birthdate, User user,
			Set<Prescription> prescriptions) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.phoneNumber = phoneNumber;
		this.birthdate = birthdate;
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

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname
				+ ", pesel=" + pesel + ", phoneNumber=" + phoneNumber
				+ ", birthdate=" + birthdate + "]";
	}
	
}
