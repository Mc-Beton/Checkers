package com.checkers.board;

public class Movement {
    private int col;
    private int row;
    private int col2;
    private int row2;
    private boolean withHit;

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getCol2() {
        return col2;
    }

    public int getRow2() {
        return row2;
    }

    public boolean isWithHit() {
        return withHit;
    }

    public Movement(int col, int row, int col2, int row2, boolean withHit) {
        this.col = col;
        this.row = row;
        this.col2 = col2;
        this.row2 = row2;
        this.withHit = withHit;
    }

}
