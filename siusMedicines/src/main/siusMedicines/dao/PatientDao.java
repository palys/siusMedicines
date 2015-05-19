package siusMedicines.dao;

import java.util.List;

import siusMedicines.model.Patient;

public class PatientDao extends AbstractDao implements PatientDaoInterface {

	public PatientDao() {
		
	}
	
	@Override
	public void persist(Patient entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public void update(Patient entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public Patient findById(Long id) {
		Patient patient = (Patient) getCurrentSession().get(Patient.class, id);
		return patient;
	}

	@Override
	public void delete(Patient entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public List<Patient> findAll() {
		@SuppressWarnings("unchecked")
		List<Patient> patients = (List<Patient>) getCurrentSession().createQuery("from Patient").list();
		return patients;
	}

	@Override
	public void deleteAll() {
		List<Patient> patients = findAll();
		for(Patient patient : patients) {
			delete(patient);
		}
	}

}
