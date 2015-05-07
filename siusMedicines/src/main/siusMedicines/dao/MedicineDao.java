package siusMedicines.dao;

import java.util.List;

import siusMedicines.model.Medicine;

public class MedicineDao extends AbstractDao implements MedicineDaoInterface {	
	
	public MedicineDao() {
		
	}

	@Override
	public void persist(Medicine entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public void update(Medicine entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public Medicine findById(String id) {
		Medicine medicine = (Medicine) getCurrentSession().get(Medicine.class, id);
		return medicine;
	}

	@Override
	public void delete(Medicine entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public List<Medicine> findAll() {
		@SuppressWarnings("unchecked")
		List<Medicine> medicines = (List<Medicine>) getCurrentSession().createQuery("from medicines").list();
		return medicines;
	}

	@Override
	public void deleteAll() {
		List<Medicine> medicines = findAll();
		for (Medicine medicine : medicines) {
			delete(medicine);
		}
	}

}
