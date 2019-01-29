package Users;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String LastName;
	private String Email;
	private String ID;
	private String Password;
	public String PhoneNumber;
	int level;
	//0-user, 1-subscriber, 2-librarian

	protected User() {}
	
	public User(String iD, String firstName, String lastName, String email, String phoneNumner, String password, int level) {
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
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public void updatePersonalDetails() {}

	public String getFullName() {
		return firstName+" "+LastName;
	}
	
	
	
}
