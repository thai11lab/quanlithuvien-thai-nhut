package quanlithuvien.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import quanlithuvien.datasoucre.HibernateConfig;
import quanlithuvien.entity.BookReader;

public class BookReaderRepository {
	
	private static Session session;
	
	public List<BookReader> findByProductId(Long id) {
		// TODO Auto-generated method stub
		List<BookReader> bookReaders = new ArrayList<BookReader>();
		String sql = "SELECT br FROM BookReader br WHERE br.reader.id =:reader_id";
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(sql,BookReader.class);
			bookReaders = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return bookReaders;
	}

	public List<BookReader> findByReaderId(Long idUpdate) {
		// TODO Auto-generated method stub
		List<BookReader> bookReaders = new ArrayList<BookReader>();
		String sql = "SELECT br FROM BookReader br WHERE br.reader.id =:reader_id";
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(sql,BookReader.class);
			query.setParameter("reader_id",idUpdate);
			bookReaders = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return bookReaders;
	}
	
}
