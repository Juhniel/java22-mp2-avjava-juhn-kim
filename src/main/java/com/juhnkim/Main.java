package com.juhnkim;

import com.juhnkim.logics.TicTacToeLogic;
import com.juhnkim.utils.MinMax;
import com.juhnkim.views.Board;

public class Main {
    public static void main(String[] args) {
        MinMax minMax = new MinMax();
        TicTacToeLogic gameLogic = new TicTacToeLogic(minMax);
        Board board = new Board(gameLogic, minMax);
        board.initializeGame();
    }
}