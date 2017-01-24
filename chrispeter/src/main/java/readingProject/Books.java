package readingProject;

import java.util.HashSet;
import java.util.Set;

public class Books {

	private Integer bookId, publicationYear;
	private String bookTitle, bookAuthor, genre;

	private Set<Interactions> interactions = new HashSet<Interactions>();

	public Books() {
	};

	public Books(String bookTitle, String bookAuthor, String genre, int publicationYear) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.genre = genre;
		this.publicationYear = publicationYear;
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

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Set<Interactions> getInteractions() {
		return interactions;
	}

	public void setInteractions(Set<Interactions> interactions) {
		this.interactions = interactions;
	}
	
	public void addInteractions(Interactions interactionToBeAdded) {
		this.interactions.add(interactionToBeAdded);	
	}	
}
