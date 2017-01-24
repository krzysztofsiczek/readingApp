package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UpdateBookData implements UpdateData{

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Books bookToBeUpdated;
	private Integer bookId, newPublicationYear;
	private String newBookTitle, newBookAuthor, newGenre;

	public UpdateBookData(Integer bookId, String newBookTitle, String newBookAuthor, String newGenre,
			Integer newPublicationYear) {
		this.bookId = bookId;
		this.newBookTitle = newBookTitle;
		this.newBookAuthor = newBookAuthor;
		this.newGenre = newGenre;
		this.newPublicationYear = newPublicationYear;
	}

	public void updateData() {
		getSessionFactoryInstance();
		startSession();
		updateDataInDatabase();
		closeSession();
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void updateDataInDatabase() {
		bookToBeUpdated = (Books) session.get(Books.class, bookId);
		bookToBeUpdated.setBookTitle(newBookTitle);
		bookToBeUpdated.setBookAuthor(newBookAuthor);
		bookToBeUpdated.setGenre(newGenre);
		bookToBeUpdated.setPublicationYear(newPublicationYear);		
		session.update(bookToBeUpdated);
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
