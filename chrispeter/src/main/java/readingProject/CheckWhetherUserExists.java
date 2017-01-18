package readingProject;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CheckWhetherUserExists implements CheckData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private String emailToBeAdded;
	private boolean doesUserExist;

	public CheckWhetherUserExists(String emailToBeAdded) {
		this.emailToBeAdded = emailToBeAdded;
	}

	@Override
	public boolean check() {
		getSessionFactoryInstance();
		startSession();
		checkUserLoginData();
		closeSession();
		return doesUserExist;
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void checkUserLoginData() {
		String checkingWhetherUserExists = "FROM Users WHERE email= :email";
		Query<User> query = session.createQuery(checkingWhetherUserExists);
		query.setParameter("email", emailToBeAdded);
		List<User> result = query.getResultList();
		boolean wynik = result.isEmpty();
		if (wynik != true) {
			doesUserExist = true;
		}
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
