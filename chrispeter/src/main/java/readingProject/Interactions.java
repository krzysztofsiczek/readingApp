package readingProject;

public class Interactions {

	private BooksUsersId booksUsersId = new BooksUsersId();
	
	private boolean hasRead, hasGot, wantsToBuy;

	protected Interactions() {
	}

	protected BooksUsersId getId() {
		return booksUsersId;
	}

	protected void setId(BooksUsersId booksUsersId) {
		this.booksUsersId = booksUsersId;
	}

	protected Books getBooks() {
		return getId().getBooks();
	}

	protected void setBooks(Books bookId) {
		getId().setBooks(bookId);
	}

	protected Users getUsers() {
		return getId().getUsers();
	}

	protected void setUsers(Users users) {
		getId().setUsers(users);
	}

	protected boolean getHasRead() {
		return hasRead;
	}

	protected void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
	}

	protected boolean getHasGot() {
		return hasGot;
	}

	protected void setHasGot(boolean hasGot) {
		this.hasGot = hasGot;
	}

	protected boolean getWantsToBuy() {
		return wantsToBuy;
	}

	protected void setWantsToBuy(boolean wantsToBuy) {
		this.wantsToBuy = wantsToBuy;
	}

}