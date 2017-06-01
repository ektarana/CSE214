/**
 * Ekta Rana 111030624 HW#1 Recitation section: 08 Recitation TA: Michael Rizzo
 */

public class Floor {
	private Student[] students;
	final int CAPACITY = 50;
	private int studentAmount;

	/**
	 * @return the studentAmount
	 */
	public int getStudentAmount() {
		return studentAmount;
	}

	/**
	 * @param studentAmount
	 *            the studentAmount to set
	 */
	public void setStudentAmount(int studentAmount) {
		this.studentAmount = studentAmount;
	}

	/**
	 * Floor constructor - makes a Floor object with an array of Students at size CAPACITY
	 */
	public Floor() {
		students = new Student[CAPACITY];
	}

	/**
	 * 
	 * @param student
	 * takes in Student object to add
	 * @param position
	 * the spot at which to add the Student
	 * @throws FullFloorException
	 * thrown when the array of Students is full
	 * @throws IllegalArgumentException
	 * thrown when the arguments do not match
	 */
	public void addStudent(Student student, int position) throws FullFloorException, IllegalArgumentException {
		// make sure pos is not less than 0 or geq to cap
		// check that pos-1 has something in it, unless position =0
		if (position < 0 || position > CAPACITY - 1) {
			throw new IllegalArgumentException();
		}
		if (students[CAPACITY - 1] != null) {
			throw new FullFloorException();
		}
		// this is a valid pos
		if (position > 0 && students[position - 1] != null) {
			shiftRight(position);
			students[position] = student;
			studentAmount++;
		}
		// this is if the pos is 0 and 0 is empty
		else if (position == 0 && students[position] == null) {
			students[position] = student;
			studentAmount++;
		}
		// this is if the pos is 0 and 0 is not empty
		else if (position == 0 && students[position] != null) {
			shiftRight(position);
			students[position] = student;
			studentAmount++;
		}
	}

	/**
	 * 
	 * @param position
	 * takes in a position to remove a Student
	 * @return
	 * the removed student
	 * @throws EmptyFloorException
	 * thrown when trying to remove a student from an empty floor
	 * @throws IllegalArgumentException
	 * thrown when the arguments do not match
	 */
	public Student removeStudent(int position) throws EmptyFloorException, IllegalArgumentException {
		if (position < 0 || position > CAPACITY - 1) {
			throw new IllegalArgumentException();
		}
		if (students[0] == null) {
			throw new EmptyFloorException();
		}
		shiftLeft(position);
		studentAmount--;
		return students[position];

	}

	/**
	 * 
	 * @param position
	 * takes in an int position
	 * @return
	 * the Student object at the position
	 */
	public Student getStudent(int position) {
		if (position < 0 || position > CAPACITY - 1) {
			throw new IllegalArgumentException();
		}
		return students[position];
	}

	/**
	 * 
	 * @param student
	 * takes a student object
	 * @param position
	 * the location to set the student in the array
	 * @throws IllegalArgumentException
	 * thrown when the arguments do not match
	 * @throws FullFloorException
	 * thrown when the floor is at capacity
	 */
	public void setStudent(Student student, int position) throws IllegalArgumentException, FullFloorException {
		if (position < 0 || position > CAPACITY - 1) {
			throw new IllegalArgumentException();
		}
		if (students[CAPACITY - 1] != null) {
			throw new FullFloorException();
		}
		// this is a valid pos
		if (position > 0 && students[position - 1] != null) {
			students[position] = student;
		}
		// this is if the pos is 0 and 0 is empty
		else if (position == 0) {
			students[position] = student;
		}
	}

	/**
	 * @return
	 * the amount of students in the array
	 */
	public int count() {
		// Returns the number of students in the floor
		// This should run in O(1) time.
		return studentAmount;
	}

	/**
	 * @return 
	 * a clone of the floor 
	 */
	public Floor clone() {
		// Makes a deep copy of the floor, with the students in the array by
		// using the clone method from the Student class.
		Floor f = new Floor();
		f.studentAmount = studentAmount;
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i] != null)
				f.students[i] = students[i].clone();
		}
		return f;
	}

	/**
	 * 
	 * @param name
	 * takes in the student's name
	 * @throws IllegalArgumentException
	 * thrown when the arguments do not match
	 * @throws EmptyFloorException
	 * thrown when the floor is empty
	 */
	public void writeup(String name) throws IllegalArgumentException, EmptyFloorException {
		if (students[0] == null) {
			throw new EmptyFloorException();
		}
		for (int i = 0; i < CAPACITY; i++) {
			if (students[i] != null) {
				if (name.equals(students[i].getName()) && students[i].getNumWriteups() == 2) {
					removeStudent(i);
				} else if (name.equals(students[i].getName())) {
					students[i].setNumWriteups(students[i].getNumWriteups() + 1);
				}
			}
		}
	}

	/**
	 * prints the floor number and all the students on the floor with their room numbers, names, ID numbers, and number of writeups
	 */
	public void printFloor() {
		System.out.printf("%1s  %-7s   %-7s   %-6s", "Room", "Name", "ID", "Writeups \n");
		System.out.println("---------------------------------");
		for (int i = 0; i < students.length; i++) {
			if (students[i] != null) {
				System.out.printf("%1d %7s %7d %9d", i + 1, students[i].getName(), students[i].getIdNumber(),
						students[i].getNumWriteups());
				System.out.println();
			}
		}
		System.out.println();
	}

	/**
	 * @param position
	 * takes in a int value, the position, to start a right shift by one in the array
	 */
	public void shiftRight(int position) {
		for (int i = CAPACITY - 1; i > position; i--) {
			students[i] = students[i - 1];
		}
		students[position] = null;
	}

	/**
	 * @param position
	 * takes in a int value, the position, to start a left shift by one in the array
	 */
	public void shiftLeft(int position) {
		for (int i = position; i + 1 < CAPACITY; i++) {
			students[i] = students[i + 1];
		}
		students[CAPACITY - 1] = null;
	}
}