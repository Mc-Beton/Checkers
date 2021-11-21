package com.checkers;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<BoardRow> rows = new ArrayList<>();

    public Board() {
        for (int i = 0; i<8; i++) {
            BoardRow boardrows = new BoardRow();
            rows.add(boardrows);
        }
        this.rows = rows;
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getColumns().get(col);
    }

    public String toString() {
        String line = "=================";
        String rowlist = "";
        String boardRows = "";
        for(BoardRow rowes : rows) {
            for(Figure figure : rowes.getColumns()) {
                boardRows += "|" +  figure;
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
        if (figure.getClass() == None.class) {
            System.out.println("There is no figure at this spot");
            return false;
        } else if (figure.getClass() == Pawn.class) {
            if (col +1 == newcol && row - 1 == newrow && newcol < 7 && newrow < 7 || col -1 == newcol && row - 1 == newrow && newcol < 7 && newrow < 7) {
                Figure newSpot  = getFigure(newcol, newrow);

                if (newSpot.getClass() == None.class) {
                    setFigure(col, row, new None());
                    setFigure(newcol, newrow, figure);
                    m = true;

                } else if (newSpot.getClass() == Pawn.class || newSpot.getClass() == Queen.class) {
                    if (getFigure(col + 2, row - 2).getClass() == None.class && col+2 <7 && row - 2 < 7 || getFigure(col - 2, row - 2).getClass() == None.class && col-2 > 0 && row - 2 < 7) {
                        setFigure(col, row, new None());
                        setFigure(newcol, newrow, new None());
                        setFigure(col + 2, row - 2, figure);
                        m = true;
                    } else {
                        System.out.println("Movement is not possible");
                        return false;
                    }
                }
            } else {
                System.out.println("Movement not possible");
                return false;
            }
        }
        return m;
    }
}
