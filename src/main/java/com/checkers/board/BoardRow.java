package com.checkers.board;

import com.checkers.figures.Figure;
import com.checkers.figures.None;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {
    private List<Figure> columns = new ArrayList<>();

    public BoardRow() {
        for (int spot = 0; spot<8; spot++) {
            Figure empty = new None();
            columns.add(empty);
        }
        this.columns = columns;
    }

    public List<Figure> getColumns() {
        return columns;
    }
}

