package com.testing;

import com.checkers.board.Board;
import com.checkers.figures.Figure;
import com.checkers.figures.FigureColor;
import com.checkers.figures.Pawn;
import com.checkers.figures.Queen;

public class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Board newBoard = new Board();
        newBoard.setFigure(1, 0, new Pawn(FigureColor.BLACK));
        newBoard.setFigure(4, 7, new Pawn(FigureColor.WHITE));
        newBoard.setFigure(5, 6, new Pawn(FigureColor.BLACK));
        System.out.println(newBoard);
        newBoard.move(1, 0, 0, 2);
        System.out.println(newBoard);
        newBoard.move(4, 7,  6, 5);
        System.out.println(newBoard);

        System.out.println(newBoard);
    }
}
