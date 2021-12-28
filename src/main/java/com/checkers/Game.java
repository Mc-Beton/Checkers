package com.checkers;

import com.checkers.board.Board;
import com.checkers.figures.FigureColor;
import com.checkers.figures.Pawn;
import com.checkers.figures.Queen;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {
    private final GridPane grid;
    private final Board board;
    private int oldX = -1;
    private int oldY = -1;
    private FigureColor whoseMove = FigureColor.WHITE;

    private final Image blackPawn = new Image("file:src/main/resources/Figures/blackPawn.gif");
    private final Image whitePawn = new Image("file:src/main/resources/Figures/whitePawn.gif");
    private final Image blackQueen = new Image("file:src/main/resources/Figures/blackQueen.gif");
    private final Image whiteQueen = new Image("file:src/main/resources/Figures/whiteQueen.gif");

    public Game(GridPane grid, Board board) {

        this.grid = grid;
        this.board = board;
    }

    public void doClick(int x, int y) {
        if (oldX == -1 && board.getFigure(x, y).getColor() == whoseMove) {
            oldX = x;
            oldY = y;
            Rectangle rectangle = new Rectangle(100, 100, Color.TRANSPARENT);
            rectangle.setStroke(Color.RED);
            grid.add(rectangle, x, y);
        } else if (oldX != -1) {
            if (board.move(oldX, oldY, x, y, whoseMove)) {
                whoseMove = (whoseMove == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
                board.computerMoveBlack(whoseMove);
                whoseMove = (whoseMove == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
            }
            oldX = -1;
            oldY = -1;
            displayOnBoard();
        }
    }

    public void displayOnBoard() {
        grid.getChildren().clear();
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (board.getFigure(col, row) instanceof Pawn)
                    displayPawn(grid, board, row, col);
                else if (board.getFigure(col, row) instanceof Queen)
                    displayQueen(grid, board, row, col);
            }
        }
    }

    private void displayQueen(GridPane grid, Board board, int row, int col) {
        if ((board.getFigure(col, row).getColor().equals(FigureColor.WHITE))) {
            addWhiteQueen(grid, col, row);
        } else if ((board.getFigure(col, row).getColor().equals(FigureColor.BLACK))) {
            addBlackQueen(grid, col, row);
        }
    }

    private void displayPawn(GridPane grid, Board board, int row, int col) {
        if ((board.getFigure(col, row).getColor().equals(FigureColor.WHITE))) {
            addBWhitePawn(grid, col, row);
        } else if ((board.getFigure(col, row).getColor().equals(FigureColor.BLACK))) {
            addBlackPawn(grid, col, row);
        }
    }

    private void addBlackQueen(GridPane grid, int col, int row) {
        ImageView queen = new ImageView(blackQueen);
        figureDisplayAlignment(queen);
        grid.add(queen, col, row);
    }

    private void addWhiteQueen(GridPane grid, int col, int row) {
        ImageView queen = new ImageView(whiteQueen);
        figureDisplayAlignment(queen);
        grid.add(queen, col, row);
    }

    private void addBlackPawn(GridPane grid, int col, int row) {
        ImageView pawn = new ImageView(blackPawn);
        figureDisplayAlignment(pawn);
        grid.add(pawn, col, row);
    }

    private void addBWhitePawn(GridPane grid, int col, int row) {
        ImageView pawn = new ImageView(whitePawn);
        figureDisplayAlignment(pawn);
        grid.add(pawn, col, row);
    }

    private void figureDisplayAlignment(ImageView figure) {
        GridPane.setHalignment(figure, HPos.CENTER);
        GridPane.setValignment(figure, VPos.CENTER);
    }
}
