package com.juhnkim.gamelogics;

import com.juhnkim.interfaces.BoardOperations;
import com.juhnkim.utils.MinMax;
import com.juhnkim.views.Board;

public class TicTacToeLogic implements BoardOperations {
    private MinMax minMax;
    private char[][] gameState;

    public TicTacToeLogic(MinMax minMax) {
        this.minMax = minMax;
        this.gameState = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameState[i][j] = ' ';
            }
        }
    }

    public void updateGameState(char mark, int row, int col) {
        gameState[row][col] = mark;
    }

    @Override
    public boolean isValidMove(int row, int col) {
        return gameState[row][col] == ' ';
    }

    @Override
    public void placeMark(char mark, int row, int col) {
        gameState[row][col] = mark;
    }

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

    @Override
    public boolean isDraw() {
        if (isWinner() != 0) {
            return false;
        }

        return !hasEmptyCells();
    }

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

    // sätt ihop till en
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

    @Override
    public boolean hasEmptyCells() {
        // Check if all cells are filled (i.e., board is full)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState[i][j] == ' ') {
                    return true; // found an empty cell, so it's not a draw yet
                }
            }
        }
        return false;
    }

    @Override
    public void makeComputerMove(Board board) {
        int[] bestMove = minMax.findBestMove(this, false, 9);  // Update this line based on your actual MinMax function signature

        // Place the computer's mark ('O') at the best position
        if (bestMove[0] != -1 && bestMove[1] != -1) {  // Check for a valid move
//            placeMark('O', bestMove[0], bestMove[1]);
            board.placeMark('O', bestMove[0], bestMove[1]);
        } else {
            // Handle the case where no best move was found
            System.out.println("No valid move found.");
        }
    }

    @Override
    public void removeMark(int row, int col) {
        gameState[row][col] = ' ';
    }
}
