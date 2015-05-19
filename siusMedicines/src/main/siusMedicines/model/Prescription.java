package siusMedicines.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@OneToMany(mappedBy = "prescription", fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private Set<Portion> portions;
	
	@ManyToOne
	@JoinColumn(name = "medicine_name")
	private Medicine medicine;
	
	public Prescription() {
		
	}

	public Prescription(Long id, Patient patient, Doctor doctor,
			Set<Portion> portions, Medicine medicine) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.portions = portions;
		this.medicine = medicine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Portion> getPortions() {
		return portions;
	}

	public void setPortions(Set<Portion> portions) {
		this.portions = portions;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", patient=" + patient + ", doctor="
				+ doctor + ", portions=" + portions + ", medicine=" + medicine
				+ "]";
	}

}
