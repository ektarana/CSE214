/**
 * Ekta Rana
 * 111030624
 * HW 7
 * R08
 * Michael Rizzo
 * Tim Zhang
 */

import java.util.ArrayList;

/**
 * This class will represent a Movie that an Actor could be in. The information about each Movie is from the Open Movie Database.
 */
public class Movie {
    private String title;
    private ArrayList<Actor> actors = new ArrayList<>();
    private int year;

    /**
     * Loads (using BigData) a movie using the passed title to create the URL and makes a new Movie object from it.
     *
     * @param title String that is the name of the movie
     */
    public Movie(String title) {
        this.title = title;
    }

    /**
     * Returns a String that contains the title of the movie
     * @return String that is the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * returns the year that the movie was released
     * @return int representing the year the movie was released
     */
    public int getYear() {
        return year;
    }

    /**
     * Constructor for the Movie object that takes in a String title, ArrayList of Actors that were in the Movie, and the year the film was released
     * @param title String that is the title of the movie
     * @param actors ArrayList that holds all the Actors in the movie
     * @param year int that is the release date
     */
    public Movie(String title, ArrayList<Actor> actors, int year) {
        this.title = title;
        this.actors = actors;
        this.year = year;
    }

    /**
     * String that is passed in representing the title of the movie that the title variable is set to
     * @param title String that is passed in representing the title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * ArrayList of Actor objects that is passed in representing the actors of the movie
     * @param actors ArrayList of Actor objects that is passed in representing the actors of the movie
     */
    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    /**
     * Takes in an int that represents the year the film was released and sets the year variable to that value
     * @param year int that represents the year the film was released
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * returns the list of actors in a Movie
     * @return ArrayList of all the Actors in the Movie
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }
}
