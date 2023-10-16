package com.juhnkim.views;

import com.juhnkim.interfaces.BoardOperations;
import com.juhnkim.utils.MinMax;

import javax.swing.*;
import java.awt.*;

public class Board implements BoardOperations {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JButton[][] buttons;

    public Board() {
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
                buttons[i][j].addActionListener(e -> {
                    if (isValidMove(finalI, finalJ)) {
                        placeMark('X', finalI, finalJ);

                        if (isXWinner()) {
                            JOptionPane.showMessageDialog(frame, "Player Wins!");
                            return;
                        } else if (isDraw()) {
                            JOptionPane.showMessageDialog(frame, "It's a Draw!");
                            return;
                        }

                        makeComputerMove();

                        bestMove();

                        if (isOWinner()) {
                            JOptionPane.showMessageDialog(frame, "Computer Wins!");
                            return;
                        } else if (isDraw()) {
                            JOptionPane.showMessageDialog(frame, "It's a Draw!");
                        }
                    }
                });

                mainPanel.add(buttons[i][j]);
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void removeMark(int row, int col) {
        buttons[row][col].setText("");
    }

    public void bestMove() {
        int[] bestMove = MinMax.findBestMove(this, true, 9);
        if (bestMove[0] != -1 && bestMove[1] != -1) {  // Check for a valid move
            buttons[bestMove[0]][bestMove[1]].setBackground(Color.GREEN);  // Set the background color to green
        } else {
            // Handle the case where no best move was found
            System.out.println("No valid move found.");
        }
    }

    @Override
    public boolean isValidMove(int row, int col) {
        return "".equals(buttons[row][col].getText());
    }

    @Override
    public void placeMark(char mark, int row, int col) {
        buttons[row][col].setText(String.valueOf(mark));
        buttons[row][col].setBackground(null);
    }

    @Override
    public int isWinner() {
        if(isXWinner()){
            return 10;
        }
        if(isOWinner()){
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
            if ("X".equals(buttons[i][0].getText()) &&
                    "X".equals(buttons[i][1].getText()) &&
                    "X".equals(buttons[i][2].getText())) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if ("X".equals(buttons[0][i].getText()) &&
                    "X".equals(buttons[1][i].getText()) &&
                    "X".equals(buttons[2][i].getText())) {
                return true;
            }
        }
        // Check diagonals
        if ("X".equals(buttons[0][0].getText()) &&
                "X".equals(buttons[1][1].getText()) &&
                "X".equals(buttons[2][2].getText())) {
            return true;
        }
        if ("X".equals(buttons[0][2].getText()) &&
                "X".equals(buttons[1][1].getText()) &&
                "X".equals(buttons[2][0].getText())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isOWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if ("O".equals(buttons[i][0].getText()) &&
                    "O".equals(buttons[i][1].getText()) &&
                    "O".equals(buttons[i][2].getText())) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if ("O".equals(buttons[0][i].getText()) &&
                    "O".equals(buttons[1][i].getText()) &&
                    "O".equals(buttons[2][i].getText())) {
                return true;
            }
        }
        // Check diagonals
        if ("O".equals(buttons[0][0].getText()) &&
                "O".equals(buttons[1][1].getText()) &&
                "O".equals(buttons[2][2].getText())) {
            return true;
        }
        if ("O".equals(buttons[0][2].getText()) &&
                "O".equals(buttons[1][1].getText()) &&
                "O".equals(buttons[2][0].getText())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasEmptyCells() {
        // Check if all cells are filled (i.e., board is full)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ("".equals(buttons[i][j].getText())) {
                    return true; // found an empty cell, so it's not a draw yet
                }
            }
        }
        return false;
    }

    @Override
    public void makeComputerMove() {
        int[] bestMove = MinMax.findBestMove(this, false, 9);  // Update this line based on your actual MinMax function signature

        // Place the computer's mark ('O') at the best position
        if (bestMove[0] != -1 && bestMove[1] != -1) {  // Check for a valid move
            placeMark('O', bestMove[0], bestMove[1]);
        } else {
            // Handle the case where no best move was found
            System.out.println("No valid move found.");
        }
    }
}
