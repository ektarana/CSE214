/**
 * Ekta Rana
 * 111030624
 * HW 7
 * R08
 * Michael Rizzo
 * Tim Zhang
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class will represent an Actor in the graph. You will get the information about each Actor through the Open Movie Database.
 */
public class Actor {
    private String name;
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Actor> friends = new ArrayList<>();
    private boolean visited;
    private LinkedList<String> path = new LinkedList<>();

    /**
     * Constructor for an Actor object. Takes in the name of the actor as a parameter.
     * @param name String that the name variable is initialized to
     */
    public Actor(String name) {
        this.name = name;
    }

    /**
     * getName returns the name of the Actor
     * @return String that is the name of the Actor
     */
    public String getName() {
        return name;
    }

    /**
     * getMovies returns the list of all the movies that actor has been in
     * @return ArrayList of all the movies that actor has been in
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * getFriends returns the list of the actors this actor has costarred with
     * @return ArrayList of all the actors in the same movies that this actor has been in
     */
    public ArrayList<Actor> getFriends() {
        return friends;
    }

    /**
     * Returns a LinkedList that is the path of connections between actors up to that actor
     * @return LinkedList of Strings that are the actors names
     */
    public LinkedList<String> getPath() {
        return path;
    }

    /**
     * Takes in a LinkedList of Strings to set the Actor's member variable, path, to the path passed in
     * @param path LinkedList of Strings that the path is set to
     */
    public void setPath(LinkedList<String> path) {
        this.path = path;
    }

    /**
     * String passed in that is representing the name of the actor is set to the name variable
     * @param name String representing the name of the actor
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ArrayList of Movie objects is passed in that represents the list of Movies the actor has been in and is set to the movies variable
     * @param movies ArrayList of Movie objects that is the list of Movies the actor has been in
     */
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /**
     * ArrayList of Actor objects is passed in that represents the list of friends the actor has and is set to the friends variable
     * @param friends ArrayList of Actor objects that represents the list of friends the actor has
     */
    public void setFriends(ArrayList<Actor> friends) {
        this.friends = friends;
    }

    /**
     * Boolean variable representing whether or not that actor has been visited in BFT/BFS
     * @return boolean value representing whether or not that actor has been visited in BFT/BFS
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Takes in a Boolean representing whether or not the actor has been visited and sets that value to the visited variable
     * @param visited Boolean representing whether or not the actor has been visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}