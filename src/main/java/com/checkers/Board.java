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
}
