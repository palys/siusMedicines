package siusMedicines.service;

import java.util.List;

import siusMedicines.dao.MedicineDao;
import siusMedicines.model.Medicine;

public class MedicineService {
	
	private static MedicineDao medicineDao;
	
	public MedicineService() {
		medicineDao = new MedicineDao();
	}
	
	public void persist(Medicine entity) {
		medicineDao.openCurrentSessionWithTransaction();
		medicineDao.persist(entity);
		medicineDao.closeCurrentSessionWithTransaction();
	}
	
	public void update(Medicine entity) {
		medicineDao.openCurrentSessionWithTransaction();
		medicineDao.update(entity);
		medicineDao.closeCurrentSessionWithTransaction();
	}
	
	public Medicine findById(String id) {
		medicineDao.openCurrentSession();
		Medicine medicine = medicineDao.findById(id);
		medicineDao.closeCurrentSession();
		return medicine;
	}
	
	public void delete(String id) {
		medicineDao.openCurrentSessionWithTransaction();
		Medicine medicine = medicineDao.findById(id);
		medicineDao.delete(medicine);
		medicineDao.closeCurrentSessionWithTransaction();
	}
	
	public List<Medicine> findAll() {
		medicineDao.openCurrentSession();
		List<Medicine> medicines = medicineDao.findAll();
		medicineDao.closeCurrentSession();
		return medicines;
	}
	
	public void deleteAll() {
		medicineDao.openCurrentSessionWithTransaction();
		medicineDao.deleteAll();
		medicineDao.closeCurrentSessionWithTransaction();
	}
	
	public MedicineDao medicineDao() {
		return medicineDao;
	}

}
