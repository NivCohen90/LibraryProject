package Users;


public class Librarian extends User{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Affiliation;
	
	public Librarian() {}

	public Librarian(String firstName, String lastName, String email, String iD, String password, String phoneNumber, String affiliation, int Level) {
		super(iD, firstName, lastName, email, phoneNumber, password, Level);
		Affiliation=affiliation;
	}

	public String getAffiliation() {
		return Affiliation;
	}

	public void setAffiliation(String affiliation) {
		Affiliation = affiliation;
	}
	
	

}
