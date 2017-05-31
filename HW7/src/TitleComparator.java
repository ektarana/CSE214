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
 * Movies should be sorted by title so to accomplish this, TitleComparator implements the Comparator interface which allows
 * the Movie instances to be arranged in sorted order based on the value of the member variable title.
 */
public class TitleComparator implements Comparator<Movie> {
    /**
     * /**
     * Compare takes in the two Movie objects and returns an int based on the values of their title member variables.
     * The value returned will be 0 if the argument is a string lexicographically equal to this string;
     * a value less than 0 if the argument is a string lexicographically greater than this string;
     * and a value greater than 0 if the argument is a string lexicographically less than this string.
     * @param left an Movie object that has a String title member variable to be compared with right
     * @param right an Movie object that has a String title2 member variable to be compared with left
     * @return an integer value that is less than 0, greater than 0 or 0 if the Strings are equal
     */
    @Override
    public int compare(Movie left, Movie right) {
        return (left.getTitle().compareTo(right.getTitle()));
    }
}
