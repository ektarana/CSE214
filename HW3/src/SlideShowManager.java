/**
 * Ekta Rana
 * 111030624
 * HW#3
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */

import java.util.*;

/**
 * the driver class, containing the main method
 */
public class SlideShowManager {
    public static UndoRedoStack undoStack = new UndoRedoStack();
    public static UndoRedoStack redoStack = new UndoRedoStack();
    public static ArrayList<String> slideshow = new ArrayList<>();

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String selection, photo;
        int pos1;
        int pos2;
        boolean run = true;

        System.out.println("Welcome to Slideshow Manager!");

        while (run) {
            printMenu();
            System.out.println("Please select an option: ");
            selection = input.nextLine().toUpperCase();

            try {
                switch (selection) {
                    case "A":
                        System.out.println("Please enter the photo name: ");
                        photo = input.nextLine();
                        System.out.println("Please enter the position: ");
                        pos1 = input.nextInt() - 1;
                        input.nextLine();
                        while (pos1 < 0 || pos1 > slideshow.size()) {
                            System.out.println("Invalid input, try again. ");
                            System.out.println("Please enter the position: ");
                            pos1 = input.nextInt() - 1;
                        }
                        input.nextLine();
                        ActionCommand aCom = new ActionCommand(ActionType.ADD);
                        aCom.setPhoto(photo);
                        aCom.setPositionOne(pos1);
                        aCom.perform(slideshow);
                        undoStack.pusH(aCom);
                        redoStack.clear();
                        break;

                    case "R":
                        System.out.println("Please enter the position: ");
                        pos1 = input.nextInt() - 1;
                        input.nextLine();
                        if (slideshow.isEmpty()) {
                            System.out.println("The slideshow is empty");
                        } else {
                            while (pos1 < 0 || pos1 > slideshow.size()) {
                                System.out.println("Invalid input, try again. ");
                                System.out.println("Please enter the position: ");
                                pos1 = input.nextInt() - 1;
                            }
                            input.nextLine();
                            ActionCommand rCom = new ActionCommand(ActionType.REMOVE);
                            rCom.setPhoto(slideshow.get(pos1));
                            rCom.setPositionOne(pos1);
                            rCom.perform(slideshow);
                            undoStack.pusH(rCom);
                            redoStack.clear();
                        }
                        break;

                    case "S":
                        System.out.println("Please enter the first position: ");
                        pos1 = input.nextInt() - 1;
                        System.out.println("Please enter the second position: ");
                        pos2 = input.nextInt() - 1;
                        input.nextLine();
                        if (slideshow.isEmpty()) {
                            System.out.println("The slideshow is empty");
                        } else if (slideshow.size() == 1) {
                            System.out.println("You can't swap any images in a slideshow of 1. Sorry!");
                        } else {
                            while (pos1 < 0 || pos1 > slideshow.size() || pos2 < 0 || pos2 > slideshow.size()) {
                                System.out.println("Invalid input, try again. ");
                                System.out.println("Please enter the first position: ");
                                pos1 = input.nextInt() - 1;
                                System.out.println(" Please enter the second position: ");
                                pos2 = input.nextInt() - 1;
                            }
                            input.nextLine();
                            ActionCommand sCom = new ActionCommand(ActionType.SWAP);
                            sCom.setPositionOne(pos1);
                            sCom.setPositionTwo(pos2);
                            sCom.perform(slideshow);
                            undoStack.pusH(sCom);
                            redoStack.clear();
                        }
                        break;

                    case "M":
                        System.out.println("Please enter the source position: ");
                        pos1 = input.nextInt() - 1;
                        System.out.println("Please enter the destination position: ");
                        pos2 = input.nextInt() - 1;
                        input.nextLine();
                        if (slideshow.isEmpty()) {
                            System.out.println("The slideshow is empty");
                        } else if (slideshow.size() == 1) {
                            System.out.println("You can't move any images in a slideshow of 1. Sorry!");
                        } else {
                            while (pos1 < 0 || pos1 > slideshow.size() || pos2 < 0 || pos2 > slideshow.size()) {
                                System.out.println("Invalid input, try again. ");
                                System.out.println("Please enter the source position: ");
                                pos1 = input.nextInt() - 1;
                                System.out.println(" Please enter the destination position: ");
                                pos2 = input.nextInt() - 1;
                            }
                            input.nextLine();
                            ActionCommand mCom = new ActionCommand(ActionType.MOVE);
                            mCom.setPositionOne(pos1);
                            mCom.setPositionTwo(pos2);
                            mCom.perform(slideshow);
                            undoStack.pusH(mCom);
                            redoStack.clear();
                        }
                        break;

                    case "P":
                        print();
                        break;

                    case "Z":
                        if (undoStack.isEmpty()) {
                            System.out.println("There's nothing to undo!");
                        } else {
                            redoStack.push(undoStack.peek()); //saves the action command at the top of the undoStack to the redoStack
                            switch (undoStack.peek().getType()) {
                                case ADD:
                                    System.out.println("Removed " + undoStack.peek().getPhoto() + " from position " + (undoStack.peek().getPositionOne() + 1));
                                    break;
                                case REMOVE:
                                    System.out.println("Added " + undoStack.peek().getPhoto() + " from position " + (undoStack.peek().getPositionOne() + 1));
                                    break;
                                case SWAP:
                                    System.out.println("Swapped position " + (undoStack.peek().getPositionOne() + 1) + " and position " + (undoStack.peek().getPositionTwo() + 1));
                                    break;
                                case MOVE:
                                    System.out.println("Moved from position " + (undoStack.peek().getPositionOne() + 1) + " to position " + (undoStack.peek().getPositionTwo() + 1));
                                    break;
                            }
                            undoStack.pop().getInverse().perform(slideshow); //pops the first actioncommand from undo and then performs the inverse
                        }
                        break;

                    case "Y":
                        if (redoStack.isEmpty()) {
                            System.out.println("There's nothing to redo!");
                        } else {
                            undoStack.push(redoStack.peek()); //saves the action command at the top of the undostack
                            switch (redoStack.peek().getType()) {
                                case ADD:
                                    System.out.println("Added " + redoStack.peek().getPhoto() + " from position " + (redoStack.peek().getPositionOne() + 1));
                                    break;
                                case REMOVE:
                                    System.out.println("Removed " + redoStack.peek().getPhoto() + " from position " + (redoStack.peek().getPositionOne() + 1));
                                    break;
                                case SWAP:
                                    System.out.println("Swapped position " + (redoStack.peek().getPositionOne() + 1) + " and position " + (redoStack.peek().getPositionTwo() + 1));
                                    break;
                                case MOVE:
                                    System.out.println("Moved from position " + (redoStack.peek().getPositionOne() + 1) + " to position " + (redoStack.peek().getPositionTwo() + 1));
                                    break;
                            }
                            redoStack.pop().perform(slideshow); //pops the first actioncommand from undo and then performs the inverse
                        }
                        break;

                    case "Q":
                        System.out.println("Thank you, and goodbye!");
                        run = false;
                        break;

                    default:
                        System.out.println("Error, try again. ");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }
        }

    }//closes the main

    /**
     * the print method that displays the elements in the slideshow ArrayList, the undoStack, and the redoStack
     * to print the undoStack and redoStack, a new stack, printStack is instantiated, which inverts the stack the user desires to print and saves it
     * this ensures that the original stack can be returned to its original state
     * Preconditions
     * print is called
     * Postconditions
     * three things are printed: the slideshow, the undoStack, and the redoStack
     */
    public static void print() {
        System.out.println("Slideshow:");
        System.out.println("---------------------------------------------------------------------------");
        for (int i = 0; i < slideshow.size(); i++) {
            System.out.println((i + 1) + ". " + slideshow.get(i));
        }
        System.out.println();

        //print the undoStack
        System.out.println("Undo Stack:");
        if (undoStack.isEmpty()) {
            System.out.println("[empty]");
        }
        UndoRedoStack printStack = new UndoRedoStack(); //creates an inverted stack to help print
        while (!undoStack.isEmpty()) {
            switch (undoStack.peek().getType()) {
                case ADD:
                    System.out.println(undoStack.peek().getType() + " " + undoStack.peek().getPhoto() + " in position " + (undoStack.peek().getPositionOne() + 1)); //use peek to print the top of the stack > the inverted print stack prints in order
                    break;
                case REMOVE:
                    System.out.println(undoStack.peek().getType() + " " + undoStack.peek().getPhoto() + " in position " + (undoStack.peek().getPositionOne() + 1));
                    break;
                case SWAP:
                    System.out.println(undoStack.peek().getType() + " position " + (undoStack.peek().getPositionOne() + 1) + " and position " + (undoStack.peek().getPositionTwo() + 1));
                    break;
                case MOVE:
                    System.out.println(undoStack.peek().getType() + " position " + (undoStack.peek().getPositionOne() + 1) + " to position " + (undoStack.peek().getPositionTwo() + 1));
                    break;
            }
            printStack.pusH(undoStack.pop()); //pushes ActionCommand items to the printStack by popping them from the undoStack
        }
        while (!printStack.isEmpty()) {
            undoStack.pusH(printStack.pop()); // pop from print stack and push to undoStack
        }

        System.out.println();

        //print the redoStack
        System.out.println("Redo Stack:");
        if (redoStack.isEmpty()) {
            System.out.println("[empty]");
        }
        while (!redoStack.isEmpty()) {
            switch (redoStack.peek().getType()) {
                case ADD:
                    System.out.println(redoStack.peek().getType() + " " + redoStack.peek().getPhoto() + " in position " + (redoStack.peek().getPositionOne() + 1)); //use peek to print the top of the stack > the inverted print stack prints in order
                    break;
                case REMOVE:
                    System.out.println(redoStack.peek().getType() + " " + redoStack.peek().getPhoto() + " in position " + (redoStack.peek().getPositionOne() + 1));
                    break;
                case SWAP:
                    System.out.println(redoStack.peek().getType() + " position " + (redoStack.peek().getPositionOne() + 1) + " and position " + (redoStack.peek().getPositionTwo() + 1));
                    break;
                case MOVE:
                    System.out.println(redoStack.peek().getType() + " position " + (redoStack.peek().getPositionOne() + 1) + " to position " + (redoStack.peek().getPositionTwo() + 1));
                    break;
            }

            printStack.pusH(redoStack.pop()); //pushes ActionCommand items to the printStack by popping them from the undoStack

        }
        while (!printStack.isEmpty()) {
            redoStack.pusH(printStack.pop()); // pop from print stack and push to undoStack
        }
        System.out.println();
    }

    /**
     * simply prints the menu options to the console
     */
    public static void printMenu() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("A) Add a photo");
        System.out.println("R) Remove a photo");
        System.out.println("S) Swap photos");
        System.out.println("M) Move photo");
        System.out.println("P) Print slideshow and stacks");
        System.out.println("Z) Undo");
        System.out.println("Y) Redo");
        System.out.println("Q) Quit");
    }
}