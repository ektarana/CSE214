import java.io.FileInputStream;

/**
 * Ekta Rana
 * 111030624
 * HW 6
 * R08
 * Michael Rizzo
 * Tim Zhang
 */

import java.util.*;
import java.io.*;

/**
 * This class will allow the user to interact with the databases by logging in and out of the social network,
 * listing the Users that specific User follows, allowing the User to follow and unfollow other Users, and listing all the Users in that social network.
 * This class should provide the functionality to load the saved (serialized) Bitter or create a new one if a saved social network does not exist.
 */
public class BitterPlatform {
    private static Bitter bitter;

    /**
     * Implement the login and menu options specified in the UI required functions as well as allow the user to interact with the social network.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String selection = "";
        String email = "";
        String name = "";
        String pass = "";
        boolean loggedIn = false;
        boolean run = true;
        UserDatabase userDatabase = new UserDatabase();
        AccountDatabase accountDatabase = new AccountDatabase();
        User currentUser = null;
        Account currentAccount = null;

        System.out.println("Hello, and welcome to Bitter, a more tasteful social network.");
        try {
            FileInputStream file = new FileInputStream("Bitter.ser");
            ObjectInputStream inStream = new ObjectInputStream(file);
            bitter = (Bitter) inStream.readObject();
            inStream.close();
        } catch (Exception e) {
            System.out.println("No previous data found.");
            bitter = new Bitter(userDatabase, accountDatabase);
        }

        while (run) {
            if (!loggedIn) {
                System.out.println("You are not logged in.");
            }
            loginMenu();
            System.out.println("Please select an option: ");
            selection = input.nextLine();
            selection = selection.toUpperCase();

            switch (selection) {
                case "L":
                    System.out.println("Please enter your email: ");
                    email = input.nextLine();
                    System.out.println("Please enter your password: ");
                    pass = input.nextLine();

                    if (!bitter.getUsers().containsKey(email)) {
                        System.out.println("There is no account associated with this email! Make sure you sign up first.");
                        break;
                    } else {
                        if (pass.equals(bitter.getAccounts().getAccountInformation(email).getPassword().getPassword())) {
                            loggedIn = true;
                        }
                        currentUser = bitter.getUsers().getUser(email);
                        currentAccount = bitter.getAccounts().getAccountInformation(email);
                    }
                    break;
                case "S":
                    System.out.println("Please enter your email: ");
                    email = input.nextLine();

                    System.out.println("Please enter your name: ");
                    name = input.nextLine();

                    boolean isSecure = false;
                    Password password = null;

                    while (!isSecure) {
                        System.out.println("Please enter a password: ");
                        pass = input.nextLine();
                        try {
                            password = new Password(pass);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Your password is not secure enough. Please make sure you have at least one upper case and one lower case letter, one special character, and one number.");
                            continue;
                        }
                        isSecure = true;
                    }

                    User user = new User(name, email);
                    Account account = new Account(name, password);

                    try {
                        bitter.addUser(email, user, account);
                        currentAccount = bitter.getAccounts().getAccountInformation(email);
                        currentUser = bitter.getUsers().getUser(email);
                        loggedIn = true;
                        System.out.println("Thanks for signing up! Welcome to Bitter!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("oops! something went wrong with your sign up. You may already have an account!  ");
                    }

                    break;
                case "Q":
                    System.out.println("Shutting down...");
                    try {
                        FileOutputStream file = new FileOutputStream("Bitter.ser");
                        ObjectOutputStream outStream = new ObjectOutputStream(file);
                        outStream.writeObject(bitter);
                        outStream.close();
                        run = false;
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                    System.exit(0);
                default:
                    System.out.println("Invalid input.");
            }
            while (loggedIn) {
                userMenu();
                System.out.println("Please select an option: ");
                selection = input.nextLine();
                selection = selection.toUpperCase();

                switch (selection) {
                    case "F":
                        System.out.println("Please enter the email of the person you would like to follow: ");
                        email = input.nextLine();

                        User userAcctToFollow = bitter.getUsers().getUser(email);
                        Account acctToFollow = bitter.getAccounts().getAccountInformation(email);

                        if (bitter.getUsers().containsKey(email)) {
                            acctToFollow.getFollowers().add(currentUser); // adds the currentUser to the list of followers of the account associated with the entered email
                            currentAccount.getFollowing().add(userAcctToFollow); //adds the entered email to the list of accounts being followed

                            System.out.println(bitter.getUsers().getUser(email).getName() + " has been successfully followed. ");
                        } else {
                            System.out.println("This user cannot be found!!");
                        }
                        break;
                    case "U":
                        if (currentAccount.getFollowing() == null) {
                            System.out.println("You don't follow any accounts yet, so you can't unfollow any accounts!");
                        } else {

                            System.out.println("Please enter the email of the person you would like to unfollow: ");
                            email = input.nextLine();


                            User userAcctToUnfollow = bitter.getUsers().getUser(email);
                            Account acctToUnfollow = bitter.getAccounts().getAccountInformation(email);

                            if (currentAccount.getFollowing().contains(userAcctToUnfollow)) {
                                currentAccount.getFollowing().remove(userAcctToUnfollow); //you unfollow them
                                acctToUnfollow.getFollowers().remove(currentUser); //you are supposed to be removed from their list of followers
                                System.out.println(acctToUnfollow.getName() + " unfollowed.");
                            }
                        }
                        break;
                    case "V":
                        if (currentAccount.getFollowers().isEmpty()) {
                            System.out.println("you have no followers!");
                        } else {
                            System.out.println("Your followers: ");
                            System.out.println("Email                               Name");
                            System.out.println("----------------------------------------------------------------------------");

                            for (int i = 0; i < currentAccount.getFollowers().size(); i++) {
                                System.out.printf("%-30s%-26s", currentAccount.getFollowers().get(i).getEmail(), currentAccount.getFollowers().get(i).getName());
                                System.out.println();
                            }
                        }
                        break;
                    case "S":
                        if (currentAccount.getFollowing() == null) {
                            System.out.println("You don't follow any accounts yet!");
                        } else {
                            System.out.println("You follow: ");
                            System.out.println("Email                               Name");
                            System.out.println("----------------------------------------------------------------------------");

                            for (int i = 0; i < currentAccount.getFollowing().size(); i++) {
                                System.out.printf("%-30s%-26s",currentAccount.getFollowing().get(i).getEmail(), currentAccount.getFollowing().get(i).getName());
                                System.out.println();
                            }
                        }
                        break;
                    case "A":
                        bitter.getUsers().printData();
                        break;
                    case "L":
                        currentAccount = null;
                        currentUser = null;
                        loggedIn = false;
                        break;
                    case "C":
                        if (!currentAccount.getFollowing().isEmpty()) { //if there are people I follow
                            //go to the first person in the list of people I follow and remove me from their list of followers
                            for (int i = 0; i < currentAccount.getFollowing().size(); i++) {
                                bitter.getAccounts().getAccountInformation(currentAccount.getFollowing().get(i).getEmail()).getFollowers().remove(currentUser);
                            }
                        }
                        if (!currentAccount.getFollowers().isEmpty()) { //if there are people that follow me
                            //go to the first user in the people that follow me and remove me from their list of following
                            for (int i = 0; i < currentAccount.getFollowers().size(); i++) {
                                bitter.getAccounts().getAccountInformation(currentAccount.getFollowers().get(i).getEmail()).getFollowing().remove(currentUser);
                            }
                        }
                        System.out.println(currentUser.getName() + "'s account has been closed.");

                        bitter.getAccounts().removeAccount(email);
                        bitter.getUsers().removeUser(email);
                        loggedIn = false;
                        currentAccount = null;
                        currentUser = null;

                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
        }

    }

    /**
     * prints the list of menu options the user has when opening Bitter
     */
    public static void loginMenu() {
        System.out.println("Login Menu:");
        System.out.println();
        System.out.println("L)Login");
        System.out.println("S)Sign Up");
        System.out.println("Q)Quit.");
    }

    /**
     * prints the list of menu options the user has after signing in
     */
    public static void userMenu() {
        System.out.println("User Menu:");
        System.out.println();
        System.out.println("F)Follow someone");
        System.out.println("U)Unfollow someone");
        System.out.println("V)View Followers");
        System.out.println("S)See who you follow.");
        System.out.println("A)List all users.");
        System.out.println("L)Logout.");
        System.out.println("C)Close your account.");
        System.out.println();
    }

}

