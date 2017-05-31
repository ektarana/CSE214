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
 * This class will represent the social network we are trying to model in this homework.
 * This class will allow the user to interact with the UserDatabase and AccountDatabase by allowing the user to create and close accounts in the social network.
 * The key for hashing in the UserDatabase and AccountDatabase will be the email of the User.
 */
public class Bitter implements Serializable{
    private UserDatabase users;
    private AccountDatabase accounts;

    /**
     * Manually inserts a User into the UserDatabase and Account into the AccountDatabase both with the specified key (email).
     * Preconditions:
     * email does not already exist in the table.
     * user != null && account != null
     * Postconditions:
     * The user and account have been inserted into their respective databases with the indicated key.
     * @param email
     * @param user
     * @param account
     * @throws IllegalArgumentException if the given email = null, or if it’s already stored in either of the databases.
     */
    public void addUser(String email, User user, Account account) throws IllegalArgumentException {
        if (user == null || account == null) {
            throw new IllegalArgumentException();
        }else if ((getUsers() !=null && getUsers().containsKey(email)) || (getAccounts() != null && accounts.containsKey(email))){
            throw new IllegalArgumentException();
        }
        users.addUser(email, user);
        accounts.addAccount(email, account);
    }
    /**
     * This method removes a User from the social network.
     * @param email
     * @throws IllegalArgumentException if the given email = null or if the User with the given email is not in the social network.
     */
    public void removeUser(String email) throws IllegalArgumentException{
        if(email == null || !users.containsKey(email)) {
            throw new IllegalArgumentException();
        }
        removeUser(email);
    }

    /**
     * Gets the HashMap containing the Users in Bitter
     * @return HashMap of Users
     */
    public UserDatabase getUsers() {
        return users;
    }

    /**
     * Gets the HashMap containing the Users in Bitter
     * @return HashMap of Accounts
     */
    public AccountDatabase getAccounts() {
        return accounts;
    }

    /**
     * Constructor for Bitter objects.
     * Takes in HashMaps of Users and Accounts
     * @param users HashMap
     * @param accounts HashMap
     */
    public Bitter(UserDatabase users, AccountDatabase accounts) {
        this.users = users;
        this.accounts = accounts;
    }
}
