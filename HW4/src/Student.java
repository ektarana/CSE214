/**
 * Ekta Rana
 * 111030624
 * HW#4
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */

/**
 * This class represents a Student which goes to the professor’s office hours.
 * Each instance contains the studentId, an int based on the time the student arrives, the timeArrived (int), and the Course the Student is in.
 * The Student is initialized with a studentId, Course, and the time the Student arrived.
 * The student counter is static and stores the number of Students that have arrived at the professor’s office hours so far.
 * It is the only variable that is modifiable.
 */
public class Student {
    private static int studentCounter = 0;
    private int studentId;
    private int timeArrived;
    private int timeRequired;
    private Course course;

    /**
     * gets the student's id number
     * @return int
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * sets the student ID to the order in which the student arrives
     * @param studentId, takes in a number
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * returns an int for the number of students
     * @return int
     */
    public static int getStudentCounter() {
        return studentCounter;
    }

    /**
     * sets the student counter
     * @param studentCounter
     */
    public static void setStudentCounter(int studentCounter) {
        Student.studentCounter = studentCounter;
    }

    /**
     * returns the time step when the student arrived
     * @return int
     */
    public int getTimeArrived() {
        return timeArrived;
    }

    /**
     * sets the time arrived
     * @param timeArrived
     */
    public void setTimeArrived(int timeArrived) {
        this.timeArrived = timeArrived;
    }

    /**
     * gets the time required for the student to get help
     * @return int
     */
    public int getTimeRequired() {
        return timeRequired;
    }

    /**
     * sets the time required
     * @param timeRequired
     */
    public void setTimeRequired(int timeRequired) {
        this.timeRequired = timeRequired;
    }

    /**
     * gets the course that the student is in and all of its data
     * @return Course object
     */
    public Course getCourse() {
        return course;
    }

    /**
     * sets a student's course
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Default constructor. Automatically increments the studentCounter, sets the studentId to its new value, and sets the timeRequired to the value generated.
     * Preconditions: initTimeArrived > 0; the course’s field courseNumber exists in the given courseNumbers.
     * Postconditions: a new Student is created with values for initTimeArrived, course, and timeRequired
     * @param initTimeArrived int
     * @param course Course object
     * @param timeRequired int
     * @throws IllegalArgumentException if initTimeArrived <= 0, or if courseNumber does not exist in the given courseNumbers.
     */
    public Student(int initTimeArrived, Course course, int timeRequired) throws IllegalArgumentException{
        if(initTimeArrived <= 0)  {
            throw new IllegalArgumentException();
        }
        studentCounter++;
        studentId = studentCounter;
        this.timeRequired = timeRequired;
    }
}
