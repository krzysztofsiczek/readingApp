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
	private String userEmailToBeCompared;
	private String passwordToBeCompared;
	private boolean isUserLoginDataCorrect;

	public CheckUserLoginData(String userEmailToBeCompared, String passwordToBeCompared) {
		this.userEmailToBeCompared = userEmailToBeCompared;
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
		String checkingLoginData = "FROM Users WHERE email= :email AND password= :password";
		Query<User> query = session.createQuery(checkingLoginData);
		query.setParameter("email", userEmailToBeCompared);
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
