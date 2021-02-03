package quanlithuvien.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import quanlithuvien.datasoucre.HibernateConfig;
import quanlithuvien.entity.Book;

public class BookRepository {
	
	private static Session session;
	
	
	public List<Book> findAll() {
		List<Book> listBook= new ArrayList<Book>();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			listBook = session.createQuery("SELECT b FROM Book b",Book.class).getResultList();
			
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
		return listBook;
	}
	
	
	public void save(Book entity) {
		try {
			session = HibernateConfig.buildSessionFactory().openSession();	
			session.beginTransaction();
			session.save(entity);
			
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
	
	public void update(Book book,Long id) {
	
		try {
			session = HibernateConfig.buildSessionFactory().openSession();	
			Book bookEdit = session.find(Book.class,id);
			session.beginTransaction();
			bookEdit.setCategory(book.getCategory());
			bookEdit.setCode(book.getCode());
			bookEdit.setCompany(book.getCompany());
			bookEdit.setCreatedBy(book.getCreatedBy());
			bookEdit.setName(book.getName());
			bookEdit.setTotalBook(book.getTotalBook());
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


	public Book findById(Long id) {
		// TODO Auto-generated method stub
		Book book = new Book();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			book=session.find(Book.class,id);
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
		return book;
	}


	public void deleteById(Long id) {
		// TODO Auto-generated method stub
//		Book book = new Book();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("delete Book where id = :ID");
			query.setParameter("ID", id);
			query.executeUpdate();
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


	public List<Book> findBySearch(String key) {
		List<Book> listBook= new ArrayList<Book>();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			listBook = session.createQuery("SELECT b FROM Book b WHERE b.name LIKE '%"+key+"%' "
					+ "OR b.company LIKE '%"+key+"%'"
					+ " OR b.code LIKE '%"+key+"%'",Book.class).getResultList();			
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
		return listBook;
	}
	
	
	
}
