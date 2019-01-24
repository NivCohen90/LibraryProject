package SystemObjects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CatalogNumber;
	private String BookName;
	private String AuthorName;
	private String Subject;
	private int NumberOfCopies;
	private int AvailableCopies;
	private int NumberOfOrders;
	private String ShelfLoaction;
	private String EditionNumber;
	private Date purchesDate;
	private boolean isWanted;
	private String Description;
	private String ContextTable;
	private ArrayList<Loan> Loaners;
	
	
	

		public Book(String catalogNumber,String bookName,String authorName,String subject,int numberOfLibraryCopies,int availableCopies,int NumberOfOrders,String shelfLoaction,String editionNumber,Date purchesDate,boolean iswant,String description,String contextTable) {
		BookName = bookName;
		AuthorName = authorName;
		CatalogNumber = catalogNumber;
		ShelfLoaction = shelfLoaction;
		AvailableCopies = availableCopies;
		NumberOfCopies = numberOfLibraryCopies;
		Description = description;
		this.Subject = subject;
		ContextTable = contextTable;
		EditionNumber = editionNumber;
		this.purchesDate = purchesDate;
		Loaners = new ArrayList<Loan>();
		NumberOfOrders=0;
		isWanted=false;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getAuthorName() {
		return AuthorName;
	}

	public void setAuthorName(String authorNames) {
		AuthorName = authorNames;
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
		return NumberOfCopies;
	}

	public void setNumberOfLibraryCopies(int numberOfLibraryCopies) {
		NumberOfCopies = numberOfLibraryCopies;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		this.Subject = subject;
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
	
	public void setNumberOfOrders(int orders) {
		NumberOfOrders = orders;
	}
	
	public int getNumberOfOrders() {
		return NumberOfOrders;
	}
	
	public void setIsWanted(boolean isWanted) {
		this.isWanted = isWanted;
	}
	
	public boolean getIsWanted() {
		return isWanted;
	}
		
}
