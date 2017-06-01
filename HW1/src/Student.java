/**
 * Ekta Rana 
 * 111030624 
 * HW#1 
 * Recitation section: 08 
 * Recitation TA: Michael Rizzo
 */

public class Student {
	final int MAX_WRITEUPS = 3;
	private String name;
	private int idNumber;
	private int numWriteups;

	/**
	 * @param name
	 * @param idNumber
	 * @param numWriteups
	 */
	public Student clone() {
		Student s = new Student();
		s.setName(name);
		s.setIdNumber(idNumber);
		s.setNumWriteups(numWriteups);
		return s;
	}

	/**
	 * no-args constructor
	 */
	public Student() {
		name = null;
		idNumber = 0;
	}

	/**
	 * 
	 * @param name
	 * @param idNumber
	 * @param numWriteups
	 */
	public Student(String name, int idNumber, int numWriteups) {
		super();
		this.name = name;
		this.idNumber = idNumber;
		this.numWriteups = numWriteups;
	}

	/**
	 * 
	 * @param name
	 * @param idNumber
	 */
	public Student(String name, int idNumber) {
		super();
		this.name = name;
		this.idNumber = idNumber;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return idNumber
	 */
	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return numWriteups
	 */
	public int getNumWriteups() {
		return numWriteups;
	}

	public void setNumWriteups(int numWriteups) {
		this.numWriteups = numWriteups;
	}
}