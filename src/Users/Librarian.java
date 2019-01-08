package Users;

public class Librarian extends User{
	
	private String Affiliation;

	public Librarian(String firstName, String lastName, String email, String iD, String password, String affiliation) {
		super(firstName, lastName, email, iD, password,2);
		Affiliation=affiliation;
	}

	public String getAffiliation() {
		return Affiliation;
	}

	public void setAffiliation(String affiliation) {
		Affiliation = affiliation;
	}
	
	

}
