package Users;

import java.io.Serializable;

public abstract class User implements Serializable{

	private String firstName;
	private String LastName;
	private String Email;
	private String ID;
	private String Password;
	int level;
	//0-user, 1-subscriber, 2-librarian

	protected User() {}
	
	public User(String firstName, String lastName, String email, String iD, String password, int level) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		Email = email;
		ID = iD;
		Password=password;
		this.level = level;
	}
	
	
	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	//public Book Search(String SearchField)(){}
	
	public void updatePersonalDetails() {}
	
	
	
}
