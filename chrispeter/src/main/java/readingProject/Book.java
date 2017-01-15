package readingProject;

public class Book {

	private int bookId, publicationYear;
	private double isbn;
	private String bookTitle, bookAuthor, genre;

	public Book() {
	};

	public Book(String bookTitle, String bookAuthor, double isbn, String genre, int publicationYear) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.isbn = isbn;
		this.genre = genre;
		this.publicationYear = publicationYear;
	}

	public Book(int bookId, String bookTitle, String bookAuthor, double isbn, String genre, int publicationYear) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.isbn = isbn;
		this.genre = genre;
		this.publicationYear = publicationYear;
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
}
