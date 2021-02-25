package quanlithuvien.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import quanlithuvien.datasoucre.HibernateConfig;
import quanlithuvien.entity.Reader;

public class ReaderRepository {
	
	private static Session session;

	public List<Reader> findAll() {
		List<Reader> listRD = new ArrayList<Reader>();
		try {
			session = HibernateConfig.buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("SELECT *FROM Reader");
//			List<Reader> listRD = query.getResultList();
			
			session.beginTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		
		return null;
	}
	

}
