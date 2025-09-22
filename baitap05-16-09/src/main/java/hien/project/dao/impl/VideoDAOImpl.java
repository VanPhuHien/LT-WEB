package hien.project.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import hien.project.config.JPAConfig;
import hien.project.dao.IVideoDAO;
import hien.project.entity.Video;

import java.util.List;

public class VideoDAOImpl implements IVideoDAO {

	@Override
	public Video create(Video video) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(video);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return video;
	}

	@Override
	public Video update(Video video) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(video);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return video;
	}

	@Override
	public Video remove(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		Video video = findById(id);
		try {
			em.getTransaction().begin();
			em.remove(em.merge(video));
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return video;
	}

	@Override
	public Video findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<Video> query = em.createNamedQuery("Video.findById", Video.class);
		query.setParameter("id", id);
		Video video = query.getSingleResult();
		em.close();
		return video;
	}

	@Override
	public List<Video> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		List<Video> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Video> findByKeyword(String keyword) {
		EntityManager em = JPAConfig.getEntityManager();
		TypedQuery<Video> query = em.createNamedQuery("Video.findByKeyword", Video.class);
		query.setParameter("keyword", "%" + keyword + "%");
		List<Video> list = query.getResultList();
		em.close();
		return list;
	}
}