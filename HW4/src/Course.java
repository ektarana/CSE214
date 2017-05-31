/**
 * Ekta Rana
 * 111030624
 * HW#4
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */

/**
 * This class represents a course which is offered at the university.
 * Each instance contains a courseNumber (int) and a courseDifficulty (int) and an arrivalProbabilty (int).
 * The course difficulty will depend on how many courses that were passed at the onset.
 * The value of courseDifficulty will be determined by how many courses there are and the course numbers.
 */
public class Course implements Comparable<Course> {
    private int courseNumber;
    private int courseDifficulty;
    private double arrivalProbability;

    /**
     * returns the arrivalProbability
     *
     * @return double
     */
    public double getArrivalProbability() {
        return arrivalProbability;
    }

    /**
     * takes in a double value that is set to the arrivalProbability
     *
     * @param arrivalProbability double
     */
    public void setArrivalProbability(double arrivalProbability) {
        this.arrivalProbability = arrivalProbability;
    }

    /**
     * returns the courseDifficulty, an int value assigned based on how hard the course is relative to others
     *
     * @return int
     */
    public int getCourseDifficulty() {
        return courseDifficulty;
    }

    /**
     * takes in an int value that is set to the courseDifficulty
     *
     * @param courseDifficulty int
     */
    public void setCourseDifficulty(int courseDifficulty) {
        this.courseDifficulty = courseDifficulty;
    }

    /**
     * returns the courseNumber, an int value assigned based on the course level
     *
     * @return int
     */
    public int getCourseNumber() {
        return courseNumber;
    }

    /**
     * takes in an int to set the courseNumber to
     *
     * @param courseNumber int
     */
    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    /**
     * Default constructor that creates a Course with the given course number and arrival probability.
     * The value of the courseDifficulty member variable is set after all the Courses are sorted by their course numbers.
     * Preconditions: courseNumber exists in the given courseNumbers.
     * Postconditions: a Couse object is created with a courseNumber and an arrivalProbability
     *
     * @param courseNumber       int
     * @param arrivalProbability double
     * @throws IllegalArgumentException if courseNumber does not exist in the given courseNumbers.
     */
    public Course(int courseNumber, double arrivalProbability) throws IllegalArgumentException {
        if (courseNumber < 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks to see which of two Course objects has the larger courseNumber, hence having a higher difficulty
     *
     * @param o Course object
     * @param o Course object
     * @return int
     */
    @Override
    public int compareTo(Course o) {
        return o.getCourseNumber() - this.getCourseNumber();
    }
}