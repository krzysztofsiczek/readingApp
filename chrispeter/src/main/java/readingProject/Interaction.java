package readingProject;

public class Interaction {

	private boolean hasRead, hasGot, wantsToBuy;
	private BooksUsersId booksUsersId = new BooksUsersId();
	
	public Interaction() {
	}

	public BooksUsersId getId() {
		return booksUsersId;
	}

	public void setId(BooksUsersId booksUsersId) {
		this.booksUsersId = booksUsersId;
	}
	
	public Books getBooks() {
		return getId().getBooks();
	}

	public void setBooks(Books bookId) {
		getId().setBooks(bookId);
	}

	public Users getUsers() {
		return getId().getUsers();
	}

	public void setUsers(Users users) {
		getId().setUsers(users);
	}

	public boolean getHasRead() {
		return hasRead;
	}

	public void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
	}

	public boolean getHasGot() {
		return hasGot;
	}

	public void setHasGot(boolean hasGot) {
		this.hasGot = hasGot;
	}

	public boolean getWantsToBuy() {
		return wantsToBuy;
	}

	public void setWantsToBuy(boolean wantsToBuy) {
		this.wantsToBuy = wantsToBuy;
	}

}