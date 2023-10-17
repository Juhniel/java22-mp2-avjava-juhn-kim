package com.juhnkim.views;

import com.juhnkim.logics.TicTacToeLogic;
import com.juhnkim.utils.MinMax;

import javax.swing.*;
import java.awt.*;

/**
 * The Board class handles the graphical user interface of the Tic-Tac-Toe game.
 */
public class Board {
    private final TicTacToeLogic gameLogic;
    private final MinMax minMax;
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JButton[][] buttons;

    /**
     * Initializes the Board with the game logic and MinMax algorithm.
     *
     * @param gameLogic The TicTacToeLogic instance.
     * @param minMax    The MinMax instance.
     */
    public Board(TicTacToeLogic gameLogic, MinMax minMax) {
        this.gameLogic = gameLogic;
        this.minMax = minMax;
        frame = new JFrame("Tic-Tac-Toe");
        mainPanel = new JPanel();
        buttons = new JButton[3][3];
    }

    /**
     * Initializes the Tic-Tac-Toe game board GUI.
     */
    public void initializeGame() {
        mainPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> handlePlayerMove(finalI, finalJ));
                mainPanel.add(buttons[i][j]);
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    /**
     * Handles the player's move and updates the game state.
     *
     * @param row Row index.
     * @param col Column index.
     */
    private void handlePlayerMove(int row, int col) {
        if (gameLogic.isValidMove(row, col)) {
            placeMark('X', row, col);

            if (gameLogic.isXWinner()) {
                JOptionPane.showMessageDialog(frame, "Player Wins!");
                return;
            } else if (gameLogic.isDraw()) {
                JOptionPane.showMessageDialog(frame, "It's a Draw!");
                return;
            }

            gameLogic.makeComputerMove(this);

            bestMove();

            if (gameLogic.isOWinner()) {
                JOptionPane.showMessageDialog(frame, "Computer Wins!");
            } else if (gameLogic.isDraw()) {
                JOptionPane.showMessageDialog(frame, "It's a Draw!");
            }
        }
    }

    /**
     * Calls findBestMove method for the player and sets the background color to green.
     */
    public void bestMove() {
        int[] bestMove = minMax.findBestMove(gameLogic, true, 9);
        if (bestMove[0] != -1 && bestMove[1] != -1) {
            System.out.println("Best move for player X: Row = " + bestMove[0] + ", Col = " + bestMove[1]);
            buttons[bestMove[0]][bestMove[1]].setBackground(Color.GREEN);
        } else {
            System.out.println("No valid move found.");
        }
    }

    /**
     * Places a mark ('X' or 'O') on the board and updates the game state.
     *
     * @param mark Mark to be placed.
     * @param row  Row index.
     * @param col  Column index.
     */
    public void placeMark(char mark, int row, int col) {
        if (gameLogic.isValidMove(row, col)) {
            gameLogic.updateGameState(mark, row, col);
            buttons[row][col].setText(String.valueOf(mark));
            buttons[row][col].setBackground(null);
        }
    }
}
