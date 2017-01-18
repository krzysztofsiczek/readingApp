package readingProject;

import java.util.HashSet;
import java.util.Set;

public class Books {

	private int bookId, publicationYear;
	private double isbn;
	private String bookTitle, bookAuthor, genre;
	private Set<Interactions> interactions = new HashSet<Interactions>();

	public Books() {
	};

	public Books(int bookId, String bookTitle, String bookAuthor, double isbn, String genre,
			int publicationYear, Set<Interactions> interactions) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.isbn = isbn;
		this.genre = genre;
		this.publicationYear = publicationYear;
		this.interactions = interactions;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public double getIsbn() {
		return isbn;
	}

	public void setIsbn(double isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Set<Interactions> getInteractions() {
		return interactions;
	}

	public void setInteractions(Set<Interactions> interactions) {
		this.interactions = interactions;
	}
}
