package siusMedicines.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription {

	@Id
	@Column(name = "id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@OneToMany(mappedBy = "prescription")
	private Set<Portion> portions;
	
	@ManyToOne
	@JoinColumn(name = "medicine_name")
	private Medicine medicine;
	
	public Prescription() {
		
	}

	public Prescription(String id, Patient patient, Doctor doctor,
			Set<Portion> portions, Medicine medicine) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.portions = portions;
		this.medicine = medicine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
