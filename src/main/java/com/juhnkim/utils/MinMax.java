package com.juhnkim.utils;

import com.juhnkim.gamelogics.TicTacToeLogic;


public class MinMax {

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

