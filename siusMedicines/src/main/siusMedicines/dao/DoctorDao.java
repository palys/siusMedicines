package siusMedicines.dao;

import java.util.List;

import siusMedicines.model.Doctor;

public class DoctorDao extends AbstractDao implements DoctorDaoInterface {

	public DoctorDao() {
		
	}
	
	@Override
	public void persist(Doctor entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public void update(Doctor entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public Doctor findById(Long id) {
		Doctor doctor = (Doctor) getCurrentSession().get(Doctor.class, id);
		return doctor;
	}

	@Override
	public void delete(Doctor entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public List<Doctor> findAll() {
		@SuppressWarnings("unchecked")
		List<Doctor> doctors = (List<Doctor>) getCurrentSession().createQuery("from Doctor").list();
		return doctors;
	}

	@Override
	public void deleteAll() {
		List<Doctor> doctors = findAll();
		for(Doctor doctor : doctors) {
			delete(doctor);
		}
	}

}
