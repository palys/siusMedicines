package siusMedicines.dao;

import java.util.List;

import siusMedicines.model.Portion;

public class PortionDao extends AbstractDao implements PortionDaoInterface {

	public PortionDao() {
		
	}
	
	@Override
	public void persist(Portion entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public void update(Portion entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public Portion findById(Long id) {
		Portion portion = (Portion) getCurrentSession().get(Portion.class, id);
		return portion;
	}

	@Override
	public void delete(Portion entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public List<Portion> findAll() {
		@SuppressWarnings("unchecked")
		List<Portion> portions = (List<Portion>) getCurrentSession().createQuery("from Portion").list();
		return portions;
	}

	@Override
	public void deleteAll() {
		List<Portion> portions = findAll();
		for(Portion portion : portions) {
			delete(portion);
		}
	}

}
