package com.juhnkim.views;

import com.juhnkim.gamelogics.TicTacToeLogic;
import com.juhnkim.utils.MinMax;

import javax.swing.*;
import java.awt.*;

public class Board {
    private final TicTacToeLogic gameLogic;
    private final MinMax minMax;
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JButton[][] buttons;

    public Board(TicTacToeLogic gameLogic, MinMax minMax) {
        this.gameLogic = gameLogic;
        this.minMax = minMax;
        frame = new JFrame("Tic-Tac-Toe");
        mainPanel = new JPanel();
        buttons = new JButton[3][3];
    }

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

    public void bestMove() {
        int[] bestMove = minMax.findBestMove(gameLogic, true, 9);
        if (bestMove[0] != -1 && bestMove[1] != -1) {  // Check for a valid move
            System.out.println("Best move for player X: Row = " + bestMove[0] + ", Col = " + bestMove[1]);
            buttons[bestMove[0]][bestMove[1]].setBackground(Color.GREEN);  // Set the background color to green
        } else {
            // Handle the case where no best move was found
            System.out.println("No valid move found.");
        }
    }

    public void placeMark(char mark, int row, int col) {
        if (gameLogic.isValidMove(row, col)) {
            gameLogic.updateGameState(mark, row, col);
            buttons[row][col].setText(String.valueOf(mark));
            buttons[row][col].setBackground(null);
        }
    }
}
