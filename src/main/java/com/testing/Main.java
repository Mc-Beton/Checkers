package com.testing;

import com.checkers.*;

public class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Board newBoard = new Board();
        Figure q1 = new Queen();
        Figure p1 = new Pawn();
        newBoard.setFigure(0, 0, q1);
        newBoard.setFigure(7, 7, p1);
        System.out.println(newBoard.getFigure(7, 7));
        System.out.println(newBoard);
    }
}
