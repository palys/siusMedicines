package siusMedicines.service;

import java.util.List;

import siusMedicines.dao.PrescriptionDao;
import siusMedicines.model.Prescription;

public class PrescriptionService {
	
	private static PrescriptionDao prescriptionDao;
	
	public PrescriptionService() {
		prescriptionDao = new PrescriptionDao();
	}
	
	public void persist(Prescription entity) {
		prescriptionDao.openCurrentSessionWithTransaction();
		prescriptionDao.persist(entity);
		prescriptionDao.closeCurrentSessionWithTransaction();
	}
	
	public void update(Prescription entity) {
		prescriptionDao.openCurrentSessionWithTransaction();
		prescriptionDao.update(entity);
		prescriptionDao.closeCurrentSessionWithTransaction();
	}
	
	public Prescription findById(Long id) {
		prescriptionDao.openCurrentSession();
		Prescription prescription = prescriptionDao.findById(id);
		prescriptionDao.closeCurrentSession();
		return prescription;
	}
	
	public void delete(Long id) {
		prescriptionDao.openCurrentSessionWithTransaction();
		Prescription prescription = prescriptionDao.findById(id);
		prescriptionDao.delete(prescription);
		prescriptionDao.closeCurrentSessionWithTransaction();
	}
	
	public List<Prescription> findAll() {
		prescriptionDao.openCurrentSession();
		List<Prescription> prescriptions = prescriptionDao.findAll();
		prescriptionDao.closeCurrentSession();
		return prescriptions;
	}
	
	public void deleteAll() {
		prescriptionDao.openCurrentSessionWithTransaction();
		prescriptionDao.deleteAll();
		prescriptionDao.closeCurrentSessionWithTransaction();
	}
	
	public PrescriptionDao prescriptionDao() {
		return prescriptionDao;
	}

}
