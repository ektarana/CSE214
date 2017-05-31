/**
 * Ekta Rana
 * 111030624
 * HW 7
 * R08
 * Michael Rizzo
 * Tim Zhang
 */

import java.util.*;

/**
 * NameComparator compares the two Actor objects based on the values of their name member variables.
 * Actors should be sorted by name, so to accomplish this, NameComparator implements the Comparator
 * interface which allow the Actor instances to be arranged in sorted order based on the value of the member variable name.
 */
public class NameComparator implements Comparator<Actor>  {
    /**
     * Compare takes in the two Actor objects and returns an int based on the values of their name member variables.
     * The value returned will be 0 if the argument is a string lexicographically equal to this string;
     * a value less than 0 if the argument is a string lexicographically greater than this string;
     * and a value greater than 0 if the argument is a string lexicographically less than this string.
     * @param left an Actor object that has a String name member variable to be compared with right
     * @param right an Actor object that has a String name member variable to be compared with left
     * @return an integer value that is less than 0, greater than 0 or 0 if the Strings are equal
     */
    @Override
    public int compare(Actor left, Actor right) {
        return (left.getName().compareTo(right.getName()));
    }
}

