package com.checkers;

import java.util.*;
import java.lang.*;
import java.io.*;

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

