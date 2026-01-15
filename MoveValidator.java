import java.util.Locale;

/**
 * Handles move parsing and validation.
 */
public class MoveValidator {

    /**
     * Parse a move string like "e5" to coordinates [row, col].
     * Returns null if invalid format.
     */
    public static int[] parseMove(String s) {
        if (s == null) return null;
        s = s.trim().toLowerCase(Locale.ROOT);
        
        if (s.length() < 2 || s.length() > 3) return null;

        char rowCh = s.charAt(0);
        if (rowCh < 'a' || rowCh > 'h') return null;

        String numPart = s.substring(1);
        int col;
        try {
            col = Integer.parseInt(numPart);
        } catch (NumberFormatException e) {
            return null;
        }
        if (col < 1 || col > 8) return null;

        int r = rowCh - 'a';
        int c = col - 1;
        return new int[]{r, c};
    }

    /**
     * Convert row/col coordinates to move string (e.g., "e5").
     */
    public static String toMoveString(int r, int c) {
        char row = (char) ('a' + r);
        int col = c + 1;
        return "" + row + col;
    }

    /**
     * Check if a move is legal on the given board.
     */
    public static boolean isLegalMove(Board board, int r, int c) {
        return board.inBounds(r, c) && board.isEmpty(r, c);
    }
}
