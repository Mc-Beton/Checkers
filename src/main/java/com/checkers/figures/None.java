package com.checkers.figures;

public class None implements Figure {

    public FigureColor getColor() {
        return FigureColor.NONE;
    }


    public String toString() {
        return "  ";
    }
}
