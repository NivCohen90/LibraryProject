package Users;
import java.util.ArrayList;
import java.util.Date;

public class Book {

	private String BookName;
	private ArrayList<String> AuthorName;
	private String CatalogNumber;
	private String ShelfLoaction;
	private int AvailableCopies;
	private int NumberOfLibraryCopies;
	private String Description;
	private String subject;
	private String ContextTable;
	private String EditionNumber;
	private Date purchesDate;
	private ArrayList<Loan> Loaners;
	
	public Book(String bookName, String catalogNumber, String shelfLoaction, int availableCopies, int numberOfLibraryCopies, String description, String subject, String contextTable, String editionNumber, Date purchesDate) {
		BookName = bookName;
		AuthorName = new ArrayList<String>();
		CatalogNumber = catalogNumber;
		ShelfLoaction = shelfLoaction;
		AvailableCopies = availableCopies;
		NumberOfLibraryCopies = numberOfLibraryCopies;
		Description = description;
		this.subject = subject;
		ContextTable = contextTable;
		EditionNumber = editionNumber;
		this.purchesDate = purchesDate;
		Loaners = new ArrayList<Loan>();
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public ArrayList<String> getAuthorName() {
		return AuthorName;
	}

	public void setAuthorName(ArrayList<String> authorName) {
		AuthorName = authorName;
	}

	public String getCatalogNumber() {
		return CatalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		CatalogNumber = catalogNumber;
	}

	public String getShelfLoaction() {
		return ShelfLoaction;
	}

	public void setShelfLoaction(String shelfLoaction) {
		ShelfLoaction = shelfLoaction;
	}

	public int getAvailableCopies() {
		return AvailableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		AvailableCopies = availableCopies;
	}

	public int getNumberOfLibraryCopies() {
		return NumberOfLibraryCopies;
	}

	public void setNumberOfLibraryCopies(int numberOfLibraryCopies) {
		NumberOfLibraryCopies = numberOfLibraryCopies;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContextTable() {
		return ContextTable;
	}

	public void setContextTable(String contextTable) {
		ContextTable = contextTable;
	}

	public String getEditionNumber() {
		return EditionNumber;
	}

	public void setEditionNumber(String editionNumber) {
		EditionNumber = editionNumber;
	}

	public Date getPurchesDate() {
		return purchesDate;
	}

	public void setPurchesDate(Date purchesDate) {
		this.purchesDate = purchesDate;
	}

	public ArrayList<Loan> getLoaners() {
		return Loaners;
	}

	public void setLoaners(ArrayList<Loan> loaners) {
		Loaners = loaners;
	}
		
}
