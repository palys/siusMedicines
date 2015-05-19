package siusMedicines.model;

import java.sql.Date;
import java.sql.Timestamp;
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
	
	@OneToMany(mappedBy = "prescription", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
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
	
	public int portionsLeft() {
		int count = 0;
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		for (Portion p : portions) {
			if (!p.isTaken() && p.getTakeTime().after(currentTime)) {
				count++;
			}
		}
		return count;
	}
	
	public int totalPortions() {
		return portions.size();
	}
	
	public Date nextPortionDate() {
		Timestamp nextPortionDate = new Timestamp(0);
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		if (portionsLeft() != 0) {
			for (Portion p : portions) {
				if (!p.isTaken() && currentTime.before(p.getTakeTime())) {
					nextPortionDate = p.getTakeTime();
					break;
				}
			}
			
			for (Portion p : portions) {
				if (nextPortionDate.before(p.getTakeTime()) && !p.isTaken()) {
					nextPortionDate = p.getTakeTime();
				}
			}
		}
		return new Date(nextPortionDate.getTime());
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", patient=" + patient + ", doctor="
				+ doctor + ", portions=" + portions + ", medicine=" + medicine
				+ "]";
	}

}
