/**
 * Ekta Rana
 * 111030624
 * HW 7
 * R08
 * Michael Rizzo
 * Tim Zhang
 */

import java.util.*;
import big.data.DataSource;

/**
 * KBCalculator is the driver class that allows a user to view datasets obtained from the OMDB API.
 * This class should contain a main method which creates an ActionGraph and prompts the user to import Movies,
 * display all the current Movies in alphabetical order, display all the current Actors in alphabetical order,
 * print the shortest path from one Actor to another, print the breadth first traversal from an Actor,
 * as well as look up a certain Actor by a given name.
 */
public class KBCalculator {
    /**
     * The main method runs a menu driven application which creates an ActionGraph instance
     * and then prompts the user for a menu command selecting the operation.
     * The required information is then requested from the user based on the selected operation.
     *
     * @param args
     */
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String selection = "";
        boolean run = true;
        ActorGraph graph = new ActorGraph();
        NameComparator nc = new NameComparator();
        TitleComparator tc = new TitleComparator();

        System.out.println("Welcome to the Kevin Bacon Calculator!");

        while (run) {
            printMenu();
            System.out.println("Please select an option: ");
            selection = input.nextLine();
            selection = selection.toUpperCase();
            switch (selection) {
                case "I":
                    try {
                        System.out.print("Enter a movie title: ");
                        String title = input.nextLine();
                        if (title.length() > 0) {
                            //check if the movie has already been added
                            if (!graph.getMoviesByTitle().containsKey(title)) {
                                //generate the webaddress for the movie
                                String prefix = "http://www.omdbapi.com/?t=";
                                String postfix = "&y=&plot=short&r=xml";

                                DataSource ds = DataSource.connectXML(prefix + title.replace(' ', '+') + postfix);
                                ds.load();
                                //prints the title
                                System.out.println("title: " + ds.fetchString("movie/title"));
                                String trueTitle = ds.fetchString("movie/title");
                                //prints actors
                                System.out.println("actors: " + ds.fetchString("movie/actors"));
                                String actorString = ds.fetchString("movie/actors");
                                //adds actors to an AL
                                ArrayList<String> actorStringsAL = new ArrayList(Arrays.asList(actorString.split("\\s*,\\s*")));
                                ArrayList<Actor> actorsAL = new ArrayList<>();
                                for (int i = 0; i < actorStringsAL.size(); i++) {
                                    if (graph.getActorsByName().containsKey(actorStringsAL.get(i))) {
                                        actorsAL.add(graph.getActorsByName().get(actorStringsAL.get(i)));
                                    } else {
                                        Actor actor = new Actor(actorStringsAL.get(i));
                                        actorsAL.add(actor);
                                    }
                                }
                                //gets the year the movie was made
                                System.out.println("year: " + ds.fetchInt("movie/year"));
                                int year = ds.fetchInt("movie/year");

                                //create a movie object
                                Movie mov = new Movie(trueTitle, actorsAL, year);
                                //add the movie to the hashmap in actorgraph
                                graph.getMoviesByTitle().put(trueTitle, mov);
                                //add this movie to each actors list of movies
                                for (int i = 0; i < actorsAL.size(); i++) {
                                    actorsAL.get(i).getMovies().add(mov);
                                }
                                //then add friends for each actor
                                for (int i = 0; i < actorsAL.size(); i++) {
                                    for (int j = 0; j < actorsAL.size(); j++) {
                                        if (!(actorsAL.get(i).equals(actorsAL.get(j)))) {
                                            if (!actorsAL.get(i).getFriends().contains(actorsAL.get(j))) {
                                                actorsAL.get(i).getFriends().add(actorsAL.get(j));
                                            }
                                        }
                                    }
                                }
                                //add actors to actorsByName Hashmap
                                for (int i = 0; i < actorsAL.size(); i++) {
                                    if (!graph.getActorsByName().containsKey(actorsAL.get(i).getName())) {
                                        graph.getActorsByName().put(actorsAL.get(i).getName(), actorsAL.get(i));
                                    }
                                }
                            }

                        } else {
                            System.out.println("this movie already exists in the database");
                        }
                    } catch (big.data.DataInstantiationException e) {
                        System.out.println("sorry this movie doesn't exist. check your spelling.");
                    } catch (Exception e) {
                        System.out.println("something went wrong. try again!");
                    }
                    break;
                case "A":
                    System.out.println("Here is the alphabetic list of actors:");
                    System.out.println("-----------------------------------------------");

                    Iterator iterator = graph.getActorsByName().keySet().iterator();
                    ArrayList<Actor> actors = new ArrayList<>();
                    while (iterator.hasNext()) {
                        actors.add(graph.getActorsByName().get(iterator.next()));
                    }
                    Collections.sort(actors, nc);
                    for (int i = 0; i < actors.size(); i++) {
                        System.out.println(actors.get(i).getName());
                    }
                    break;

                case "M":
                    System.out.println("Here is the alphabetic list of movies:");
                    System.out.println("-----------------------------------------------");

                    iterator = graph.getMoviesByTitle().keySet().iterator();
                    ArrayList<Movie> movies = new ArrayList<>();
                    while (iterator.hasNext()) {
                        movies.add(graph.getMoviesByTitle().get(iterator.next()));
                    }
                    Collections.sort(movies, tc);
                    for (int i = 0; i < movies.size(); i++) {
                        System.out.println(movies.get(i).getTitle());
                    }

                    break;

                case "P":
                    System.out.println("Please enter the first name: ");
                    String name1 = input.nextLine();
                    System.out.println("Please enter the second name: ");
                    String name2 = input.nextLine();

                    if (!(graph.getActorsByName().containsKey(name1) || graph.getActorsByName().containsKey(name2))) {
                        System.out.println("One of those actors does not exist in the graph.");
                    } else {
                        try {
                            graph.bfs(name1);

                            if (!graph.getActorsByName().get(name2).getPath().contains(name1)) {
                                System.out.println("No path.");
                            } else {
                                for (String people : graph.getActorsByName().get(name2).getPath()) {
                                    System.out.println(people);
                                    if (people.equals(name2)) {
                                        break;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("error.");
                        }
                    }
                    break;
                case "B":
                    String name;
                    System.out.println("Enter a starting actor: ");
                    name = input.nextLine();
                    try {
                        LinkedList<String> bfs = graph.bfs(name);
                        System.out.println(Arrays.toString(bfs.toArray()));
                    } catch (Exception e) {
                        System.out.println("Error. Try Again.");
                    }
                    break;
                case "L":
                    System.out.println("Enter a name: ");
                    name = input.nextLine();

                    if (graph.getActorsByName().containsKey(name)) {
                        System.out.println("Actor: " + name);
                        System.out.println("Friends: ");
                        for (int i = 0; i < graph.getActorsByName().get(name).getFriends().size(); i++) {
                            System.out.println(graph.getActorsByName().get(name).getFriends().get(i).getName());
                        }
                        System.out.println("Movies: ");
                        for (int i = 0; i < graph.getActorsByName().get(name).getMovies().size(); i++) {
                            System.out.println(graph.getActorsByName().get(name).getMovies().get(i).getTitle() + " (" +
                                    graph.getActorsByName().get(name).getMovies().get(i).getYear() + ")");
                        }

                    } else {
                        System.out.println("this actor is not in the database");
                    }
                    break;
                case "Q":
                    System.out.println("BYE");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid. Try again.");
            }
        }
    }

    /**
     * prints the menu options to the console
     */
    public static void printMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("Options:");
        System.out.println("I)Import a Movie");
        System.out.println("A)Print all actors");
        System.out.println("M)Print all movies");
        System.out.println("P)Print the shortest path between two actors.");
        System.out.println("B)Print the BFS(Breadth First Search) from a given actor");
        System.out.println("L)Lookup Actor By Name");
        System.out.println("Q)Quit");
        System.out.println();
    }

}
