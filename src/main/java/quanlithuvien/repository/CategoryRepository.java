package quanlithuvien.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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
			session.beginTransaction();
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

	public void save(Category category) {
		// TODO Auto-generated method stub
		try {
			session = HibernateConfig.buildSessionFactory().openSession();	
			session.beginTransaction();
			session.save(category);
			
			session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Rollback");
			session.getTransaction().rollback();
		}finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void update(Category category,Long idUpdate) {
		// TODO Auto-generated method stub
		try {
			session = HibernateConfig.buildSessionFactory().openSession();	
			Category categoryEdit = session.find(Category.class,idUpdate);
			session.beginTransaction();
			categoryEdit.setCode(category.getCode());
			categoryEdit.setName(category.getName());			
			session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			
		}finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Category> findBySearch(String key) {
		List<Category> liCategories= new ArrayList<Category>();
		String sql = "SELECT b FROM Category b";
		if (key != null && key !="") {
			sql+=" WHERE b.name LIKE :key1 OR b.code LIKE :key2 ";
		}
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery(sql,Category.class);
			if (key != null && key !="") {
				query.setParameter("key1", "%"+key+"%");
				query.setParameter("key2", "%"+key+"%");
			}
			
			liCategories = query.getResultList();
			session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Rollback");
			session.getTransaction().rollback();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return liCategories;
	}

	public void deleteById(Long id1) {
		// TODO Auto-generated method stub
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("Select c FROM Category c where c.id = :ID",Category.class);
			query.setParameter("ID", id1);
			Category category = (Category) query.getSingleResult();
			session.delete(category);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			System.out.println("Rollback");
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Category> findByExistName(Category category) {
		List<Category> listCategory = new ArrayList<Category>();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("SELECT c FROM Category c WHERE c.name =:name", Category.class);
			query.setParameter("name", category.getName());
			listCategory = query.getResultList();
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

	public List<Category> findByExistCode(Category category) {
		List<Category> listCategory = new ArrayList<Category>();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("SELECT c FROM Category c WHERE c.code =:code", Category.class);
			query.setParameter("code", category.getCode());
			listCategory = query.getResultList();
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
}
