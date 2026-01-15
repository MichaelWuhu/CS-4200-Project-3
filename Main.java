import java.util.Scanner;

/**
 * CS4200 Project 3: 4-in-a-line (8x8), rows/cols only, no diagonals.
 * X = computer, O = human.
 * Uses alpha-beta pruning with a strict 5-second move limit.
 *
 * Main entry point for the game.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController(scanner);
        controller.playGame();
        scanner.close();
    }
}
