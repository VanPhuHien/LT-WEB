package hien.project.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import hien.project.config.JPAConfig;
import hien.project.dao.ICategoryDAO;
import hien.project.entity.Category;

import java.util.List;

public class CategoryDAOImpl implements ICategoryDAO {

	@Override
	public Category create(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(category);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return category;
	}

	@Override
	public Category update(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(category);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return category;
	}

	@Override
	public Category remove(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		Category category = findById(id);
		try {
			em.getTransaction().begin();
			em.remove(em.merge(category));
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return category;
	}

	@Override
	public Category findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<Category> query = em.createNamedQuery("Category.findById", Category.class);
		query.setParameter("id", id);
		Category category = query.getSingleResult();
		em.close();
		return category;
	}

	@Override
	public List<Category> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<Category> query = em.createNamedQuery("Category.findAll", Category.class);
		List<Category> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Category> findByKeyword(String keyword) {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<Category> query = em.createNamedQuery("Category.findByKeyword", Category.class);
		query.setParameter("keyword", "%" + keyword + "%");
		List<Category> list = query.getResultList();
		em.close();
		return list;
	}
}