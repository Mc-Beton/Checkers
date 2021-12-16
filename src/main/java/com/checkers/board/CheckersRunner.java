package com.checkers.board;

import com.checkers.figures.FigureColor;
import com.checkers.figures.Pawn;
import com.checkers.figures.Queen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CheckersRunner extends Application {

    private final Image checkBoard = new Image("file:src/main/resources/board.jpg");
    private final Image blackPawn = new Image("file:src/main/resources/Figures/blackPawn.gif");
    private final Image whitePawn = new Image("file:src/main/resources/Figures/whitePawn.gif");
    private final Image blackQueen = new Image("file:src/main/resources/Figures/blackQueen.gif");
    private final Image whiteQueen = new Image("file:src/main/resources/Figures/whiteQueen.gif");
    private final Image newGame = new Image("file:src/main/resources/Menu/newGame.gif");
    private final Image exitGame = new Image("file:src/main/resources/Menu/exit.gif");
    private final FlowPane menuButtons = new FlowPane(Orientation.VERTICAL);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = showMenu();
        showScene(primaryStage, grid);
    }

    private void showScene(Stage primaryStage, GridPane grid) {
        Scene scene = new Scene(grid, 800, 800);
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane showMenu() {
        Background background = setBackgroundBoard(checkBoard);
        GridPane grid = createMenuPane(background);
        setMenuButton(grid);
        return grid;

    }

    private void setMenuButton(GridPane grid) {
        setStartbutton();
        setExitButton();
        menuButtons.setAlignment(Pos.CENTER);
        grid.add(menuButtons,0, 0);
    }

    private void setExitButton() {
        Button treminateGame = new Button();
        treminateGame.setGraphic(new ImageView(exitGame));
        treminateGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        menuButtons.getChildren().add(treminateGame);
    }

    private void setStartbutton() {
        Button startNewGame = new Button();
        startNewGame.setGraphic(new ImageView(newGame));
        startNewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage primaryStage = new Stage();
                GridPane grid = startANewGame();
                showScene(primaryStage, grid);
            }
        });
        menuButtons.getChildren().add(startNewGame);
    }

    private GridPane startANewGame() {
        Background background = setBackgroundBoard(checkBoard);
        GridPane grid = createGridPane(background);
        Board newBoard = new Board();
        newBoard.setNewGame();
        displayOnBoard(grid, newBoard);
        return grid;
    }

    private Background setBackgroundBoard(Image image) {
        BackgroundSize backgroundSize = new BackgroundSize(100,100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }

    private void displayOnBoard(GridPane grid, Board board) {
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

    private GridPane createGridPane(Background background) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        createRows(grid, 8);
        createColumns(grid, 8);
        grid.setPadding(new Insets(-15, -15, -15, -15));
        grid.setGridLinesVisible(true);
        return grid;
    }

    private GridPane createMenuPane(Background background) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        grid.setPadding(new Insets(-15, -15, -15, -15));
        return grid;
    }

    private void createColumns(GridPane grid, int col) {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100 / col);

        for (int i = 0; i < col; i++) {
            grid.getColumnConstraints().add(cc);
        }
    }

    private void createRows(GridPane grid, int row) {
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100 / row);

        for (int i = 0; i < row; i++) {
            grid.getRowConstraints().add(rc);
        }
    }
}
