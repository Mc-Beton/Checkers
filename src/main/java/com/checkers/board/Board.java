package com.checkers.board;

import com.checkers.figures.Figure;
import com.checkers.figures.FigureColor;
import com.checkers.figures.None;
import com.checkers.figures.Pawn;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Board {
    private FigureColor whoseMove = FigureColor.WHITE;
    private List<BoardRow> rows = new ArrayList<>();

    //Primary methods to create a board
    public Board() {
        for (int i = 0; i < 8; i++) {
            BoardRow boardrows = new BoardRow();
            rows.add(boardrows);
        }
        this.rows = rows;
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getColumns().get(col);
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getColumns().set(col, figure);
    }

    public String toString() {
        String line = "=========================";
        String rowlist = "";
        String boardRows = "";
        for (BoardRow rowes : rows) {
            for (Figure figure : rowes.getColumns()) {
                boardRows += "|" + figure;
            }
            rowlist += boardRows + "|" + "\n" + line + "\n";
            boardRows = "";
        }
        return line + "\n" + rowlist;
    }

    //Methods of Movement
    public boolean move(int col, int row, int newcol, int newrow) {
        boolean newResult = checkPlayerPick(col, row, newcol, newrow);

        if (getFigure(col, row) instanceof Pawn && newResult)
            newResult = newResult && colorMoveDirection(col, row, newrow);
        boolean withHit = pawnHitOrMove(col, row, newcol, newrow);
        doMove(col, row, newcol, newrow, withHit, newResult);
        return newResult;
    }

    private void doMove(int col, int row, int newcol, int newrow, boolean withHit, boolean result) {
        Figure figure = getFigure(col, row);
        if (result) {
            setFigure(col, row, new None());
            setFigure(newcol, newrow, figure);
            if (withHit)
                removeBetween(col, row, newcol, newrow);
            switchPlayer();
        } else {
            System.out.println("Invalid move, try again");
        }
    }

    public void switchPlayer() {
        whoseMove = (whoseMove == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }

    private void removeBetween(int col, int row, int newcol, int newrow) {
        int dx = (newcol > col) ? 1 : -1;
        int dy = (newrow > row) ? 1 : -1;
        setFigure(newcol - dx, newrow - dy, new None());
    }

    //Methods of pick and movement validation
    public boolean checkPlayerPick(int col, int row, int newcol, int newrow) {
        boolean result = true;
        result = isInRange(col, row);
        result = result && isFigurePresent(col, row);
        result = result && isInRange(newcol, newrow);
        result = result && isEmpty(newcol, newrow);
        result = result && checkColor(col, row);
        result = result && checkTileColor(newcol, newrow);
        return result;
    }

    public boolean isInRange(int newcol, int newrow) {
        return newcol >= 0 && newcol <= 7 && newrow >= 0 && newrow <= 7;
    }

    public boolean isFigurePresent(int col, int row) {
        return !(getFigure(col, row) instanceof None);
    }

    public boolean isEmpty(int col, int row) {
        return getFigure(col, row) instanceof None;
    }

    public boolean checkColor(int col, int row) {
        return getFigure(col, row).getColor() == whoseMove;
    }

    public boolean colorMoveDirection(int col, int row, int newrow) {
        Figure figure = getFigure(col, row);
        if (figure.getColor() == FigureColor.WHITE)
            return (row > newrow);
        else
            return (newrow > row);
    }

    public boolean checkTileColor(int newcol, int newrow) {
        return (newcol + newrow) % 2 != 0;
    }

    private boolean pawnHitOrMove(int col, int row, int newcol, int newrow) {
        return abs(col - newcol) != 1 && abs(row - newrow) != 1;
    }

    //Set new game method
    public void setNewGame() {
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 7; col++) {
                if (checkTileColor(col, row))
                    setFigure(col, row, new Pawn(FigureColor.BLACK));
            }
        }
        for (int row = 5; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (checkTileColor(col, row))
                    setFigure(col, row, new Pawn(FigureColor.WHITE));
            }
        }
    }
}
