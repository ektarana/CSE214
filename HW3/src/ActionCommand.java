/**
 * Ekta Rana
 * 111030624
 * HW#3
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */

import java.util.ArrayList;

/**
 * wraps an ActionType enum and attaches it to the position it is stored at and the name of the photo
 */
public class ActionCommand {
    private int positionOne; //for add and remove
    private int positionTwo; //for move and swap
    private String photo; //this field may be null if the ActionType is MOVE or SWAP.
    final ActionType type; //(this must be set in the constructor, and cannot be static).

    /**
     * constructs a ActionCommand object
     * Preconditions
     * an ActionCommand object called with the data fields necessary, an ActionType
     * Postconditions
     * an ActionCommand object is created
     *
     * @param type takes in an ActionType object
     */
    public ActionCommand(ActionType type) {
        this.type = type;
    }

    /**
     * returns the ActionType of the ActionCommand the method was called on
     *
     * @return ActionType
     */
    public ActionType getType() {
        return type;
    }

    /**
     * returns the second position of the ActionCommand
     * @return the second position of the ActionCommand
     */
    public int getPositionTwo() {

        return positionTwo;
    }

    /**
     * takes in a value to set the second position of the ActionCommand
     * @param positionTwo
     */
    public void setPositionTwo(int positionTwo) {
        this.positionTwo = positionTwo;
    }

    /**
     * returns the name of the image
     *
     * @return String, the photo name
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * take in a String to set the name of the image
     *
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * returns the first position of the ActionCommandc
     * @return the first position of the ActionCommand
     */
    public int getPositionOne() {
        return positionOne;
    }

    /**
     * takes in a value to set the first position of the ActionCommand
     * @param positionOne
     */
    public void setPositionOne(int positionOne) {
        this.positionOne = positionOne;
    }

    /**
     * takes in an ArrayList object, performs the action on the given slideshow (ArrayList of String) object.
     * Preconditions
     * the slideshow is not empty
     * Postconditions
     * some ActionCommand has ben performed
     * @param slideshow
     */
    public void perform(ArrayList<String> slideshow) {
        //performs the action on the given slideshow (ArrayList of String) object.
        //switch case for the four commands
        switch (type) {
            case ADD:
                slideshow.add(positionOne, photo);
                break;
            case REMOVE:
                slideshow.remove(positionOne);
                break;
            case SWAP:
                String temp = slideshow.get(positionOne);
                slideshow.set(positionOne, slideshow.get(positionTwo));
                slideshow.set(positionTwo, temp);
                break;
            case MOVE:
                temp = slideshow.get(positionOne);
                slideshow.remove(positionOne);
                slideshow.add(positionTwo, temp);
                break;
            default:
                System.out.println("something went really wrong");
                break;
        }
    }

    /**
     * Generates a new action command that would undo this action command (ie: add would generate a remove)
     * Precondtions
     * a valid ActionType must be given
     * Postconditions
     * add returns remove
     * remove returns add
     * move and swap return themselves
     * @return the inverse of the ActionCommand
     */
    public ActionCommand getInverse() {
        ActionCommand iCom = null;
        switch (type) {
            case ADD:
                iCom = new ActionCommand(ActionType.REMOVE);
                iCom.setPhoto(photo);
                iCom.setPositionOne(positionOne);
                break;

            case REMOVE:
                iCom = new ActionCommand(ActionType.ADD);
                iCom.setPositionOne(positionOne);
                iCom.setPhoto(photo);
                break;

            case SWAP: {
                iCom = new ActionCommand(ActionType.SWAP);
                iCom.setPositionOne(positionOne);
                iCom.setPositionTwo(positionTwo);
                break;
            }
            case MOVE: {
                iCom = new ActionCommand(ActionType.MOVE);
                iCom.setPositionOne(positionOne);
                iCom.setPositionTwo(positionTwo);
                break;
            }
        }
        return iCom;
    }

}