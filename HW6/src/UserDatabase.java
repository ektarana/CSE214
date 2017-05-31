/**
 * Ekta Rana
 * 111030624
 * HW 6
 * R08
 * Michael Rizzo
 * Tim Zhang
 */

import java.io.Serializable;
import java.util.*;

/**
 * The database of Users will be stored in a HashMap. Use the email of the User as the key for the HashMap.
 * In this assignment, you must use the HashMap/HashTable implementation provided by the Java API.
 */
public class UserDatabase extends HashMap<String, User> implements Serializable {

    /**
     * This method adds a User into the UserDatabase using the specified email as the key.
     *
     * @param email
     * @param user
     * @throws IllegalArgumentException if the given email = null or if the email is already in the UserDatabase. You may assume the email address will be of the correct format.
     */
    public void addUser(String email, User user) throws IllegalArgumentException {
        if (email == null || (!isEmpty() && containsKey(email))) {
            throw new IllegalArgumentException();
        }
        put(email, user);
    }

    /**
     * Retrieves the User from the table having the indicated email. If the requested email does not exist in the UserDatabase, return null.
     *
     * @param email
     * @return
     */
    public User getUser(String email) {
        return get(email);

    }

    /**
     * This method removes a User from the UserDatabase.
     * @param email
     * @throws IllegalArgumentException if the given email = null or if it doesn’t exist in the UserDatabase.
     */
    public void removeUser(String email) throws IllegalArgumentException {
        if (email == null || !containsKey(email)) {
            throw new IllegalArgumentException();
        }
        remove(email);
    }

    /**
     * Prints the data of the User HashMap to the console
     */
    public void printData() {
        Set<String> keys = keySet();
        System.out.println("All users: ");
        System.out.println("Email                          Name");
        System.out.println("------------------------------------------------------------------------");

        for (String email : keys) {
            System.out.println(String.format("%-30s%-26s", email, get(email).getName()));
        }
        System.out.println();
    }

}

