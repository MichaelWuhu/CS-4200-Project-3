import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controls the overall game flow and logic.
 */
public class GameController {
    private final Board board;
    private final Scanner scanner;
    private final List<String> computerMoves;
    private final List<String> humanMoves;

    public GameController(Scanner scanner) {
        this.board = new Board();
        this.scanner = scanner;
        this.computerMoves = new ArrayList<>();
        this.humanMoves = new ArrayList<>();
    }

    /**
     * Run the main game loop.
     */
    public void playGame() {
        GameUI.printWelcome();
        boolean humanFirst = askWhoFirst();

        GameUI.printBoard(board);
        GameUI.printMoveList(computerMoves, humanMoves);

        boolean humanTurn = humanFirst;

        while (true) {
            if (humanTurn) {
                playHumanMove();
            } else {
                playComputerMove();
            }

            GameUI.printBoard(board);
            GameUI.printMoveList(computerMoves, humanMoves);

            // Check for win or draw
            int winner = board.checkWinner();
            if (winner == 1) {
                System.out.println("I win");
                return;
            } else if (winner == -1) {
                System.out.println("you win");
                return;
            } else if (board.isFull()) {
                System.out.println("draw");
                return;
            }

            humanTurn = !humanTurn;
        }
    }

    /**
     * Ask the player who should move first.
     */
    private boolean askWhoFirst() {
        while (true) {
            System.out.print("Who moves first? Enter 1 for Human (O), 2 for Computer (X): ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                return true;
            }
            if (input.equals("2")) {
                return false;
            }
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }

    /**
     * Handle a human player move.
     */
    private void playHumanMove() {
        while (true) {
            System.out.print("Enter your move (e.g., e5): ");
            String input = scanner.nextLine().trim();

            int[] rc = MoveValidator.parseMove(input);
            if (rc == null) {
                System.out.println("Invalid format. Use a letter A-H and a number 1-8 (e.g., e5).");
                continue;
            }

            if (!board.inBounds(rc[0], rc[1])) {
                System.out.println("Out of bounds. Try again.");
                continue;
            }

            if (!board.isEmpty(rc[0], rc[1])) {
                System.out.println("Illegal move: cell not empty. Try again.");
                continue;
            }

            // Legal move
            board.setCell(rc[0], rc[1], GameConstants.HUMAN);
            humanMoves.add(MoveValidator.toMoveString(rc[0], rc[1]));
            return;
        }
    }

    /**
     * Handle a computer player move.
     */
    private void playComputerMove() {
        System.out.println("Computer is thinking (<= 5 seconds)...");
        int[] best = AIPlayer.computeBestMove(board);

        if (best == null) {
            System.out.println("No legal moves remain.");
            return;
        }

        board.setCell(best[0], best[1], GameConstants.COMPUTER);
        String moveStr = MoveValidator.toMoveString(best[0], best[1]);
        computerMoves.add(moveStr);
        System.out.println("Computer move: " + moveStr);
    }
}
