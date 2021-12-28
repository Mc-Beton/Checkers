package com.checkers.board;

import com.checkers.figures.Figure;
import com.checkers.figures.FigureColor;
import com.checkers.figures.None;
import com.checkers.figures.Pawn;
import com.checkers.figures.Queen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class Board {
    private FigureColor whoseMove = FigureColor.WHITE;
    private final List<BoardRow> rows = new ArrayList<>();

    //Primary methods to create a board
    public Board() {
        for (int i = 0; i < 8; i++) {
            BoardRow boardrows = new BoardRow();
            rows.add(boardrows);
        }
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getColumns().get(col);
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getColumns().set(col, figure);
    }

    public String toString() {
        String line = "=========================";
        StringBuilder rowlist = new StringBuilder();
        StringBuilder boardRows = new StringBuilder();
        for (BoardRow rowes : rows) {
            for (Figure figure : rowes.getColumns()) {
                boardRows.append("|").append(figure);
            }
            rowlist.append(boardRows).append("|").append("\n").append(line).append("\n");
            boardRows = new StringBuilder();
        }
        return line + "\n" + rowlist;
    }

    //Methods of Movement
    public boolean move(int col, int row, int newcol, int newrow, FigureColor color) {
        whoseMove = color;
        boolean newResult = checkPlayerPick(col, row, newcol, newrow);

        if (getFigure(col, row) instanceof Pawn && newResult) {
            newResult = colorMoveDirection(col, row, newrow);
            doPawnMove(col, row, newcol, newrow, newResult);
        } else if (getFigure(col, row) instanceof Queen && newResult) {
            newResult = queenLineMoveCheck(col, row, newcol, newrow);
            doQueenMove(col, row, newcol, newrow, newResult);
        }
        return newResult;
    }

    private void doQueenMove(int col, int row, int newcol, int newrow, boolean result) {
        Figure figure = getFigure(col, row);
        //System.out.println(queenHitMove(col, row, newcol, newrow));
        if (result && queenCleanMove(col, row, newcol, newrow)) {
            setFigure(col, row, new None());
            setFigure(newcol, newrow, figure);
        }
        if (result && queenHitMove(col, row, newcol, newrow)) {
            setFigure(col, row, new None());
            setFigure(newcol, newrow, figure);
            removeBetween(col, row, newcol, newrow);
        }
    }

    private void doPawnMove(int col, int row, int newcol, int newrow, boolean result) {
        Figure figure = getFigure(col, row);
        System.out.println("pawn move " + result);
        if (result) {
            setFigure(col, row, new None());
            setFigure(newcol, newrow, figure);
        }
        if (pawnHit(col, row, newcol, newrow) && result)
            removeBetween(col, row, newcol, newrow);
        if (newrow == 0 || newrow == 7)
            makeQueen(newcol, newrow);
    }

    public void makeQueen(int col, int row) {
        if (row == 0)
            setFigure(col, row, new Queen(FigureColor.WHITE));
        else if (row == 7)
            setFigure(col, row, new Queen(FigureColor.BLACK));
    }

    private void removeBetween(int col, int row, int newcol, int newrow) {
        int dx = (newcol > col) ? 1 : -1;
        int dy = (newrow > row) ? 1 : -1;
        setFigure(newcol - dx, newrow - dy, new None());
    }

    private boolean queenCleanMove(int col, int row, int newcol, int newrow) {
        boolean result = true;
        int dx = (newcol > col) ? 1 : -1;
        int dy = (newrow > row) ? 1 : -1;
        int xc = col;
        int xr = row;
        while (xc != newcol && xr != newrow) {
            xc = xc + dx;
            xr = xr + dy;
            result = result && (getFigure(xc, xr) instanceof None);
        }
        return result;
    }

    private boolean queenHitMove(int col, int row, int newcol, int newrow) {
        int dx = (newcol > col) ? 1 : -1;
        int dy = (newrow > row) ? 1 : -1;
        int xc = col;
        int xr = row;
        int count = 0;
        while (xc != newcol && xr != newrow) {
            xc = xc + dx;
            xr = xr + dy;
            if (!(getFigure(xc, xr) instanceof None)) {
                count++;
            }
        }
        return count == 1 && !(getFigure(newcol - dx, newrow - dy) instanceof None) && getFigure(newcol - dx, newrow - dy).getColor() != whoseMove;
    }

    //Methods of pick and movement validation
    public boolean checkPlayerPick(int col, int row, int newcol, int newrow) {
        boolean result;
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

    private boolean pawnHit(int col, int row, int newcol, int newrow) {
        return abs(col - newcol) != 1 && abs(row - newrow) != 1;
    }

    private boolean queenLineMoveCheck(int col, int row, int newcol, int newrow) {
        return abs(col - newcol) == abs(row - newrow);
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

    public boolean getWhiteFiguresAmount() {
        int wcount = 0;
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (isFigurePresent(col, row) && getFigure(col, row).getColor() == FigureColor.WHITE)
                    ++wcount;
            }
        }
        return wcount != 0;
    }

    public boolean getWBlackFiguresAmount() {
        int bcount = 0;

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (isFigurePresent(col, row) && getFigure(col, row).getColor() == FigureColor.BLACK)
                    ++bcount;
            }
        }
        return bcount != 0;
    }

    public int getRandomPosition() {
        Random random = new Random();
        return random.nextInt(8);
    }

    public boolean computerBlackPick(int col, int row) {
        boolean result;
        result = isFigurePresent(col, row);
        result = result && checkColor(col, row);
        result = result && col - 1 > 0 && col + 1 < 7 && row + 1 < 7;
        result = result && (!(isFigurePresent(col - 1, row + 1)) || !(isFigurePresent(col + 1, row + 1)));
        return result;
    }

    public void computerMoveBlack(FigureColor colorturn) {
        boolean done = true;
        for (int brow = 0; brow <= 7; brow++) {
            for (int bcol = 0; bcol <= 7; bcol++) {
                if (blackHitChecks(bcol, brow, colorturn)) {
                    done = false;
                    blackDoHit(bcol, brow);
                    System.out.println("Hit done " + bcol + " " + brow);
                }
            }
        }
        if (done) {
            doRandomMove();
            System.out.println("random done");
        }
    }

    private void doRandomMove() {
        int newCol = getRandomPosition();
        int newRow = getRandomPosition();
        if (checkTileColor(newCol,newRow) && computerBlackPick(newCol, newRow)) {
            blackDoMove(newCol, newRow);
            System.out.println(newCol + " " + newRow);
        } else {
            doRandomMove();
        }
    }

    public boolean blackHitLeft(int col, int row) {
        boolean result = false;
        if (col - 1 > 0 && col - 2 > 0 && row + 1 < 7 && row + 2 < 7)
            result = (getFigure(col-1, row+1).getColor() == FigureColor.WHITE) && isEmpty(col - 2, row + 2);
        return result;
    }

    public boolean blackHitRight(int col, int row) {
        boolean result = false;
        if (col + 1 < 7 && col + 2 < 7 && row + 1 < 7 && row + 2 < 7)
            result = (getFigure(col+1, row+1).getColor() == FigureColor.WHITE) && isEmpty(col + 2, row + 2);
        return result;
    }

    public boolean blackHitChecks(int col, int row, FigureColor colorturn) {
        boolean result;
        result = isFigurePresent(col, row);
        result = result && (getFigure(col, row).getColor() == colorturn);
        result = result && (blackHitLeft(col, row) || blackHitRight(col, row));
        return result;
    }

    public void blackDoHit(int col, int row) {
        if (blackHitLeft(col, row) && blackHitRight(col, row)) {
            Random random = new Random();
            int direct = random.nextInt(2);
            boolean b = direct == 0 ? move(col, row, col - 2, row + 2, whoseMove) : move(col, row, col + 2, row + 2, whoseMove);
        } else if (blackHitRight(col, row)) {
            move(col, row, col + 2, row + 2, whoseMove);
        } else if (blackHitLeft(col, row)) {
            move(col, row, col - 2, row + 2, whoseMove);
        }
    }

    public void blackDoMove(int col, int row) {
        if (!(isFigurePresent(col - 1, row + 1)) && !(isFigurePresent(col + 1, row + 1))) {
            Random random = new Random();
            int direct = random.nextInt(2);
            if (direct == 0)
                move(col, row, col - 1, row + 1, whoseMove);
            else
                move(col, row, col + 1, row + 1, whoseMove);
        } else if (!(isFigurePresent(col + 1, row + 1))) {
            move(col, row, col + 1, row + 1, whoseMove);
        } else if (!(isFigurePresent(col - 1, row + 1))) {
            move(col, row, col - 1, row + 1, whoseMove);
        }
    }
}
