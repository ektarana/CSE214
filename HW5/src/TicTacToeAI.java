/**
 * Ekta Rana
 * 111030624
 * HW5
 * section 8
 * Michael Rizzo
 * Tim Zhang
 */

import java.util.Scanner;

/**
 * The Driver class that runs the gameplay
 */
public class TicTacToeAI {
    /**
     * Creates the empty GameTree and initializes which player is going to have which symbol from the Box enum.
     *
     * @param args
     */
    public static void main(String[] args) {
        GameTree tree = new GameTree();
        playGame(tree);
    }

    /**
     * Provides a user interface allowing a player to play against the Tic-Tac-Toe AI represented by tree.
     * This method will allow a user to keep playing the TicTacToe game until either the user wins or loses, or the game ends in a draw.
     * At each turn, you should print out the probability of winning, losing, and drawing from the current configuration.
     * @param tree Takes in a tree to begin the game. The root of this tree contains a blank board
     */
    public static void playGame(GameTree tree) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("This is the board:");
        System.out.println(" |1|2|3|\n |4|5|6|\n |7|8|9|\n");
        GameBoard board = new GameBoard();
        GameBoardNode node = new GameBoardNode(board, Box.X);
        tree.setCursor(tree.buildTree(node, Box.X));

        while (!node.isEnd()){ //while the tree has not moved to a leaf node, keep playing

            System.out.println("Please make a move: ");
            int pos = input.nextInt() - 1;
            try {
                tree.setData(tree.getCursor());
                tree.getCursor().setProbabilities();
                tree.makeMove(pos);
                tree.makeCPUMove(tree.getCursor());
                tree.getCursor().toString();
                tree.getCursor().printProbs();
            }
            catch(IllegalArgumentException e){
                System.out.println("not a valid spot on the board");
            }
        }
    }
}
