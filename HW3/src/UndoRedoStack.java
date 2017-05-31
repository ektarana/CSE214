import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Ekta Rana
 * 111030624
 * HW#3
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */

/**
 * the Stack class which extends Java API's Stack class and requires an ActionCommand object.
 */
public class UndoRedoStack extends Stack<ActionCommand> {

    /**
     * Pushes ActionCommand a onto the top of the backing data structure.
     * Preconditions
     * an ActionCommand object, a,  is passed in
     * Postcondition
     * a is pushed to the top of the stack
     *
     * @param a takes in an ActionCommand to add to the top of the stack
     */
    public void pusH(ActionCommand a) {
        addElement(a);
    }

    /**
     * Takes the ActionCommand that is on top of the backing data structure, saves that value, removes that ActionCommand from the backing data structure, and returns that ActionCommand.
     * Preconditions
     * the stack is not already empty
     * Postconditions
     * the element at the top of the stack has been removed
     *
     * @return the value of the ActionCommand that was removed
     * @throws EmptyStackException if the stack is empty and there is nothing to remove
     */
    public ActionCommand pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return super.pop();
    }

    /**
     * Takes the ActionCommand that is on the top of the backing data structure and returns that value to the caller. This method does NOT remove that ActionCommand from the backing data structure.
     * Preconditions
     * the stack cannot be empty
     * Postconditions
     * the ActionCommand at the top of the stack is returned, but not removed or changed in any way
     *
     * @return the ActionCommand on top of the stack
     * @throws EmptyStackException if the stack is empty and there is nothing to remove
     */
    public ActionCommand peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return super.peek();
    }

    /**
     * returns a boolean value, true if the stack is empty; false if the stack contains elements
     * Preconditions
     * called on a stack
     * Postconditions
     * if stack contains elements, false is returned, otherwise, true
     * @return boolean
     */
    public boolean isEmpty() {
        return super.isEmpty();
    }
}