package com.testing;

import com.checkers.*;

public class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Board newBoard = new Board();
        Figure q1 = new Queen(FigureColor.WHITE);
        Figure p1 = new Pawn(FigureColor.BLACK);
        newBoard.setFigure(0, 0, q1);
        newBoard.setFigure(4, 7, p1);
        newBoard.setFigure(5, 6, new Pawn(FigureColor.WHITE));
        System.out.println(newBoard);
        newBoard.move(4, 7,  5, 6);
        System.out.println(newBoard);
        newBoard.move(6, 5, 7, 4);
        System.out.println(newBoard);
    }
}
