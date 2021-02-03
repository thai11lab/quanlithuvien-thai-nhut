package quanlithuvien.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import quanlithuvien.datasoucre.HibernateConfig;
import quanlithuvien.entity.Book;
import quanlithuvien.entity.Category;

public class CategoryRepository {

	private static Session session;

	public List<Category> findAll() {
		List<Category> listCategory = new ArrayList<Category>();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();

			listCategory = session.createQuery("SELECT c FROM Category c", Category.class).getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Rollback");
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listCategory;
	}

	public void save(Book entity) {
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Rollback");
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Category findById(Long id) {
		Category category = new Category();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			category=session.find(Category.class,id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Rollback");
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return category;
	}
}
