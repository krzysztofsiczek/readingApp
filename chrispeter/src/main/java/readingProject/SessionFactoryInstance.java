package readingProject;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {

	private static SessionFactory sessionFactory;

	private SessionFactoryInstance() {
	}

	public static SessionFactory getSessionFactoryInstance() {
		if (sessionFactory == null) {
			sessionFactory = createSessionFactory();
		}
		return sessionFactory;
	}

	private static SessionFactory createSessionFactory() {
		JOptionPane.showMessageDialog(null, "We will connect to database to carry out your request. Please, kindly wait for our confirmation of the end of this process.", "Connecting to database",
				JOptionPane.PLAIN_MESSAGE);
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		return configuration.buildSessionFactory();
	}
}
