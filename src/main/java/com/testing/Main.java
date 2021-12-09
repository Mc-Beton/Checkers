package com.testing;

import com.checkers.board.Board;
import com.checkers.figures.Figure;
import com.checkers.figures.FigureColor;
import com.checkers.figures.Pawn;
import com.checkers.figures.Queen;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws java.lang.Exception {

        System.out.println("Welcome to checkers \n To start a new game type n");

        try {
            whatToDo();
        } catch (WrongKeyException e) {
            System.out.println("Wrong value, please try again");
            whatToDo();
        }
    }

    private static void whatToDo() throws WrongKeyException {
        System.out.println("What would you like to do?");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("n") || choice.equals("x")) {
            if (choice.equals("n")) {
                newGameCommand();
            } else if (choice.equals("x")) {
                exitGameCommand();
            }
        } else {
            throw new WrongKeyException();
        }
    }

    private static void exitGameCommand() throws WrongKeyException {
        System.out.println("Are you sure you want to leave the game? y/n");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("y") || choice.equals("n")) {
            if (choice.equals("y")) {
                System.exit(0);
            } else {
                whatToDo();
            }
        } else {
            throw new WrongKeyException();
        }
    }

    private static void newGameCommand() throws WrongKeyException {
        System.out.println("Are you sure you want to start a new game? y/n");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("y") || choice.equals("n")) {
            if (choice.equals("y")) {
                startNewGame();
            } else {
                whatToDo();
            }
        } else {
            throw new WrongKeyException();
        }
    }

    private static void startNewGame() {
        Board newBoard = new Board();
        newBoard.setNewGame();
        System.out.println(newBoard);
        System.out.println();
    }
}
