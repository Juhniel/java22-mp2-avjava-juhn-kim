package com.juhnkim.interfaces;

import com.juhnkim.views.Board;

/**
 * BoardOperations serves as an interface to be implemented by any class that
 * deals with board manipulation. This interface provides a list of essential
 * methods for board manipulation. It may be extended to include other
 * board-specific methods.
 *
 * @author lukas kurasinski
 */
public interface BoardOperations {

	/**
	 * Checks if placing a mark at the given row and column is a valid move.
	 *
	 * @param row Row index.
	 * @param col Column index.
	 * @return True if the cell is empty, false otherwise.
	 */
	boolean isValidMove(int row, int col);

	/**
	 * Places a mark ('X' or 'O') on the board at the specified row and column.
	 *
	 * @param mark The mark ('X' or 'O') to place.
	 * @param row  Row index.
	 * @param col  Column index.
	 */
	void placeMark(char mark, int row, int col);

	/**
	 * Checks if there is a winner on the board.
	 *
	 * @return 10 if 'X' wins, -10 if 'O' wins, 0 if no winner.
	 */
	int isWinner();

	/**
	 * Checks if the game is a draw.
	 *
	 * @return True if it's a draw, false otherwise.
	 */
	boolean isDraw();

	/**
	 * Checks if 'X' is the winner.
	 *
	 * @return True if 'X' wins, false otherwise.
	 */
	boolean isXWinner();

	/**
	 * Checks if 'O' is the winner.
	 *
	 * @return True if 'O' wins, false otherwise.
	 */
	boolean isOWinner();

	/**
	 * Checks if there are any empty cells on the board.
	 *
	 * @return True if there are empty cells, false otherwise.
	 */
	boolean hasEmptyCells();

	/**
	 * Makes the computer's move.
	 *
	 * @param board The current state of the board.
	 */
	void makeComputerMove(Board board);

	/**
	 * Removes a mark ('X' or 'O') from the specified row and column.
	 *
	 * @param row Row index.
	 * @param col Column index.
	 */
	void removeMark(int row, int col);
}
