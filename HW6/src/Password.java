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
 * This class will essentially be the wrapper class for a valid password to a User’s account.
 * Each Password object should have two member variables: String password and Account account.
 */
public class Password implements Serializable {
    private String password;
    Account account;

    /**
     * The default constructor for a Password object.
     * Throws:
     * IllegalArgumentException if the password doesn’t satisfy the following requirements:
     * At least 1 upper-case letter.
     * At least 1 number.
     * At least 1 special character (!@#$%^&*)
     * At least 1 lower-case letter.
     *
     * @param initPassword
     * @throws IllegalArgumentException if the requirements are not satisfied
     */
    public Password(String initPassword) throws IllegalArgumentException {
        if (initPassword.length() < 4) {
            throw new IllegalArgumentException();
        }
            int hasDigits = 0;
            int hasCapitals = 0;
            int hasLowercase = 0;
            int hasSpecialChars = 0;

            for (int i = 0; i < initPassword.length(); i++) {
                if (Character.isDigit((initPassword.charAt(i)))) {
                    hasDigits++;
                } else if (Character.isUpperCase(initPassword.charAt(i))) {
                    hasCapitals++;
                } else if (Character.isLowerCase(initPassword.charAt(i))) {
                    hasLowercase++;
                } else if (initPassword.charAt(i) == ('!') || initPassword.charAt(i) == ('@') || initPassword.charAt(i) == ('#') || initPassword.charAt(i) == ('$') ||
                        initPassword.charAt(i) == ('%') || initPassword.charAt(i) == ('^') || initPassword.charAt(i) == ('&') || initPassword.charAt(i) == ('*')) {
                    hasSpecialChars++;
                }
            }
        if (hasDigits == 0 || hasCapitals == 0 || hasLowercase == 0 || hasSpecialChars == 0) {
            throw new IllegalArgumentException();
        }
        this.password = initPassword;
    }

    /**
     * Gets the String password associated with the User's account
     * @return String
     */
    public String getPassword() {
        return password;
    }

}
