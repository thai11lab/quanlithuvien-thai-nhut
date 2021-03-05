package quanlithuvien.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import quanlithuvien.datasoucre.HibernateConfig;
import quanlithuvien.entity.Book;
import quanlithuvien.entity.BookReader;
import quanlithuvien.entity.Reader;

public class ReaderRepository {

	private static Session session;
	
	
	private BookReaderRepository bookReaderRepository;
	public ReaderRepository() {
		bookReaderRepository = new BookReaderRepository();
	}
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
			Reader readerUpdate = session.find(Reader.class, idUpdate);
			readerUpdate.setAddress(reader.getAddress());
			readerUpdate.setAge(reader.getAge());
			readerUpdate.setCode(reader.getCode());
//			readerUpdate.setCreatedDate((java.sql.Date) new Date());
			readerUpdate.setName(reader.getName());
			List<BookReader> bookReaderOld = bookReaderRepository.findByReaderId(idUpdate);
			
			if (productIdL != null && productIdL.size() > 0) {
				for (BookReader bookReader : bookReaderOld) {
					session.delete(bookReader);
				}
				
				for (Long itemIdPr : productIdL) {
					book = session.find(Book.class, itemIdPr);
					book.setTotalBook(book.getTotalBook()-1);
					BookReader bookReader = new BookReader();
					
					bookReader.setBook(book);
					bookReader.setReader(readerUpdate);
					session.save(bookReader);
				}
				
			}
		
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByReaderDetail(Long idReader) {
		// TODO Auto-generated method stub
		List<Object[]> listObjects = new ArrayList<Object[]>();
		String sql = "SELECT r,b FROM Reader r inner join BookReader br on r.id = br.reader.id inner join Book b on b.id = br.book.id WHERE r.id= :reader_id";
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("reader_id",idReader);
			listObjects = query.getResultList();			
			session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		return listObjects;
	}
	public void deleteById(Long id1) {
		// TODO Auto-generated method stub
		String sql = "Select b from Reader b WHERE b.id = :reader_id";
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(sql, Reader.class);
			query.setParameter("reader_id", id1);
			Reader reader = (Reader) query.getSingleResult();
			session.delete(reader);

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
	
	
}
