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
 * The database of accounts will be stored in a HashMap. Uses the email of the User objects as the key for hashing.
 */
public class AccountDatabase extends HashMap<String, Account> implements Serializable{
    /**
     * This method adds a new Account into the AccountDatabase using the specified User email as the key.
     *
     * @param email
     * @param account
     * @throws IllegalArgumentException if the given email = null or if the email is already in the AccountDatabase.
     */
    public void addAccount(String email, Account account) throws IllegalArgumentException {
        if (email == null || (!isEmpty() && containsKey(email))) {
            throw new IllegalArgumentException();
        }
        put(email, account);
    }

    /**
     * Retrieves the Account from the table having the indicated email. If the requested email does not exist in the AccountDatabase, return null.
     * @param email
     * @return
     */
    public Account getAccountInformation(String email) {
            return get(email);
    }

    /**
     * This method removes an Account from the AccountDatabase.
     *
     * @param email
     * @throws IllegalArgumentException if the given email = null or if the email is not in the AccountDatabase.
     */
    public void removeAccount(String email) throws IllegalArgumentException {
        if (email == null || !containsKey(email)) {
            throw new IllegalArgumentException();
        }
        remove(email);
    }
}