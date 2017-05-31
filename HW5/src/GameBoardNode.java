/**
 * Ekta Rana
 * 111030624
 * HW5
 * section 8
 * Michael Rizzo
 * Tim Zhang
 */

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * The GameBoardNode class is a node in the GameTree which contains a GameBoard object and other data fields that must be set.
 * The nodes of a tree require this information to
 */
public class GameBoardNode {
    private GameBoard board;
    private boolean isEnd;
    private Box currentTurn;
    private Box winner;
    //Note: If the game ended in a draw, the value of this variable should be Box.EMPTY.
    private GameBoardNode config[] = new GameBoardNode[9];
    private double winProb;
    private double loseProb;
    private double drawProb;

    /**
     * Returns a GameBoard object within the node
     *
     * @return the GameBoard within the GameBoardNode
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * Returns a boolean that is true if the node is a leaf and false if the node is not a leaf
     *
     * @return whether or not a node is a leaf, as a boolean value
     */
    public boolean isEnd() {
        if (this.getBoard().isFull()) {
            return true;
        }else if(winner != Box.EMPTY){
            return true;
        }
        return false;
    }

    /**
     * allows you to set the isEnd value of a node to true is the node is a leaf
     *
     * @param end takes in a boolean to set isEnd to
     */

    public void setEnd(boolean end) {
        isEnd = end;
    }

    /**
     * allows you to take in a Box object to set node's winner data field to.
     * this lets you know if that node is a game ending node where the end is caused by a win
     *
     * @param winner a Box object
     */
    public void setWinner(Box winner) {
        this.winner = winner;
    }

    /**
     * gets the config array of children of the root node
     *
     * @return an array of GameBoardNode objects that are the possible paths from the current node
     */
    public GameBoardNode[] getConfig() {
        return config;
    }

    /**
     * Returns the probability of the user winning from the current point in the game
     *
     * @return a double value that is equal to the number of winning leaves/number of total leaves in the subtree from this node
     */
    public double getWinProb() {
        return winProb;
    }

    /**
     * allows the user to set the probability of a node winning
     *
     * @param winProb double that the winProb should be set to
     */
    public void setWinProb(double winProb) {
        this.winProb = winProb;
    }

    /**
     * Returns the probability of the user losing from the current game state
     *
     * @return the number of losing leaves/number of total leaves in the subtree from this node as a double
     */
    public double getLoseProb() {
        return loseProb;
    }

    /**
     * Allows you to set the probability of the player losing
     *
     * @param loseProb takes in a double to set loseProb to
     */
    public void setLoseProb(double loseProb) {
        this.loseProb = loseProb;
    }

    /**
     * Returns the probability of the game ending in a draw from the current game
     *
     * @return the number of draw leaves/number of total leaves in the subtree from this node as a double
     */
    public double getDrawProb() {
        return drawProb;
    }

    /**
     * Allows you to set the probability of the game ending in a tie
     *
     * @param drawProb takes in a double to set drawProb to
     */
    public void setDrawProb(double drawProb) {
        this.drawProb = drawProb;
    }

    /**
     * The default constructor for the GameBoardNode class.
     * The configurations of the GameBoard (aka the config references)
     * should be created based on the configuration of the board as well as whose turn it currently is.
     * The value of currentTurn will determine which extra Box will be filled in.
     * Preconditions:
     * currentTurn != Box.EMPTY
     * board is not null.
     *
     * @param board
     * @param currentTurn
     */
    public GameBoardNode(GameBoard board, Box currentTurn) {
        this.board = board;
        this.isEnd = false;
        this.currentTurn = currentTurn;
        this.winner = Box.EMPTY;
    }

    /**
     * Sets all probabilities of winning, losing and drawing from the current configuration of the GameBoard.
     * This method sets the variables winProb, loseProb, and drawProb to their respective probabilities.
     */
    public void setProbabilities() {
//        if (isEnd()) {
//            leaves++;
//            if (this.isEnd() && ((GameTree.checkWin(this) == Box.O  || GameTree.checkWin(this) == Box.X))) {
//                wins++;
//            } else if (this.isEnd() && (GameTree.checkWin(this) == null || GameTree.checkWin(this) != Box.EMPTY)) {
//                draws++;
//            }
//        }else{
//            for (int i = 0; i<9; i++){
//                if(config[i] != null) {
//                    config[i].setProbabilities();
//                    wins += config[i].getWins();
//                    leaves += config[i].getLeaves();
//                    draws += config[i].getDraws();
//                }
//            }
//        }
//
//        setWinProb(wins / leaves);
//        setLoseProb((leaves-wins) / leaves);
//        setDrawProb(draws / leaves);
        int winningLeaves = countWins(this);
        int loseLeaves = countLosses(this);
        int drawLeaves = countDraws(this);
        int totalLeaves = winningLeaves + loseLeaves + drawLeaves;

        setWinProb((double) (winningLeaves / totalLeaves));
        setLoseProb((double) (loseLeaves / totalLeaves));
        setDrawProb((double) (drawLeaves / totalLeaves));
    }

    /**
     * Recursively calculates the number of nodes in the tree resulting in a loss for the player by traversing the GameTree and returns that value as an int
     *
     * @param node takes in a GameBoardNode with losing current game state
     * @return an int representing the number of winning nodes
     */
    public int countLosses(GameBoardNode node) {
        if (node == null) {
            return 0;
        }
        if (this.isEnd() && (GameTree.checkWin(node) == Box.O)) {
            return 1;
        } else {
            return countLosses(node.getConfig()[0]) + countLosses(node.getConfig()[1]) + countLosses(node.getConfig()[2]) +
                    countLosses(node.getConfig()[3]) + countLosses(node.getConfig()[4]) + countLosses(node.getConfig()[5]) +
                    countLosses(node.getConfig()[6]) + countLosses(node.getConfig()[7]) + countLosses(node.getConfig()[8]);
        }
    }

    /**
     * * Recursively calculates the number of nodes in the tree resulting in a win for the player by traversing the GameTree and returns that value as an int
     *
     * @param node takes in a GameBoardNode with the current game state
     * @return an int representing the number of winning nodes
     */
    public int countWins(GameBoardNode node) {
        if (node == null) {
            return 0;
        }
        if (this.isEnd() && (GameTree.checkWin(node) != Box.X)) {
            return 1;
        } else
            return countWins(node.getConfig()[0]) + countWins(node.getConfig()[1]) + countWins(node.getConfig()[2]) +
                    countWins(node.getConfig()[3]) + countWins(node.getConfig()[4]) + countWins(node.getConfig()[5]) +
                    countWins(node.getConfig()[6]) + countWins(node.getConfig()[7]) + countWins(node.getConfig()[8]);
    }

    /**
     * * Recursively calculates the number of nodes in the tree resulting in a draw by traversing the GameTree and returns that value as an int
     *
     * @param node takes in a GameBoardNode with the current game state
     * @return an int representing the number of tied nodes
     */
    public int countDraws(GameBoardNode node) {
        if (node == null) {
            return 0;
        }
        if (this.isEnd() && (GameTree.checkWin(node) != Box.O || GameTree.checkWin(node) != Box.X)) {
            return 1;
        } else
            return countDraws(node.getConfig()[0]) + countDraws(node.getConfig()[1]) + countDraws(node.getConfig()[2]) +
                    countDraws(node.getConfig()[3]) + countDraws(node.getConfig()[4]) + countDraws(node.getConfig()[5]) +
                    countDraws(node.getConfig()[6]) + countDraws(node.getConfig()[7]) + countDraws(node.getConfig()[8]);

    }

    /**
     * Prints the player's probabilities of winning, losing, and drawing at the current game state
     */
    public void printProbs() {
        System.out.print("the probability of a win is:");
        System.out.printf("%.2f%", getWinProb());
        System.out.println();
        System.out.print("the probability of a draw is:");
        System.out.printf("%.2f%", getDrawProb());
        System.out.println();
        System.out.print("the probability of a loss is:");
        System.out.printf("%.2f%", getLoseProb());
        if (winProb == 1) {
            System.out.println("The winner is: " + currentTurn);
        } else if (drawProb == 1) {
            System.out.println("It's a draw.");
        }
    }

    /**
     * Creates and prints the String representation of the GameBoard configuration in the current GameBoardNode.
     *
     * @return String after the GameBoard has been printed
     */
    public String toString() {
        System.out.println();
        for (int i = 0; i < board.getBoard().length; i++) {
            if (i == 3 || i == 6) {
                System.out.println();
            }
            if (board.getBoard()[i] == Box.EMPTY) {
                System.out.print("|_");
            } else if (board.getBoard()[i] == Box.O) {
                System.out.print("|O");

            } else if (board.getBoard()[i] == Box.X) {
                System.out.print("|X");
            }
        }
        return "";
    }
}