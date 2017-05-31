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
 * Each Account object should have: User[] followers, User[] following, String name and Password password.
 * This class will contain the information about a User that is protected by the User’s password.
 */
public class Account implements Serializable{
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private String name;
    private Password password;

    /**
     * Constructor for the Account objects, Takes in a name and a password
     * @param name String
     * @param password String
     */
    public Account(String name, Password password) {
        this.followers = new ArrayList<User>();
        this.following = new ArrayList<User>();
        this.name = name;
        this.password = password;
    }

    /**
     * Gets the ArrayList of User objects that contains the users that follow the account
     * @return ArrayList<User>
     */
    public ArrayList<User> getFollowers() {
        return followers;
    }

    /**
     * Gets the ArrayList of User objects that contains the users the account is following
     * @return ArrayList<User>
     */
    public ArrayList<User> getFollowing() {
        return following;
    }

    /**
     * Get the String name associated w
     * h the account
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get the Password wrapper object that contains the String password for an Account
     * @return Password
     */
    public Password getPassword() {
        return password;
    }
}
