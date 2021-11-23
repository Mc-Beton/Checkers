package com.checkers;

import java.util.ArrayList;
import java.util.List;

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
        boolean m = false;
        Figure figure = getFigure(col, row);

        boolean result = true;
        boolean withHit = false;
        result = result && isFigurePresent(col, row);
        result = result && isInRange(newcol, newrow);
        if (result)
            doMove(col, row, newcol, newrow, withHit);
        return result;


    }

    private void doMove(int col, int row, int newcol, int newrow, boolean withHit) {
        Figure figure = getFigure(col, row);
        setFigure(col, row, new None());
        setFigure(newcol, newrow, figure);
        if (withHit)
            removeBetween(col, row, newcol, newrow);
        switchPlayer();
    }

    private void switchPlayer() {
        whoseMove = (whoseMove == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }

    private void removeBetween(int col, int row, int newcol, int newrow) {
        int dx = (newcol > col) ? 1 : -1;
        int dy = (newrow > row) ? 1 : -1;
        setFigure(col + dx, row + dy, new None());
    }

    private boolean isInRange(int newcol, int newrow) {
        return newcol >= 0 && newcol <= 7 && newrow >= 0 && newrow <= 7;
    }

    private boolean isFigurePresent(int col, int row) {
        return !(getFigure(col, row) instanceof None);
    }
}
