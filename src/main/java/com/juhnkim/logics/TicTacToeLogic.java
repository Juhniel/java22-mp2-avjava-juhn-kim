package com.juhnkim.logics;

import com.juhnkim.interfaces.BoardOperations;
import com.juhnkim.utils.MinMax;
import com.juhnkim.views.Board;

public class TicTacToeLogic implements BoardOperations {
    private final MinMax minMax;
    private final char[][] gameState;

    /**
     * Constructor initializes the game state and MinMax algorithm instance.
     *
     * @param minMax The MinMax algorithm instance.
     */
    public TicTacToeLogic(MinMax minMax) {
        this.minMax = minMax;
        this.gameState = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameState[i][j] = ' ';
            }
        }
    }

    /**
     * Updates the game state with a new mark at a specific row and column.
     *
     * @param mark Mark to be placed ('X' or 'O').
     * @param row  Row index.
     * @param col  Column index.
     */
    public void updateGameState(char mark, int row, int col) {
        gameState[row][col] = mark;
    }

    /**
     * Checks if a move is valid by verifying the cell is empty.
     *
     * @param row Row index.
     * @param col Column index.
     * @return True if the cell is empty, false otherwise.
     */
    @Override
    public boolean isValidMove(int row, int col) {
        return gameState[row][col] == ' ';
    }

    /**
     * Places a mark ('X' or 'O') on the game board at the specified row and column.
     *
     * @param mark Mark to be placed.
     * @param row  Row index.
     * @param col  Column index.
     */
    @Override
    public void placeMark(char mark, int row, int col) {
        gameState[row][col] = mark;
    }

    /**
     * Determines if there is a winner and returns an integer score accordingly.
     *
     * @return 10 if 'X' wins, -10 if 'O' wins, or 0 if no winner.
     */
    @Override
    public int isWinner() {
        if (isXWinner()) {
            return 10;
        }
        if (isOWinner()) {
            return -10;
        }
        return 0;  // default
    }

    /**
     * Determines if the game is a draw.
     *
     * @return True if the game is a draw, false otherwise.
     */
    @Override
    public boolean isDraw() {
        if (isWinner() != 0) {
            return false;
        }

        return !hasEmptyCells();
    }

    /**
     * Checks if 'X' is the winner.
     *
     * @return True if 'X' has won, false otherwise.
     */
    @Override
    public boolean isXWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (gameState[i][0] == 'X' &&
                    gameState[i][1] == 'X' &&
                    gameState[i][2] == 'X') {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (gameState[0][i] == 'X' &&
                    gameState[1][i] == 'X' &&
                    gameState[2][i] == 'X') {
                return true;
            }
        }
        // Check diagonals
        if (gameState[0][0] == 'X' &&
                gameState[1][1] == 'X' &&
                gameState[2][2] == 'X') {
            return true;
        }
        if (gameState[0][2] == 'X' &&
                gameState[1][1] == 'X' &&
                gameState[2][0] == 'X') {
            return true;
        }
        return false;
    }

    /**
     * Checks if 'O' is the winner.
     *
     * @return True if 'O' has won, false otherwise.
     */
    @Override
    public boolean isOWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (gameState[i][0] == 'O' &&
                    gameState[i][1] == 'O' &&
                    gameState[i][2] == 'O') {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (gameState[0][i] == 'O' &&
                    gameState[1][i] == 'O' &&
                    gameState[2][i] == 'O') {
                return true;
            }
        }
        // Check diagonals
        if (gameState[0][0] == 'O' &&
                gameState[1][1] == 'O' &&
                gameState[2][2] == 'O') {
            return true;
        }
        if (gameState[0][2] == 'O' &&
                gameState[1][1] == 'O' &&
                gameState[2][0] == 'O') {
            return true;
        }
        return false;
    }

    /**
     * Checks if there are any empty cells on the board.
     *
     * @return True if there are empty cells, false otherwise.
     */
    @Override
    public boolean hasEmptyCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Makes a computer move by finding the best possible position to place 'O'.
     *
     * @param board The game board.
     */
    @Override
    public void makeComputerMove(Board board) {
        int[] bestMove = minMax.findBestMove(this, false, 9);  // Update this line based on your actual MinMax function signature

        if (bestMove[0] != -1 && bestMove[1] != -1) {  // Check for a valid move
            board.placeMark('O', bestMove[0], bestMove[1]);
        } else {
            System.out.println("No valid move found.");
        }
    }

    /**
     * Removes a mark from the game board at the specified row and column.
     *
     * @param row Row index.
     * @param col Column index.
     */
    @Override
    public void removeMark(int row, int col) {
        gameState[row][col] = ' ';
    }
}
