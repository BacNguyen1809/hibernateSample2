/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateConnectionUtil {
	private static SessionFactory sessionFactory = null;
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null ) {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			ServiceRegistry sRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(sRegistry);
		
		}
		return sessionFactory;
	}
	public static void closeSessionFactory(SessionFactory sessionFactory) {
		sessionFactory.close();
	}

}
