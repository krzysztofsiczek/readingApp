package readingProject;

import java.util.Iterator;
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
	private Integer userId;

	public CheckWhetherUserExists(String emailToBeAdded) {
		this.emailToBeAdded = emailToBeAdded;
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

	@SuppressWarnings("unchecked")
	private void checkUserLoginData() {
		String checkingWhetherUserExists = "FROM Users WHERE email= :email";
		Query<Users> query = session.createQuery(checkingWhetherUserExists);
		query.setParameter("email", emailToBeAdded);
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
