package readingProject;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StoreBookData implements StoreData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Books books;
	private Books bookToBeAdded;
	private Integer bookId;

	public StoreBookData(Books bookToBeAdded) {
		this.bookToBeAdded = bookToBeAdded;
	}

	@Override
	public void save() {
		getSessionFactoryInstance();
		startSession();
		insertDataIntoDatabase();
		closeSession();
	}

	public Integer saveAndRetrieveId() {
		getSessionFactoryInstance();
		startSession();
		insertDataIntoDatabase();
		retrieveBookId();
		closeSession();
		return bookId;
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void insertDataIntoDatabase() {
		books = new Books();
		books.setBookTitle(bookToBeAdded.getBookTitle());
		books.setBookAuthor(bookToBeAdded.getBookAuthor());
		books.setGenre(bookToBeAdded.getGenre());
		books.setPublicationYear(bookToBeAdded.getPublicationYear());
		session.persist(books);
	}

	private void retrieveBookId() {
		String bookTitleToBeCompared = bookToBeAdded.getBookTitle();
		String checkingBookId = "SELECT B.bookId From Books B WHERE B.bookTitle= :bookTitle";
		Query<Integer> query = session.createQuery(checkingBookId);
		query.setParameter("bookTitle", bookTitleToBeCompared);
		List<Integer> result = query.getResultList();
		if (!(result.isEmpty())) {
			if (result.size() == 1){
				bookId = result.get(0);
			} else {
				bookId = result.get(result.size() - 1);
			}
		}
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
