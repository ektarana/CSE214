/**
 * Ekta Rana
 * 111030624
 * HW#4
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */

/**
 * This class represents someone who helps the Students at the office hours whether it be a professor or TA.
 * Each instance must contain the timeLeftTilFree (int) and isProfessor (boolean) variables.
 * The value of the timeLeftTilFree member variable will be determined based on the value of the parameter timeRequired,
 * which in turn is based on minTime and maxTime passed in during the onset of the simulation.
 */
public class Helper {
    private int timeLeftTilFree;
    private final boolean isProfessor;

    /**
     * returns the value of timeLeftTilFree
     * @return an int, the value of timeLeftTilFree
     */
    public int getTimeLeftTilFree() {
        return timeLeftTilFree;
    }

    /**
     * sets the value of timeLeftTilFree belonging to a Helper to a new value
     * @param timeLeftTilFree an int representing the amount of time that a helper will spend with a student
     */
    public void setTimeLeftTilFree(int timeLeftTilFree) {
        this.timeLeftTilFree = timeLeftTilFree;
    }

    /**
     * Default constructor that creates a Helper that could be either a professor or a TA.
     *
     * @param isProfessor If the value of isProfessor is false, then the Helper is a TA. Inversely, if the value of isProfessor is true, then the Helper is a professor.
     *                    Preconditions: isProfessor is true or false
     *                    Postconditions: timeLeftTilFree = 0.
     */
    public Helper(boolean isProfessor) {
        timeLeftTilFree = 0;
        this.isProfessor = isProfessor;
    }
}
