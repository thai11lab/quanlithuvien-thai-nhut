package quanlithuvien.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import quanlithuvien.datasoucre.HibernateConfig;
import quanlithuvien.entity.Book;
import quanlithuvien.entity.BookReader;

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
//		String sql = "Select br from BookReader as br inner join Book b on br.book.id=b.id WHERE 1 = 1 AND br.book.id= :book_id";
		String sql = "Select b from Book b WHERE b.id = :book_id";
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(sql,Book.class);
			query.setParameter("book_id", id);
			Book book = (Book) query.getSingleResult();
			session.delete(book);
			
//			session.delete();	
//			query1.setParameter("ID", id);
//			query1.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Rollback");
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}


	@SuppressWarnings("unused")
	public List<Book> findBySearch(String key) {
		List<Book> listBook= new ArrayList<Book>();
		String sql = "SELECT b FROM Book b";
		if (key != null && key !="") {
			sql+=" WHERE b.name LIKE :key1 OR b.code LIKE :key2 ";
		}
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery(sql,Book.class);
			if (key != null && key !="") {
				query.setParameter("key1", "%"+key+"%");
				query.setParameter("key2", "%"+key+"%");
			}
			
			listBook = query.getResultList();
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
