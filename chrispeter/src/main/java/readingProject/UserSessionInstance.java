package readingProject;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserSessionInstance {

	private static Integer userId;
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;

	private UserSessionInstance() {
	}

	public static Integer getUserSessionInstance(String emailToBeComapred) {
		if (userId == null) {
			userId = createUserSessionInstance(emailToBeComapred);
		}
		return userId;
	}

	public static Integer getUserSessionInstance() {
		return userId;
	}
	
	private static Integer createUserSessionInstance(String emailToBeComapred) {
		getSessionFactoryInstance();
		startSession();
		checkUserId(emailToBeComapred);
		closeSession();
		return userId;

	}

	private static void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private static void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private static void checkUserId(String emailToBeComapred) {
		String checkingUserId = "SELECT U.userId From Users U WHERE U.email= :email";
		Query<Integer> query = session.createQuery(checkingUserId);
		query.setParameter("email", emailToBeComapred);
		List<Integer> result = query.getResultList();
		if (!(result.isEmpty())) {
			userId = result.get(0);
		}
	}

	private static void closeSession() {
		transaction.commit();
		session.close();
	}
}
