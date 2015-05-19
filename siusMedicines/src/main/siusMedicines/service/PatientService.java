package siusMedicines.service;

import java.util.List;

import siusMedicines.dao.PatientDao;
import siusMedicines.model.Patient;

public class PatientService {
	
	private static PatientDao patientDao;
	
	public PatientService() {
		patientDao = new PatientDao();
	}
	
	public void persist(Patient entity) {
		patientDao.openCurrentSessionWithTransaction();
		patientDao.persist(entity);
		patientDao.closeCurrentSessionWithTransaction();
	}
	
	public void update(Patient entity) {
		patientDao.openCurrentSessionWithTransaction();
		patientDao.update(entity);
		patientDao.closeCurrentSessionWithTransaction();
	}
	
	public Patient findById(Long id) {
		patientDao.openCurrentSession();
		Patient patient = patientDao.findById(id);
		patientDao.closeCurrentSession();
		return patient;
	}
	
	public void delete(Long id) {
		patientDao.openCurrentSessionWithTransaction();
		Patient patient = patientDao.findById(id);
		patientDao.delete(patient);
		patientDao.closeCurrentSessionWithTransaction();
	}
	
	public List<Patient> findAll() {
		patientDao.openCurrentSession();
		List<Patient> patients = patientDao.findAll();
		patientDao.closeCurrentSession();
		return patients;
	}
	
	public void deleteAll() {
		patientDao.openCurrentSessionWithTransaction();
		patientDao.deleteAll();
		patientDao.closeCurrentSessionWithTransaction();
	}
	
	public PatientDao patientDao() {
		return patientDao;
	}

}
