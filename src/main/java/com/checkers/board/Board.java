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

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getColumns().set(col, figure);
    }

    public boolean move(int col, int row, int newcol, int newrow) {
        boolean result = true;
        boolean withHit = false;
        boolean newResult = checkPlayerPick(col, row, newcol, newrow, result);

        if (getFigure(col, row) instanceof Pawn && newResult == true)
            newResult = colorMoveDirection(col, row, newrow);
            withHit = pawnHitOrMove(col, row, newcol, newrow);
            doMove(col, row, newcol, newrow, withHit, newResult);
        return newResult;
    }

    public boolean checkPlayerPick(int col, int row, int newcol, int newrow, boolean result) {
        result = result && isInRange(col, row);
        result = result && isFigurePresent(col, row);
        result = result && isInRange(newcol, newrow);
        result = result && checkColor(col, row);
        result = result && checkTileColor(newcol, newrow);
        return result;
    }

    private void doMove(int col, int row, int newcol, int newrow, boolean withHit, boolean result) {
        Figure figure = getFigure(col, row);
        if (result = true)
            setFigure(col, row, new None());
            setFigure(newcol, newrow, figure);
            if (withHit = true)
                removeBetween(col, row, newcol, newrow);
            switchPlayer();

    }

    public void switchPlayer() {
        whoseMove = (whoseMove == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }

    private void removeBetween(int col, int row, int newcol, int newrow) {
        int dx = (newcol > col) ? 1 : -1;
        int dy = (newrow > row) ? 1 : -1;
        setFigure(newcol - dx, newrow - dy, new None());
    }

    public boolean isInRange(int newcol, int newrow) {
        return newcol >= 0 && newcol <= 7 && newrow >= 0 && newrow <= 7;
    }

    public boolean isFigurePresent(int col, int row) {
        return !(getFigure(col, row) instanceof None);
    }

    public boolean checkColor(int col, int row) { return getFigure(col, row).getColor() == whoseMove; }

    public boolean colorMoveDirection (int col, int row, int newrow) {
        Figure figure = getFigure(col, row);
        if (figure.getColor() == FigureColor.WHITE)
            return (row > newrow);
        else
            return (newrow > row);
    }

    public boolean checkTileColor(int newcol, int newrow) { return (newcol + newrow) % 2 != 0; }

    private boolean pawnHitOrMove (int col, int row, int newcol, int newrow) {
        return abs(col - newcol) != 1 && abs(row - newrow) != 1;
    }
}
