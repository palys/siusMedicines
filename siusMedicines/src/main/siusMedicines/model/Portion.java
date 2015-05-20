package siusMedicines.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "portions")
public class Portion {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "size")
	private double size;
	
	@Column(name = "take_time")
	private Timestamp takeTime;
	
	@Column(name = "taken")
	private boolean taken;
	
	@ManyToOne
	@JoinColumn(name = "prescription_id")
	private Prescription prescription;
	
	public Portion() {
		
	}

	public Portion(Long id, String unit, double size, Timestamp takeTime,
			boolean taken, Prescription prescription) {
		super();
		this.id = id;
		this.unit = unit;
		this.size = size;
		this.takeTime = takeTime;
		this.taken = taken;
		this.prescription = prescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Timestamp getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Timestamp takeTime) {
		this.takeTime = takeTime;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	@Override
	public String toString() {
		return "Portion [id=" + id + ", unit=" + unit + ", size=" + size
				+ ", takeTime=" + takeTime + ", taken=" + taken
				+ "]";
	}
	
}
