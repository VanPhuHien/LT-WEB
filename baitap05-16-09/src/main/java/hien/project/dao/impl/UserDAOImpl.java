package hien.project.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import hien.project.config.JPAConfig;
import hien.project.dao.IUserDAO;
import hien.project.entity.User;

import java.util.List;

public class UserDAOImpl implements IUserDAO {

	@Override
	public User create(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return user;
	}

	@Override
	public User update(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return user;
	}

	@Override
	public User remove(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		User user = findById(id);
		try {
			em.getTransaction().begin();
			em.remove(em.merge(user));
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return user;
	}

	@Override
	public User findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
		query.setParameter("id", id);
		User user = query.getSingleResult();
		em.close();
		return user;
	}

	@Override
	public List<User> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		List<User> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<User> findByKeyword(String keyword) {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findByKeyword", User.class);
		query.setParameter("keyword", "%" + keyword + "%");
		List<User> list = query.getResultList();
		em.close();
		return list;
	}
}