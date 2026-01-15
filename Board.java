/**
 * Represents the game board state.
 */
public class Board {
    private final char[][] grid;
    private final int size;

    public Board() {
        this.size = GameConstants.BOARD_SIZE;
        this.grid = new char[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = GameConstants.EMPTY;
            }
        }
    }

    /**
     * Copy constructor for creating a deep copy of the board.
     */
    public Board(Board other) {
        this.size = other.size;
        this.grid = new char[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                this.grid[r][c] = other.grid[r][c];
            }
        }
    }

    public char getCell(int r, int c) {
        return grid[r][c];
    }

    public void setCell(int r, int c, char value) {
        grid[r][c] = value;
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(int r, int c) {
        return inBounds(r, c) && grid[r][c] == GameConstants.EMPTY;
    }

    public boolean isFull() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c] == GameConstants.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean inBounds(int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }

    /**
     * Check for a winner: 1 = computer, -1 = human, 0 = none yet.
     * Checks rows and columns only (no diagonals).
     */
    public int checkWinner() {
        // Check rows
        for (int r = 0; r < size; r++) {
            for (int c = 0; c <= size - GameConstants.WIN_LENGTH; c++) {
                char first = grid[r][c];
                if (first == GameConstants.EMPTY) continue;
                boolean match = true;
                for (int k = 1; k < GameConstants.WIN_LENGTH; k++) {
                    if (grid[r][c + k] != first) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return (first == GameConstants.COMPUTER) ? 1 : -1;
                }
            }
        }

        // Check columns
        for (int c = 0; c < size; c++) {
            for (int r = 0; r <= size - GameConstants.WIN_LENGTH; r++) {
                char first = grid[r][c];
                if (first == GameConstants.EMPTY) continue;
                boolean match = true;
                for (int k = 1; k < GameConstants.WIN_LENGTH; k++) {
                    if (grid[r + k][c] != first) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return (first == GameConstants.COMPUTER) ? 1 : -1;
                }
            }
        }

        return 0;
    }
}
