# CS-4200-Project-3: 4-in-a-Line Game

## Project Overview
This is an implementation of a 4-in-a-line game on an 8x8 board using alpha-beta pruning with iterative deepening. The computer (X) plays against a human player (O), searching at least 5 plies deep within a 5-second time limit.

## Features
- **Alpha-beta pruning** with iterative deepening (depths 5-12)
- **5-second move time limit** with cut-off test
- **Evaluation function** for non-terminal states
- **Move ordering** (center-first) to improve pruning efficiency
- **Illegal move detection** (invalid format, out of bounds, occupied cell)
- **Standard move notation** (e.g., "e5" = row E, column 5)
- Rows and columns only (no diagonal wins)

## File Structure
- `Main.java` - Entry point for the game
- `GameController.java` - Main game loop and user interaction
- `Board.java` - Board state management and win detection
- `AIPlayer.java` - Alpha-beta search with iterative deepening
- `GameEvaluator.java` - Position evaluation function
- `GameUI.java` - Display formatting (board and move list)
- `MoveValidator.java` - Move parsing and validation
- `GameConstants.java` - Game constants and configuration

## How to Compile
```bash
javac *.java
```

## How to Run
```bash
java Main
```

## How to Play
1. The program will ask who moves first (1 for Human, 2 for Computer)
2. Enter moves using the format: `<row><column>` (e.g., `e5`, `d4`)
   - Rows: A-H (case insensitive)
   - Columns: 1-8
3. The board and move history will be displayed after each move
4. Win by getting 4 pieces in a row or column (no diagonals)

## Example Move Notation
```
    1 2 3 4 5 6 7 8     Computer vs. Human
  A - - - - - - - -        1. e5  d5
  B - - - - - - - -        2. e4  e3
  C - - - - - - - -        3. f4  d4
  D - - - O X - - -        ...
  E - - X X O - - -
  F - - - - - - - -
  G - - - - - - - -
  H - - - - - - - -
```

## Algorithm Details

### Alpha-Beta Pruning
The AI uses alpha-beta pruning to efficiently search the game tree:
- **Iterative deepening**: Searches from depth 5 to 12
- **Time management**: Returns best move found before 5-second deadline
- **Move ordering**: Prioritizes center positions for better pruning

### Evaluation Function
Evaluates non-terminal positions by scoring all 4-piece windows:
- **4 pieces**: ±1,000,000 (terminal win)
- **3 pieces**: ±2,000
- **2 pieces**: ±100
- **1 piece**: ±10
- Mixed or empty segments: 0

Windows containing both players are ignored. Higher scores favor the computer (X).