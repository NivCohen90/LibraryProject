package App;

import java.io.Serializable;

public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String StudentID;
	String StudentName;
	String StatusMembership;
	String Operation;
	String Freeze;
	public int commend;
	
	public Student(String studentID, String studentName, String statusMembership, String operation, String freeze) {
		this.StudentID = studentID;
		this.StudentName = studentName;
		this.StatusMembership = statusMembership;
		this.Operation = operation;
		this.Freeze = freeze;
		this.commend = 0;
	}

	public String getStudentID() {
		return StudentID;
	}

	public void setStudentID(String studentID) {
		StudentID = studentID;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getStatusMembership() {
		return StatusMembership;
	}

	public void setStatusMembership(String statusMembership) {
		StatusMembership = statusMembership;
	}

	public String getOperation() {
		return Operation;
	}

	public void setOperation(String operation) {
		Operation = operation;
	}

	public String getFreeze() {
		return Freeze;
	}

	public void setFreeze(String freeze) {
		Freeze = freeze;
	}


	@Override
	public String toString() {
		return "Student [StudentID=" + StudentID + ", StudentName=" + StudentName + ", StatusMembership="
				+ StatusMembership + ", Operation=" + Operation + ", Freeze=" + Freeze + "]";
	}
	
}
