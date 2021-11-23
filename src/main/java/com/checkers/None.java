package com.checkers;

public class None implements Figure {

    public FigureColor getColor() {
        return FigureColor.NONE;
    }


    public String toString() {
        return "  ";
    }
}
