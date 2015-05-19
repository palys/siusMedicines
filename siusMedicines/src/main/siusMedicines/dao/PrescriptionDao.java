package siusMedicines.dao;

import java.util.List;

import siusMedicines.model.Prescription;

public class PrescriptionDao extends AbstractDao implements PrescriptionDaoInterface {

	public PrescriptionDao() {
		
	}
	
	@Override
	public void persist(Prescription entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public void update(Prescription entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public Prescription findById(Long id) {
		Prescription prescription = (Prescription) getCurrentSession().get(Prescription.class, id);
		return prescription;
	}

	@Override
	public void delete(Prescription entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public List<Prescription> findAll() {
		@SuppressWarnings("unchecked")
		List<Prescription> prescriptions = (List<Prescription>) getCurrentSession().createQuery("from Prescription").list();
		return prescriptions;
	}

	@Override
	public void deleteAll() {
		List<Prescription> prescriptions = findAll();
		for(Prescription prescription : prescriptions) {
			delete(prescription);
		}
	}

}
