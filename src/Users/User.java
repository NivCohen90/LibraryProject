package Users;

public abstract class User {

	private String firstName;
	private String LastName;
	private String Email;
	private String ID;
	private String Password;
	
	public User(String firstName, String lastName, String email, String iD, String password) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		Email = email;
		ID = iD;
		Password=password;
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
	
	//public Book Search(String SearchField)(){}
	
	public void updatePersonalDetails() {}
	
	
	
}
