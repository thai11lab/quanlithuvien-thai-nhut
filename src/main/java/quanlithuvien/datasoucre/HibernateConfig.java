package quanlithuvien.datasoucre;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import quanlithuvien.entity.Book;
import quanlithuvien.entity.Category;
import quanlithuvien.entity.Role;
import quanlithuvien.entity.User;

public class HibernateConfig {
	public static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate-config.xml");
		configuration.addAnnotatedClass(Book.class);
		configuration.addAnnotatedClass(Category.class);
		configuration.addAnnotatedClass(Role.class);
		configuration.addAnnotatedClass(User.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}
