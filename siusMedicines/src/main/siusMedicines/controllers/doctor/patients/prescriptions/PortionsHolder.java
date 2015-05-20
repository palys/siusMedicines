package siusMedicines.controllers.doctor.patients.prescriptions;

import java.sql.Timestamp;

public class PortionsHolder implements Comparable<PortionsHolder> {

	private Long id;
	
	private String unit;
	
	private String size;
	
	private Timestamp takeTime;
	
	private boolean taken;
	
	private boolean shouldBeTaken;
	
	private boolean showWarning;

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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
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

	public boolean isShouldBeTaken() {
		return shouldBeTaken;
	}

	public void setShouldBeTaken(boolean shouldBeTaken) {
		this.shouldBeTaken = shouldBeTaken;
	}

	public boolean isShowWarning() {
		return showWarning;
	}

	public void setShowWarning(boolean showWarning) {
		this.showWarning = showWarning;
	}

	@Override
	public int compareTo(PortionsHolder o) {
		return this.takeTime.compareTo(o.takeTime);
	}
	
	
}
