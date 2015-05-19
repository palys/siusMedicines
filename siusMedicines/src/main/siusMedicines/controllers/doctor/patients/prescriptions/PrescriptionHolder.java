package siusMedicines.controllers.doctor.patients.prescriptions;

import siusMedicines.model.Prescription;

public class PrescriptionHolder {

	private Prescription prescription;
	
	private int totalPortionsNumber;
	
	private int portionsLeft;
	
	private String nextPortion;

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public int getTotalPortionsNumber() {
		return totalPortionsNumber;
	}

	public void setTotalPortionsNumber(int totalPortionsNumber) {
		this.totalPortionsNumber = totalPortionsNumber;
	}

	public int getPortionsLeft() {
		return portionsLeft;
	}

	public void setPortionsLeft(int portionsLeft) {
		this.portionsLeft = portionsLeft;
	}

	public String getNextPortion() {
		return nextPortion;
	}

	public void setNextPortion(String nextPortion) {
		this.nextPortion = nextPortion;
	}
	
	
}
