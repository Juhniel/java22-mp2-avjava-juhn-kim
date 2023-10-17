package com.juhnkim.utils;

import com.juhnkim.logics.TicTacToeLogic;


public class MinMax {

    /**
     * This method implements the Minimax algorithm to determine the best possible move
     * for a player in a Tic-Tac-Toe game.
     *
     * @param gameLogic    The TicTacToeLogic object that holds the current game state.
     * @param depth        The depth of the game tree (recursion depth).
     * @param isMaximizing True if the current move is of the maximizing player, false otherwise.
     * @return The best possible score that can be achieved with the current board state.
     */
    public int minMax(TicTacToeLogic gameLogic, int depth, boolean isMaximizing) {
        int score = gameLogic.isWinner();

        if (score == 10) return score;
        if (score == -10) return score;
        if (!gameLogic.hasEmptyCells()) return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameLogic.isValidMove(i, j)) {
                        gameLogic.placeMark('X', i, j);
                        best = Math.max(best, minMax(gameLogic, depth + 1, false));
                        gameLogic.removeMark(i, j);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameLogic.isValidMove(i, j)) {
                        gameLogic.placeMark('O', i, j);
                        best = Math.min(best, minMax(gameLogic, depth + 1, true));
                        gameLogic.removeMark(i, j);
                    }
                }
            }
            return best;
        }
    }

    /**
     * This method finds the best possible move for the player at the current board state.
     *
     * @param gameLogic    The TicTacToeLogic object that holds the current game state.
     * @param isMaximizing True if the current move is of the maximizing player, false otherwise.
     * @param depth        The depth of the game tree (recursion depth).
     * @return An array of integers representing the row and column indices of the best move.
     */
    public int[] findBestMove(TicTacToeLogic gameLogic, boolean isMaximizing, int depth) {
        int bestValue = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameLogic.isValidMove(i, j)) {
                    char mark = isMaximizing ? 'X' : 'O';
                    gameLogic.placeMark(mark, i, j);
                    int moveValue = minMax(gameLogic, depth + 1, !isMaximizing);
                    gameLogic.removeMark(i, j);

                    if ((isMaximizing && moveValue > bestValue) || (!isMaximizing && moveValue < bestValue)) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestValue = moveValue;
                    }
                }
            }
        }

        return bestMove;
    }
}

