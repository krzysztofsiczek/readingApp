package readingProject;

import java.util.HashSet;
import java.util.Set;

public class Books {

	private Integer bookId;
	private int publicationYear;
	private String bookTitle, bookAuthor, genre;
	private Set<Interactions> interactions = new HashSet<Interactions>();

	public Books() {
	};

	public Books(Integer bookId, String bookTitle, String bookAuthor, String genre, int publicationYear,
			Set<Interactions> interactions) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.genre = genre;
		this.publicationYear = publicationYear;
		this.interactions = interactions;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
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
