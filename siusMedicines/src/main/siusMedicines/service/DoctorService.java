package siusMedicines.service;

import java.util.List;

import siusMedicines.dao.DoctorDao;
import siusMedicines.model.Doctor;

public class DoctorService {
	
	private static DoctorDao doctorDao;
	
	public DoctorService() {
		doctorDao = new DoctorDao();
	}
	
	public void persist(Doctor entity) {
		doctorDao.openCurrentSessionWithTransaction();
		doctorDao.persist(entity);
		doctorDao.closeCurrentSessionWithTransaction();
	}
	
	public void update(Doctor entity) {
		doctorDao.openCurrentSessionWithTransaction();
		doctorDao.update(entity);
		doctorDao.closeCurrentSessionWithTransaction();
	}
	
	public Doctor findById(Long id) {
		doctorDao.openCurrentSession();
		Doctor doctor = doctorDao.findById(id);
		doctorDao.closeCurrentSession();
		return doctor;
	}
	
	public void delete(Long id) {
		doctorDao.openCurrentSessionWithTransaction();
		Doctor doctor = doctorDao.findById(id);
		doctorDao.delete(doctor);
		doctorDao.closeCurrentSessionWithTransaction();
	}
	
	public List<Doctor> findAll() {
		doctorDao.openCurrentSession();
		List<Doctor> doctors = doctorDao.findAll();
		doctorDao.closeCurrentSession();
		return doctors;
	}
	
	public void deleteAll() {
		doctorDao.openCurrentSessionWithTransaction();
		doctorDao.deleteAll();
		doctorDao.closeCurrentSessionWithTransaction();
	}
	
	public DoctorDao doctorDao() {
		return doctorDao;
	}

}
