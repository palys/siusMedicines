package siusMedicines.service;

import java.util.List;

import siusMedicines.dao.PortionDao;
import siusMedicines.model.Portion;

public class PortionService {
	
	private static PortionDao portionDao;
	
	public PortionService() {
		portionDao = new PortionDao();
	}
	
	public void persist(Portion entity) {
		portionDao.openCurrentSessionWithTransaction();
		portionDao.persist(entity);
		portionDao.closeCurrentSessionWithTransaction();
	}
	
	public void update(Portion entity) {
		portionDao.openCurrentSessionWithTransaction();
		portionDao.update(entity);
		portionDao.closeCurrentSessionWithTransaction();
	}
	
	public Portion findById(Long id) {
		portionDao.openCurrentSession();
		Portion portion = portionDao.findById(id);
		portionDao.closeCurrentSession();
		return portion;
	}
	
	public void delete(Long id) {
		portionDao.openCurrentSessionWithTransaction();
		Portion portion = portionDao.findById(id);
		portionDao.delete(portion);
		portionDao.closeCurrentSessionWithTransaction();
	}
	
	public List<Portion> findAll() {
		portionDao.openCurrentSession();
		List<Portion> portions = portionDao.findAll();
		portionDao.closeCurrentSession();
		return portions;
	}
	
	public void deleteAll() {
		portionDao.openCurrentSessionWithTransaction();
		portionDao.deleteAll();
		portionDao.closeCurrentSessionWithTransaction();
	}
	
	public PortionDao portionDao() {
		return portionDao;
	}

}
