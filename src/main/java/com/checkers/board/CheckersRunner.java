package com.checkers.board;

import com.checkers.board.Board;
import com.checkers.figures.Figure;
import com.checkers.figures.FigureColor;
import com.checkers.figures.None;
import com.checkers.figures.Pawn;
import com.checkers.figures.Queen;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CheckersRunner extends Application {

    private Image checkBoard = new Image("file:src/main/resources/board.jpg");
    private Image blackPawn = new Image("file:src/main/resources/Figures/blackPawn.gif");
    private Image whitePawn = new Image("file:src/main/resources/Figures/whitePawn.gif");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Background background = setBackgroundBoard();
        GridPane grid = createGridPane(background);
        setNewGame(grid);
        Scene scene = new Scene(grid, 800, 800);


        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Background setBackgroundBoard() {
        BackgroundSize backgroundSize = new BackgroundSize(100,100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(checkBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        return background;
    }

    private void setNewGame(GridPane grid) {
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 7; col++) {
                if (checkTileColor(col, row))
                    addBlackPawn(grid, col, row);
            }
        }
        for (int row = 5; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (checkTileColor(col, row))
                    addBWhitePawn(grid, col, row);
            }
        }
    }

    public boolean checkTileColor(int newcol, int newrow) {
        return (newcol + newrow) % 2 != 0;
    }

    private void addBlackPawn(GridPane grid, int col, int row) {
        ImageView bPawn1 = new ImageView(blackPawn);
        GridPane.setHalignment(bPawn1, HPos.CENTER);
        GridPane.setValignment(bPawn1, VPos.CENTER);
        grid.add(bPawn1, col, row);
    }

    private void addBWhitePawn(GridPane grid, int col, int row) {
        ImageView bPawn1 = new ImageView(whitePawn);
        GridPane.setHalignment(bPawn1, HPos.CENTER);
        GridPane.setValignment(bPawn1, VPos.CENTER);
        grid.add(bPawn1, col, row);
    }

    private GridPane createGridPane(Background background) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        createRows(grid);
        createColumns(grid);
        grid.setPadding(new Insets(-15, -15, -15, -15));
        grid.setGridLinesVisible(true);
        return grid;
    }

    private void createColumns(GridPane grid) {
        int columnCount = 8;
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100 / columnCount);

        for (int i = 0; i < columnCount; i++) {
            grid.getColumnConstraints().add(cc);
        }
    }

    private void createRows(GridPane grid) {
        int rowCount = 8;
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100 / rowCount);

        for (int i = 0; i < rowCount; i++) {
            grid.getRowConstraints().add(rc);
        }
    }
}
