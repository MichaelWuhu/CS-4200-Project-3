/**
 * Game constants for 4-in-a-line.
 */
public class GameConstants {
    public static final int BOARD_SIZE = 8;
    public static final int WIN_LENGTH = 4;

    public static final char EMPTY = '-';
    public static final char COMPUTER = 'X';
    public static final char HUMAN = 'O';

    public static final long MOVE_TIME_LIMIT_NANOS = 5_000_000_000L; // 5 seconds

    public static final int MIN_SEARCH_DEPTH = 5;
    public static final int MAX_SEARCH_DEPTH = 12;

    public static final int TERMINAL_WIN_COMPUTER = 1_000_000;
    public static final int TERMINAL_WIN_HUMAN = -1_000_000;

    public static final int EVAL_FOUR = 1_000_000;
    public static final int EVAL_THREE = 2_000;
    public static final int EVAL_TWO = 100;
    public static final int EVAL_ONE = 10;
}
