package quanlithuvien.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import quanlithuvien.datasoucre.HibernateConfig;
import quanlithuvien.entity.Book;
import quanlithuvien.entity.BookReader;
import quanlithuvien.entity.Reader;

public class ReaderRepository {

	private static Session session;

	public List<Reader> findAll() {
		List<Reader> listRD = new ArrayList<Reader>();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("SELECT r FROM Reader r");
			listRD = query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return listRD;
	}

	public void save(Reader reader, List<Long> productIdL) {
		// TODO Auto-generated method stub

		
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();

			session.save(reader);
			if (productIdL != null && productIdL.size() > 0) {
				for (Long itemIdPr : productIdL) {
					Book book = session.find(Book.class, itemIdPr);
					book.setTotalBook(book.getTotalBook()-1);
					BookReader bookReader = new BookReader();
					bookReader.setBook(book);
					bookReader.setReader(reader);
					session.save(bookReader);
				}
				
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public void update(Reader reader, Long idUpdate, List<Long> productIdL) {
		// TODO Auto-generated method stub
		Book book = new Book();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(reader);
			if (productIdL != null && productIdL.size() > 0) {
				for (Long itemIdPr : productIdL) {
					book = session.find(Book.class, itemIdPr);
					BookReader bookReader = new BookReader();
					bookReader.setBook(book);
					bookReader.setReader(reader);
					session.save(bookReader);
				}
				book.setTotalBook(book.getTotalBook()-1);
			}
		
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public Reader findById(Long id) {
		Reader reader = new Reader();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			reader=session.find(Reader.class,id);
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
		return reader;
	}

}
