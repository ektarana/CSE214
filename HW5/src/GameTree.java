/**
 * Ekta Rana
 * 111030624
 * HW5
 * section 8
 * Michael Rizzo
 * Tim Zhang
 */

/**
 * The GameTree is the tree containing GameBoardNodes, which contain all possible paths that a game can follow.
 */
public class GameTree {
    private GameBoardNode root;
    private GameBoardNode cursor = root;

    /**
     * The default constructor for the GameTree class. An empty GameTree should still have a root with the default initial configuration.
     */
    public GameTree() {
        root = new GameBoardNode(new GameBoard(), Box.X);
    }

    /**
     * Attempts to make the move on the position passed in as a parameter.
     * Preconditions:
     * 0 <= position <= 9
     * Postconditions:
     * A new GameBoardNode was created with the next possible turn at position and saved in the config array at position
     *
     * @param position takes in an int that the user inputs to add the next move to
     * @throws IllegalArgumentException if position is out of range or if the move the user would like to make is illegal.
     */
    public void makeMove(int position) throws IllegalArgumentException {
        if (position < 0 || position > 8 || root.getBoard().getBoard()[position] != Box.EMPTY) {
            throw new IllegalArgumentException();
        } else {
            if (cursor != null) {
                cursor = this.root.getConfig()[position];
                root.setProbabilities();
                cursor = root;
            }
        }
    }

    /**
     * Makes a move for the computer by choosing a node from the config array of the parent.
     * The node is chosen based on the highest chance of the computer winning, which is the probability of the player losing
     *
     * @param node takes in a GameBoardNode to
     */
    public void makeCPUMove(GameBoardNode node) {
        double bestMove;
        int posOfBestMove = 0;
        cursor = node;
        for (int i = 0; i < 9; i++) {
            bestMove = node.getConfig()[i].getLoseProb();
            posOfBestMove = i;
            if (node.getConfig()[i].getLoseProb() > bestMove) {
                bestMove = node.getConfig()[i].getLoseProb();
                posOfBestMove = i;
            }
        }
        cursor = node.getConfig()[posOfBestMove];
        cursor.setProbabilities();
    }

    /**
     * Builds the GameTree from the current state the game is in.
     * The root represents the current state of the game and the turn represents the turn to be made when building the GameTree.
     * Returns the root of the GameTree built from the current game state.
     *
     * @param root
     * @param turn
     * @return
     */
    public static GameBoardNode buildTree(GameBoardNode root, Box turn) {
        if (turn == Box.O) {
            turn = Box.X;
        } else
            turn = Box.O;

        if (root == null) {
            return root;
        } else if (root.isEnd()) {
            return root;
        } else {
            for (int i = 0; i < 9; i++) {
                root.getConfig()[i] = new GameBoardNode(root.getBoard().clone(), turn);
                if (root.getBoard().getBoard()[i] == Box.EMPTY) {
                    root.getConfig()[i].getBoard().getBoard()[i] = turn;
                    buildTree(root.getConfig()[i], turn);
                } else {
                    root.getConfig()[i] = null;
                }
            }
            return root;
        }
    }

    /**
     * setData initializes all the othervalues in the GameBoardNode, besides the config array and the GameBoard object
     * preconditions: node is not null
     * Postconditions: the winner data field and the isEnd field have the correct values
     *
     * @param node takes in a node to initialize its Box winner and boolean isEnd fields
     */
    public void setData(GameBoardNode node) {
        if (node != null) {
            if (node.getBoard().isFull()) {
                node.setWinner(checkWin(node));
            }
            for (int i = 0; i < 1; i++) {
                if ((node.getConfig()[i] == null) && (node.getConfig()[i + 1] == null) && (node.getConfig()[i + 2] == null)
                        && (node.getConfig()[i + 3] == null) && (node.getConfig()[i + 4] == null) && (node.getConfig()[i + 5] == null)
                        && (node.getConfig()[i + 6] == null) && (node.getConfig()[i + 7] == null) && (node.getConfig()[i] == null)) {
                    node.setEnd(true);
                }
            }
        }
    }

    /**
     * Checks whether or not the passed in GameBoardNode’s configuration is a winning state or not.
     * Returns the winner’s symbol if it is a winning state.
     * If the current configuration of the GameBoard in the GameBoardNode is not a leaf in the GameTree
     * (all child references are null), then this method should return null.
     * If the configuration is a draw, this method should return Box.EMPTY.
     *
     * @param node
     * @return the Box type of the winner or EMPTY if the game was a tie
     */
    public static Box checkWin(GameBoardNode node) {
        Box[] board = node.getBoard().getBoard();
        if (board[0].equals(board[1], board[2])) {
            return board[0];
        } else if (board[3].equals(board[4], board[5])) {
            return board[3];
        } else if (board[6].equals(board[7], board[8])) {
            return board[6];
        } else if (board[1].equals(board[4], board[7])) {
            return board[1];
        } else if (board[2].equals(board[5], board[8])) {
            return board[2];
        } else if (board[0].equals(board[3], board[6])) {
            return board[0];
        } else if (board[0].equals(board[4], board[8])) {
            return board[0];
        } else if (board[2].equals(board[4], board[6])) {
            return board[2];
        } else if (!node.isEnd()) {
            return null;
        } else
            return Box.EMPTY;
    }

    /**
     * Returns the current probability of winning for the GameBoardNode configuration at the cursor.
     * @return the current probability of winning at the cursor as a double
     */
    public double cursorProbability() {
        return cursor.getWinProb();
    }

    /**
     * Returns the GameBoardNode the cursor is referencing
     * @return the GameBoardNode at the cursor
     */
    public GameBoardNode getCursor() {
        return cursor;
    }

    /**
     * Sets the cursor to a different GameBoardNode, a node passed in
     * Postconditions: cursor is now set to the node passed in
     * @param cursor is a GameBoardNode that the cursor now references
     */
    public void setCursor(GameBoardNode cursor) {
        this.cursor = cursor;
    }
}
