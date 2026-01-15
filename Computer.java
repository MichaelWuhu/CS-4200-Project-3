import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * AI player using alpha-beta pruning with iterative deepening.
 */
public class Computer {

    /**
     * Find the best move for the computer using alpha-beta search.
     * Returns null if no legal moves exist.
     */
    public static int[] computeBestMove(Board board, int thinkingTimeSeconds) {
        long start = System.nanoTime();
        long deadline = start + (thinkingTimeSeconds * 1_000_000_000L);

        List<int[]> moves = generateMoves(board);
        if (moves.isEmpty()) {
            return null;
        }

        int[] bestMove = moves.get(0);
        int bestScore = Integer.MIN_VALUE;

        // Iterative deepening: search at depths 5 through 12
        // First completed search is >= 5 plies
        for (int depth = GameConstants.MIN_SEARCH_DEPTH; depth <= GameConstants.MAX_SEARCH_DEPTH; depth++) {
            if (System.nanoTime() >= deadline) {
                break;
            }

            int localBestScore = Integer.MIN_VALUE;
            int[] localBestMove = bestMove;

            // Root search: maximize for computer
            for (int[] mv : moves) {
                if (System.nanoTime() >= deadline) {
                    break;
                }

                board.setCell(mv[0], mv[1], GameConstants.COMPUTER);
                int score = alphaBeta(board, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false, deadline);
                board.setCell(mv[0], mv[1], GameConstants.EMPTY);

                if (score > localBestScore) {
                    localBestScore = score;
                    localBestMove = mv;
                }
            }

            // Only accept results if no time out during this depth
            if (System.nanoTime() < deadline) {
                bestScore = localBestScore;
                bestMove = localBestMove;
            } else {
                break;
            }
        }

        return bestMove;
    }

    /**
     * Alpha-beta pruning algorithm.
     * Maximizing = true when searching for computer's best move.
     */
    private static int alphaBeta(Board board, int depth, int alpha, int beta, boolean maximizing, long deadline) {
        // Check time limit (cut-off test)
        if (System.nanoTime() >= deadline) {
            return GameEvaluator.evaluate(board);
        }

        // Terminal states
        int winner = board.checkWinner();
        if (winner == 1) {
            return GameConstants.TERMINAL_WIN_COMPUTER;
        }
        if (winner == -1) {
            return GameConstants.TERMINAL_WIN_HUMAN;
        }

        // Depth limit or full board
        if (depth == 0 || board.isFull()) {
            return GameEvaluator.evaluate(board);
        }

        List<int[]> moves = generateMoves(board);

        if (maximizing) {
            // Computer's turn: maximize score
            int best = Integer.MIN_VALUE;
            for (int[] mv : moves) {
                if (System.nanoTime() >= deadline) {
                    break;
                }

                board.setCell(mv[0], mv[1], GameConstants.COMPUTER);
                int score = alphaBeta(board, depth - 1, alpha, beta, false, deadline);
                board.setCell(mv[0], mv[1], GameConstants.EMPTY);

                best = Math.max(best, score);
                alpha = Math.max(alpha, best);
                if (beta <= alpha) {
                    break; // Beta cutoff
                }
            }
            return best;
        } else {
            // Human's turn: minimize score
            int best = Integer.MAX_VALUE;
            for (int[] mv : moves) {
                if (System.nanoTime() >= deadline) {
                    break;
                }

                board.setCell(mv[0], mv[1], GameConstants.HUMAN);
                int score = alphaBeta(board, depth - 1, alpha, beta, true, deadline);
                board.setCell(mv[0], mv[1], GameConstants.EMPTY);

                best = Math.min(best, score);
                beta = Math.min(beta, best);
                if (beta <= alpha) {
                    break; // Alpha cutoff
                }
            }
            return best;
        }
    }

    /**
     * Generate all legal moves on the board.
     * Orders by distance from center to improve pruning effectiveness.
     */
    private static List<int[]> generateMoves(Board board) {
        List<int[]> moves = new ArrayList<>();
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                if (board.isEmpty(r, c)) {
                    moves.add(new int[]{r, c});
                }
            }
        }

        // Sort by center distance: prefer cells closer to center
        moves.sort(Comparator.comparingInt(mv -> centerDistance(mv[0], mv[1])));
        return moves;
    }

    /**
     * Calculate squared distance from center of board.
     * Used for move ordering in alpha-beta search.
     */
    private static int centerDistance(int r, int c) {
        int centerRow = 3;
        int centerCol = 3;
        int dr = r - centerRow;
        int dc = c - centerCol;
        return dr * dr + dc * dc;
    }
}
