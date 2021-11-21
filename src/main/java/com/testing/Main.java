package com.testing;

import com.checkers.*;

public class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Board newBoard = new Board();
        Figure q1 = new Queen();
        Figure p1 = new Pawn();
        newBoard.setFigure(0, 0, q1);
        newBoard.setFigure(4, 7, p1);
        newBoard.setFigure(5, 6, new Pawn());
        System.out.println(newBoard);
        newBoard.setFigure(5, 6, new Pawn());
        newBoard.move(4, 7,  5, 6);
        System.out.println(newBoard);
    }
}
