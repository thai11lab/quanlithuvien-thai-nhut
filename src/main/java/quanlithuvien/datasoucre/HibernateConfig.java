package quanlithuvien.datasoucre;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import quanlithuvien.entity.Book;
import quanlithuvien.entity.BookReader;
import quanlithuvien.entity.Category;
import quanlithuvien.entity.Reader;

public class HibernateConfig {
	public static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate-config.xml");
		configuration.addAnnotatedClass(Book.class);
		configuration.addAnnotatedClass(Category.class);
		configuration.addAnnotatedClass(Reader.class);
		configuration.addAnnotatedClass(BookReader.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}
