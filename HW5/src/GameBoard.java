/**
 * Ekta Rana
 * 111030624
 * HW5
 * section 8
 * Michael Rizzo
 * Tim Zhang
 */

/**
 * The GameBoard is an object containing an array of Box objects which represents the board.
 * The board size is always 9.
 */
public class GameBoard {
    private Box[] board;
    private final int boardSize = 9;

    /**
     * Returns the array of Box objects which represents the board.
     * @return the Box[] of the current state of the board
     */
    public Box[] getBoard() {
        return board;
    }

    /**
     * The default constructor for the GameBoard class. This constructor initializes the array of Boxes to size boardSize.
     */
    public GameBoard() {
        board = new Box[boardSize];
        for (int i= 0; i<boardSize; i++){
            board[i] = Box.EMPTY;
        }
    }

    /**
     * Returns a new GameBoard object that is the exact same as the board it was called on.
     * Used to initially populate the config array with children as copies of the parent
     * @return
     */
    public GameBoard clone(){
        GameBoard clone = new GameBoard();
        for (int i = 0; i<9; i++) {
            clone.board[i] = board[i];
        }
        return clone;
    }

    /**
     * Returns a boolean value true if the board is full and no further moves can be made, or false if the board still has empty boxes for more moves
     * @return a boolean value telling whether or not the board is full
     */

    public boolean isFull(){
        for(int i = 0; i< 9; i++){
            if (this.getBoard()[i] == Box.EMPTY){
                return false;
            }
        }
        return true;
    }
 }