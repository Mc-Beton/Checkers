package com.testing;

import com.checkers.board.Board;
import com.checkers.figures.Figure;
import com.checkers.figures.FigureColor;
import com.checkers.figures.Pawn;
import com.checkers.figures.Queen;

public class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Board newBoard = new Board();
        newBoard.setNewGame();
        System.out.println(newBoard);
    }
}
