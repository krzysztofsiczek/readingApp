package readingProject;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CheckUserLoginData implements CheckData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private String userNameToBeCompared;
	private String passwordToBeCompared;
	private boolean isUserLoginDataCorrect;

	public CheckUserLoginData(String userNameToBeCompared, String passwordToBeCompared) {
		this.userNameToBeCompared = userNameToBeCompared;
		this.passwordToBeCompared = passwordToBeCompared;
	}

	@Override
	public boolean check() {
		getSessionFactoryInstance();
		startSession();
		checkUserLoginData();
		closeSession();
		return isUserLoginDataCorrect;
	}
	
	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void checkUserLoginData() {
		String checkingLoginData = "FROM Users WHERE userName= :userName AND password= :password";
		Query<User> query = session.createQuery(checkingLoginData);
		query.setParameter("userName", userNameToBeCompared);
		query.setParameter("password", passwordToBeCompared);
		List<User> result = query.getResultList();
		boolean wynik = result.isEmpty();
		if (wynik != true) {
			isUserLoginDataCorrect = true;
		}
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
