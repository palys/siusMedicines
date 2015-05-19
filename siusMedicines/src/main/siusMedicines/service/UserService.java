package siusMedicines.service;

import java.util.List;

import siusMedicines.dao.UserDao;
import siusMedicines.model.User;

public class UserService {

	private static UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public void persist(User entity) {
		userDao.openCurrentSessionWithTransaction();
		userDao.persist(entity);
		userDao.closeCurrentSessionWithTransaction();
	}
	
	public void update(User entity) {
		userDao.openCurrentSessionWithTransaction();
		userDao.update(entity);
		userDao.closeCurrentSessionWithTransaction();
	}
	
	public User findById(String id) {
		userDao.openCurrentSession();
		User user = userDao.findById(id);
		userDao.closeCurrentSession();
		return user;
	}
	
	public void delete(String id) {
		userDao.openCurrentSessionWithTransaction();
		User user = userDao.findById(id);
		userDao.delete(user);
		userDao.closeCurrentSessionWithTransaction();
	}
	
	public List<User> findAll() {
		userDao.openCurrentSession();
		List<User> users = userDao.findAll();
		userDao.closeCurrentSession();
		return users;
	}
	
	public void deleteAll() {
		userDao.openCurrentSessionWithTransaction();
		userDao.deleteAll();
		userDao.closeCurrentSessionWithTransaction();
	}
	
	public UserDao medicineDao() {
		return userDao;
	}
}
