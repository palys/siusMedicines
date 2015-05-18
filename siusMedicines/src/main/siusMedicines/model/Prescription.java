package siusMedicines.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription {

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "patient_id")
	private String patientId;
	
	@Column(name = "first_portion_time")
	private Date firstPortionTime;
	
	@Column(name = "portion_unit")
	private String portionUnit;
	
	@Column(name = "portion_size")
	private double portionSize;
	
	@Column(name = "portions_per_day")
	private int portionsPerDay;
	
	@Column(name = "minutes_between_portions")
	private int minutesBetweenPortions;
	
	@Column(name = "portions_to_take")
	private int portionsToTake;
	
	public Prescription() {
		
	}

	public Prescription(String id, String patientId, Date firstPortionTime,
			String portionUnit, double portionSize, int portionsPerDay,
			int minutesBetweenPortions, int portionsToTake) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.firstPortionTime = firstPortionTime;
		this.portionUnit = portionUnit;
		this.portionSize = portionSize;
		this.portionsPerDay = portionsPerDay;
		this.minutesBetweenPortions = minutesBetweenPortions;
		this.portionsToTake = portionsToTake;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getFirstPortionTime() {
		return firstPortionTime;
	}

	public void setFirstPortionTime(Date firstPortionTime) {
		this.firstPortionTime = firstPortionTime;
	}

	public String getPortionUnit() {
		return portionUnit;
	}

	public void setPortionUnit(String portionUnit) {
		this.portionUnit = portionUnit;
	}

	public double getPortionSize() {
		return portionSize;
	}

	public void setPortionSize(double portionSize) {
		this.portionSize = portionSize;
	}

	public int getPortionsPerDay() {
		return portionsPerDay;
	}

	public void setPortionsPerDay(int portionsPerDay) {
		this.portionsPerDay = portionsPerDay;
	}

	public int getMinutesBetweenPortions() {
		return minutesBetweenPortions;
	}

	public void setMinutesBetweenPortions(int minutesBetweenPortions) {
		this.minutesBetweenPortions = minutesBetweenPortions;
	}

	public int getPortionsToTake() {
		return portionsToTake;
	}

	public void setPortionsToTake(int portionsToTake) {
		this.portionsToTake = portionsToTake;
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", patientId=" + patientId
				+ ", firstPortionTime=" + firstPortionTime + ", portionUnit="
				+ portionUnit + ", portionSize=" + portionSize
				+ ", portionsPerDay=" + portionsPerDay
				+ ", minutesBetweenPortions=" + minutesBetweenPortions
				+ ", portionsToTake=" + portionsToTake + "]";
	}
	
	
}
