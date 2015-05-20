package siusMedicines.controllers.doctor.patients.prescriptions;


public class PortionWithFrequencyHolder {

	private String unit;
	
	private String size;
	
	private int portionsNumber;
	
	private String firstPortionDate;
	
	private String patientId;
	
	private String prescriptionId;

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

	public int getPortionsNumber() {
		return portionsNumber;
	}

	public void setPortionsNumber(int portionsNumber) {
		this.portionsNumber = portionsNumber;
	}

	public String getFirstPortionDate() {
		return firstPortionDate;
	}

	public void setFirstPortionDate(String firstPortionDate) {
		this.firstPortionDate = firstPortionDate;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	@Override
	public String toString() {
		return "PortionWithFrequencyHolder [unit=" + unit + ", size=" + size
				+ ", portionsNumber=" + portionsNumber + ", firstPortionDate="
				+ firstPortionDate + ", patientId=" + patientId
				+ ", prescriptionId=" + prescriptionId + "]";
	}
	
	
}
