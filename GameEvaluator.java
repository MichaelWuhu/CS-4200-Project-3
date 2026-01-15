/**
 * Evaluates board positions for computer's decisions.
 */
public class GameEvaluator {

    /**
     * Evaluate a board position.
     * Scores all length-4 segments in rows and columns.
     * If a segment contains both players -> 0 contribution.
     * Otherwise contributes based on count of X or O in the segment.
     */
    public static int evaluate(Board board) {
        int score = 0;

        // Evaluate rows
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c <= board.getSize() - GameConstants.WIN_LENGTH; c++) {
                score += evalSegment(board, r, c, 0, 1);
            }
        }

        // Evaluate columns
        for (int c = 0; c < board.getSize(); c++) {
            for (int r = 0; r <= board.getSize() - GameConstants.WIN_LENGTH; r++) {
                score += evalSegment(board, r, c, 1, 0);
            }
        }

        return score;
    }

    /**
     * Evaluate a segment (row or column) of length 4.
     * dr and dc determine direction: (0,1) = row, (1,0) = column.
     */
    private static int evalSegment(Board board, int r, int c, int dr, int dc) {
        int computerCount = 0;
        int humanCount = 0;

        for (int k = 0; k < GameConstants.WIN_LENGTH; k++) {
            char cell = board.getCell(r + k * dr, c + k * dc);
            if (cell == GameConstants.COMPUTER) {
                computerCount++;
            } else if (cell == GameConstants.HUMAN) {
                humanCount++;
            }
        }

        // If both players in segment, no value
        if (computerCount > 0 && humanCount > 0) {
            return 0;
        }

        // If empty segment, no value
        if (computerCount == 0 && humanCount == 0) {
            return 0;
        }

        // Score based on count
        if (computerCount > 0) {
            return scoreForCount(computerCount);
        } else {
            return -scoreForCount(humanCount);
        }
    }

    /**
     * Return score for a given count of pieces in a segment.
     */
    private static int scoreForCount(int count) {
        return switch (count) {
            case 1 -> GameConstants.EVAL_ONE;
            case 2 -> GameConstants.EVAL_TWO;
            case 3 -> GameConstants.EVAL_THREE;
            case 4 -> GameConstants.EVAL_FOUR;
            default -> 0;
        };
    }
}
