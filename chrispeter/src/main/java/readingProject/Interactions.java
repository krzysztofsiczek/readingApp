package readingProject;

public class Interactions {

	private Books_Users_Id booksUsersId = new Books_Users_Id();
	
	private boolean hasRead, hasGot, wantsToBuy;

	protected Interactions() {
	}

	protected Books_Users_Id getId() {
		return booksUsersId;
	}

	protected void setId(Books_Users_Id booksUsersId) {
		this.booksUsersId = booksUsersId;
	}

	protected Books_collection getBooks_collection() {
		return getId().getBooks();
	}

	protected void setBooks_collection(Books_collection bookId) {
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