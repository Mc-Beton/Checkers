package com.checkers.figures;

public class Queen implements Figure {
    private final FigureColor color;

    public Queen(FigureColor color) {
        this.color = color;
    }

    public String toString() {
        return colorSymbol() + "Q";
    }

    private String colorSymbol() {
        return (color == FigureColor.WHITE) ? "w" : "b";
    }

    @Override
    public FigureColor getColor() {
        return color;
    }
}
