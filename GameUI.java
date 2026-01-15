import java.util.List;

/**
 * Handles all UI display for the game.
 */
public class GameUI {

    /**
     * Print the game board to console.
     */
    public static void printBoard(Board board) {
        System.out.println();
        System.out.print("    ");
        for (int c = 1; c <= board.getSize(); c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int r = 0; r < board.getSize(); r++) {
            char rowLabel = (char) ('A' + r);
            System.out.print("  " + rowLabel + " ");
            for (int c = 0; c < board.getSize(); c++) {
                System.out.print(board.getCell(r, c) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Print the move list showing computer vs human moves by turn.
     */
    public static void printMoveList(List<String> computerMoves, List<String> humanMoves) {
        System.out.println("    Human vs. Computer");
        int turns = Math.max(computerMoves.size(), humanMoves.size());
        for (int i = 0; i < turns; i++) {
            String hm = (i < humanMoves.size()) ? humanMoves.get(i) : "";
            String cm = (i < computerMoves.size()) ? computerMoves.get(i) : "";
            System.out.printf("    %d. %-3s %-3s%n", (i + 1), hm, cm);
        }
        System.out.println();
    }

    /**
     * Print the welcome message and game instructions.
     */
    public static void printWelcome() {
        System.out.println("CS4200 Project 3: 4-in-a-line (8x8).");
        System.out.println("Win by getting 4 in a line in a ROW or COLUMN (no diagonals).");
        System.out.println("X = computer, O = human.");
        System.out.println();
    }
}
