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
    private int thinkingTimeSeconds;

    public GameController(Scanner scanner) {
        this.board = new Board();
        this.scanner = scanner;
        this.computerMoves = new ArrayList<>();
        this.humanMoves = new ArrayList<>();
        this.thinkingTimeSeconds = 5; // default
    }

    /**
     * Run the main game loop.
     */
    public void playGame() {
        GameUI.printWelcome();
        boolean humanFirst = askWhoFirst();
        askThinkingTime();

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
            System.out.print("Would you like to go first? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Invalid choice. Please enter y or n.");
        }
    }

    /**
     * Ask how long the computer should think.
     */
    private void askThinkingTime() {
        while (true) {
            System.out.print("How long should the computer think about its moves (in seconds)?: ");
            String input = scanner.nextLine().trim();
            try {
                int seconds = Integer.parseInt(input);
                if (seconds > 0 && seconds <= 60) {
                    this.thinkingTimeSeconds = seconds;
                    return;
                } else {
                    System.out.println("Please enter a number between 1 and 60.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
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
        System.out.println("Computer is thinking (<= " + thinkingTimeSeconds + " seconds)...");
        int[] best = AIPlayer.computeBestMove(board, thinkingTimeSeconds);

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
