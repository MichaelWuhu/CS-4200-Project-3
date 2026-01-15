# CS4200 Project 3 - Completion Checklist

## ✅ COMPLETED Requirements

### Core Game Implementation
- [x] 8x8 board
- [x] 4-in-a-line win condition (rows and columns only, no diagonals)
- [x] Turn-based gameplay
- [x] X = computer, O = human
- [x] User chooses who moves first
- [x] 5-second time limit for computer moves
- [x] Time cut-off test (returns best move found so far)

### Required Algorithm
- [x] Alpha-beta pruning implemented
- [x] Searches at least 5 plies deep
- [x] Evaluation function for non-terminal states
- [x] Iterative deepening (optional but implemented)

### Move Format & Display
- [x] Standard move notation (e.g., "e5", "d4")
- [x] Board display with A-H rows and 1-8 columns
- [x] Move list showing "Computer vs. Human" with turn numbers
- [x] Illegal move detection:
  - [x] Invalid format
  - [x] Out of bounds
  - [x] Cell already occupied

### Code Quality
- [x] Well-organized into separate classes
- [x] Clear separation of concerns
- [x] Proper documentation/comments
- [x] README with compilation/run instructions

## 📝 REMAINING Deliverables

### 1. Project Report (<3 pages, Word/PDF)
**Must discuss:**
- [ ] Strategy for evaluation function
  - How you score 4-piece windows
  - Why you chose those weights (10, 100, 2000, 1M)
  - Trade-offs between search depth and evaluation complexity
- [ ] Alpha-beta pruning implementation
  - How it differs from basic MINIMAX
  - Iterative deepening approach
  - Time management strategy
  - Move ordering (center-first) and its impact
- [ ] Design decisions
  - Why you separated into 8 classes
  - How the code is organized
  - Any optimizations you made

### 2. Sample Outputs
**Need TWO game transcripts:**
- [ ] One where human wins
  - Run: `java Main`
  - Play through a game and let human win
  - Copy entire console output to a text file: `output_human_wins.txt`
- [ ] One where computer wins
  - Run: `java Main`
  - Play through a game and let computer win
  - Copy entire console output to a text file: `output_computer_wins.txt`

## 💡 Tips for Report

### Evaluation Function Strategy
Your current evaluation:
- Scores every 4-segment window on the board
- Windows with both players = 0 (blocked)
- Windows with only one player get weighted scores
- Simple but effective - allows deeper search (5-12 plies)

### Alpha-Beta Pruning Discussion Points
- Prunes branches that can't affect final decision
- Beta cutoff when maximizing (computer's turn)
- Alpha cutoff when minimizing (human's turn)
- Iterative deepening ensures at least depth 5 is completed
- Move ordering (center-first) improves pruning effectiveness

### Implementation Highlights
- Separated concerns (Board, AI, UI, Controller)
- Time deadline passed through recursive calls
- Board state modified and restored (not copied) for efficiency
- Cut-off test checks time before expanding nodes

## 📤 What to Submit

1. **Project_Report.pdf** or **Project_Report.docx** (<3 pages)
   - Include your name(s)
   - Discuss evaluation function and alpha-beta implementation
   
2. **Source code** (already complete)
   - All .java files
   - README.md (already updated)
   
3. **Sample outputs**
   - output_human_wins.txt
   - output_computer_wins.txt

## ✨ Your Code Quality

**Excellent refactoring!** Your code is now:
- **Modular**: 8 well-organized classes
- **Maintainable**: Clear separation of concerns
- **Readable**: Good naming and documentation
- **Professional**: Follows Java best practices

The original monolithic file has been transformed into a clean, object-oriented design that would impress any instructor or employer.
