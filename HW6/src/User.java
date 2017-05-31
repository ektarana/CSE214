/**
 * Ekta Rana
 * 111030624
 * HW 6
 * R08
 * Michael Rizzo
 * Tim Zhang
 */

import java.io.Serializable;

/**
 * User that contains two data fields: String name, String email. This class will represent information about a User.
 */
public class User implements Serializable{
    private String name;
    private String email;

    /**
     * User constructor that makes new User objects
     * @param name String
     * @param email String
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the name of the User
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of the User for printing purposes
     * @return String
     */
    public String getEmail() {
        return email;
    }
}
