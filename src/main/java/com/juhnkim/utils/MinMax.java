package com.juhnkim.utils;

import com.juhnkim.views.Board;

public class MinMax {

	public static int minMax(Board board, int depth, boolean isMaximizing) {
		int score = board.isWinner();

		if (score == 10) return score;
		if (score == -10) return score;
		if (!board.hasEmptyCells()) return 0;

		if (isMaximizing) {
			int best = Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board.isValidMove(i, j)) {
						board.placeMark('X', i, j);
						best = Math.max(best, minMax(board, depth + 1, false));
						board.removeMark(i, j);
					}
				}
			}
			return best;
		} else {
			int best = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board.isValidMove(i, j)) {
						board.placeMark('O', i, j);
						best = Math.min(best, minMax(board, depth + 1, true));
						board.removeMark(i, j);
					}
				}
			}
			return best;
		}
	}


	public static int[] findBestMove(Board board, boolean isMaximizing, int depth) {
		int[] bestMove = {-1, -1};
		int bestValue = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.isValidMove(i, j)) {
					char mark = isMaximizing ? 'X' : 'O';
					board.placeMark(mark, i, j);
					int moveValue = minMax(board, depth - 1, !isMaximizing); // Decrease depth
					board.removeMark(i, j);

					if (isMaximizing && moveValue > bestValue) {
						bestValue = moveValue;
						bestMove[0] = i;
						bestMove[1] = j;
					} else if (!isMaximizing && moveValue < bestValue) {
						bestValue = moveValue;
						bestMove[0] = i;
						bestMove[1] = j;
					}
				}
			}
		}
		return bestMove;
	}
}
