package com.checkers.figures;

public class Pawn implements Figure {
    private final FigureColor color;

    public Pawn(FigureColor color) {
        this.color = color;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    private String colorSymbol() {
        return (color == FigureColor.WHITE) ? "w" : "b";
    }

    public String toString() {
        return colorSymbol() + "P";
    }
}
