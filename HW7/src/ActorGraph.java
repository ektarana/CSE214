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
 * This class will model the graph of Actors.
 * The graph will be structured with nodes being represented as Actors and edges between Actors being represented as whether or not those Actors share a Movie.
 */
public class ActorGraph {
    private static HashMap<String, Actor> actorsByName;
    private static HashMap<String, Movie> moviesByTitle;

    /**
     * Returns the breadth first traversal starting with the Actor with the passed in name.
     * This method also clears and sets the path variable of the Actors passed in the breadth first traversal with the current path up to that Actor.
     *
     * @param name String that is the name of the actor whose graph you want
     * @return LinkedList of Strings that represent all the actors that this actor is connected to through friends, friends of friends, etc.
     * @throws IllegalArgumentException if the actors don't exist in the graph
     */
    public static LinkedList<String> bfs(String name) throws IllegalArgumentException {
        if (!actorsByName.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        //clears the path
        Iterator it = actorsByName.keySet().iterator();
        while (it.hasNext()) {
            actorsByName.get(it.next()).getPath().clear();
        }

        LinkedList<String> actorNameLL = new LinkedList<>();
        HashSet<Actor> visited = new HashSet<>();
        ArrayDeque<Actor> toVisit = new ArrayDeque<>();

        visited.add(actorsByName.get(name));
        toVisit.add(actorsByName.get(name));

        while (!toVisit.isEmpty()) {
            Actor actor = toVisit.remove();
            actor.getPath().add(actor.getName());
            actorNameLL.add(actor.getName());
            if (actor.getFriends().isEmpty()) {
                return actorNameLL;
            }

            visited.add(actor); //adds actor to visited

            //adds children to queue
            for (int i = 0; i < actor.getFriends().size(); i++) {
                if (!visited.contains(actor.getFriends().get(i))) {
                    toVisit.add(actor.getFriends().get(i));
                    visited.add(actor.getFriends().get(i));

                    for (String str : actor.getPath()) {
                        actor.getFriends().get(i).getPath().add(str);
                    }
                }
            }
        }
        return actorNameLL;
    }

    /**
     * Constructs an ActorGraph. Instantiates the HashMaps containing the map of all the actors saved by their name and the map of all the movies saved by their title
     */
    public ActorGraph() {
        actorsByName = new HashMap<>();
        moviesByTitle = new HashMap<>();
    }

    /**
     * Returns the HashMap containing all the Actors by the key, name, and value, Actor object
     *
     * @return HashMap that contains all the Actors
     */
    public static HashMap<String, Actor> getActorsByName() {
        return actorsByName;
    }

    /**
     * Returns the HashMap containing all the Movies by the key, title, and value, Movie object
     *
     * @return HashMap that contains all the Movies
     */
    public static HashMap<String, Movie> getMoviesByTitle() {
        return moviesByTitle;
    }
}
