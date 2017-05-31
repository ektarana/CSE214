/**
 * Ekta Rana
 * 111030624
 * HW5
 * section 8
 * Michael Rizzo
 * Tim Zhang
 */

/**
 * Box enum
 * Represents the possibilities of the tic-tac-toe board.
 * Each box on the board can be either occupied with an X or O, or it can be EMPTY
 */
public enum Box {
    X, O, EMPTY;

    /**
     * Returns a boolean value representing if Box a and Box b are the same type of Box objects
     * @param a takes in a Box to compare
     * @param b takes in a Box to compare a to
     * @return true if a and b are the same type, false if they are not
     */
    public boolean equals(Box a, Box b){
        if(this == a && this == b)
            return true;
        return false;
    }
}
