package readingProject;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserInstance {

	private static Integer userId;
	private static Users users;
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;

	private UserInstance() {
	}

	public static Users getUserInstance() {
		if (users == null) {
			users = getCurrentUsers(userId);
		}
		return users;
	}

	public static Integer getUserId() {
		return userId;
	}

	public static void setUserId(Integer userId) {
		UserInstance.userId = userId;
	}

	private static Users getCurrentUsers(Integer userId) {
		getSessionFactoryInstance();
		startSession();
		getUsers(userId);
		closeSession();
		return users;
	}

	private static void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private static void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	private static void getUsers(Integer userId) {
		String checkingUsers = "From Users U WHERE U.userId= :userId";
		Query<Users> query = session.createQuery(checkingUsers);
		query.setParameter("userId", userId);
		List<Users> result = query.getResultList();
		if (!(result.isEmpty())) {
			Iterator<Users> iterator = result.iterator();
			users = iterator.next();
		}
	}

	private static void closeSession() {
		transaction.commit();
		session.close();
	}
}
