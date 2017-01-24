package readingProject;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Users {

	private Integer userId;
	private String userName, email, password;
	private LocalDateTime userSince;

	private Set<Interactions> interactions = new HashSet<Interactions>();

	public Users() {
	}

	public Users(String userName, String email, String password, LocalDateTime userSince) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userSince = userSince;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getUserSince() {
		return userSince;
	}

	public void setUserSince(LocalDateTime userSince) {
		this.userSince = userSince;
	}

	public Set<Interactions> getInteractions() {
		return interactions;
	}

	public void setInteractions(Set<Interactions> interactions) {
		this.interactions = interactions;
	}

	public void addInteraction(Interactions interactionToBeAdded) {
		this.interactions.add(interactionToBeAdded);
	}
}
