package siusMedicines.dao;

import java.util.List;

import siusMedicines.model.User;

public class UserDao extends AbstractDao implements UserDaoInterface {

	public UserDao() {
		
	}
	
	@Override
	public void persist(User entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public void update(User entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public User findById(String id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}

	@Override
	public void delete(User entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public List<User> findAll() {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
		return users;
	}

	@Override
	public void deleteAll() {
		List<User> users = findAll();
		for (User user : users) {
			delete(user);
		}
	}

}
