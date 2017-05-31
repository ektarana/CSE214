/**
 * Ekta Rana
 * 111030624
 * HW#4
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */
/**
 * BooleanSource abstracts a random occurrence generator.
 * This class is constructed with an initial arrival probability, a double between 0 and 1
 * which represents the likelihood that a Student will arrive at the office hours at the beginning of each time step.
 * This class contains a single method, occurs() which returns true if a Student arrives and false if not.
 */
public class BooleanSource {
    private double probability;
    /**
     * Constructor which initalizes the probability to the indicated parameter.
     * Preconditions: 0 < initProbability <=1.
     * Postconditions: a BooleanSource object is created with a double value for initProbability
     *
     * @param initProbability double
     * @throws IllegalArgumentException if initProbability <= 0 or initProbability > 1.
     */
    public BooleanSource(double initProbability) throws IllegalArgumentException {
        if (initProbability <= 0 || initProbability > 1) {
            throw new IllegalArgumentException();
        }
        probability = initProbability;
    }
    /**
     * Method which returns true with the probability indicated by the member variable probability.
     * Preconditions: probability is a valid probability (0 < initProbability <=1).
     * Postconditions: an event will or will not occur based on the probability value randomly generated
     * Returns boolean value indicating whether an event has occured or not.
     *
     * @return boolean
     */
    public boolean occurs() {
        return Math.random()<=probability;

    }
}
