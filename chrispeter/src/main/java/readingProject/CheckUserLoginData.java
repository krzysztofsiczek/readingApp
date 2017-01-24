package readingProject;

import java.util.Iterator;
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
	private Integer userId;

	public CheckUserLoginData(String userEmailToBeCompared, String passwordToBeCompared) {
		this.userEmailToBeCompared = userEmailToBeCompared;
		this.passwordToBeCompared = passwordToBeCompared;
	}

	@Override
	public Integer check() {
		getSessionFactoryInstance();
		startSession();
		checkUserLoginData();
		closeSession();
		return userId;
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
		Query<Users> query = session.createQuery(checkingLoginData);
		query.setParameter("email", userEmailToBeCompared);
		query.setParameter("password", passwordToBeCompared);
		List<Users> result = query.getResultList();

		if (!(result.isEmpty())) {
			Iterator<Users> iterator = result.iterator();
			Users user = iterator.next();
			userId = user.getUserId();
		}
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
