package com.checkers.board;

import com.checkers.Game;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CheckersRunner extends Application {

    private final Image checkBoard = new Image("file:src/main/resources/board.jpg");
    private final Image newGame = new Image("file:src/main/resources/Menu/newGame.gif");
    private final Image exitGame = new Image("file:src/main/resources/Menu/exit.gif");
    private final Image onePlayer = new Image("file:src/main/resources/Menu/OnePlayer.gif");
    private final Image twoPlayer = new Image("file:src/main/resources/Menu/TwoPlayer.gif");
    private final FlowPane menuButtons = new FlowPane(Orientation.VERTICAL);
    private final FlowPane menuPlayerButtons = new FlowPane(Orientation.VERTICAL);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = showMenu(primaryStage);
        showScene(primaryStage, grid);
    }

    private void showScene(Stage primaryStage, GridPane grid) {
        Scene scene = new Scene(grid, 800, 800);
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane showMenu(Stage primaryStage) {
        Background background = setBackgroundBoard(checkBoard);
        GridPane grid = createMenuPane(background);
        setMenuButton(grid, primaryStage);
        return grid;
    }

    private GridPane showPlayerMenu(Stage primaryStage) {
        Background background = setBackgroundBoard(checkBoard);
        GridPane grid = createMenuPane(background);
        setMenuPlayerButton(grid, primaryStage);
        return grid;
    }

    private void setMenuButton(GridPane grid, Stage stage) {
        setStartButton(stage);
        menuButtons.setVgap(20);
        setExitButton();
        menuButtons.setAlignment(Pos.CENTER);
        grid.add(menuButtons, 0, 0);
    }

    private void setMenuPlayerButton(GridPane grid, Stage stage) {
        setStartOnePlayer(stage);
        menuPlayerButtons.setVgap(20);
        setStartTwoPlayer(stage);
        menuPlayerButtons.setAlignment(Pos.CENTER);
        grid.add(menuPlayerButtons, 0, 0);
    }

    private void setExitButton() {
        Button terminateGame = new Button();
        terminateGame.setGraphic(new ImageView(exitGame));
        terminateGame.setBackground(null);
        terminateGame.setOnAction(event -> System.exit(0));
        menuButtons.getChildren().add(terminateGame);
    }

    private void setStartTwoPlayer(Stage stage) {
        Button startNewTwoPlayerGame = new Button();
        startNewTwoPlayerGame.setGraphic(new ImageView(twoPlayer));
        startNewTwoPlayerGame.setBackground(null);
        startNewTwoPlayerGame.setOnAction(event -> {
            GridPane grid = startANewTwoPlayerGame();
            showScene(stage, grid);
        });
        menuPlayerButtons.getChildren().add(startNewTwoPlayerGame);
    }

    private void setStartOnePlayer(Stage stage) {
        Button startNewOnePlayerGame = new Button();
        startNewOnePlayerGame.setGraphic(new ImageView(onePlayer));
        startNewOnePlayerGame.setBackground(null);
        startNewOnePlayerGame.setOnAction(event -> {
            GridPane grid = startANewOnePlayerGame();
            showScene(stage, grid);
        });
        menuPlayerButtons.getChildren().add(startNewOnePlayerGame);
    }

    private void setStartButton(Stage stage) {
        Button startNewGame = new Button();
        startNewGame.setGraphic(new ImageView(newGame));
        startNewGame.setBackground(null);
        startNewGame.setOnAction(event -> {
            GridPane grid = showPlayerMenu(stage);
            showScene(stage, grid);
        });
        menuButtons.getChildren().add(startNewGame);
    }

    private GridPane startANewOnePlayerGame() {
        Background background = setBackgroundBoard(checkBoard);
        GridPane grid = createGridPane(background);
        Board newBoard = new Board();
        newBoard.setNewGame();
        Game game = new Game(grid, newBoard);
        game.displayOnBoard();
        grid.setOnMouseClicked(e -> {
            int x = (int)e.getX()/100;
            int y = (int)e.getY()/100;
            game.doClick(x, y);
        });
        return grid;
    }

    private GridPane startANewTwoPlayerGame() {
        Background background = setBackgroundBoard(checkBoard);
        GridPane grid = createGridPane(background);
        Board newBoard = new Board();
        newBoard.setNewGame();
        Game game = new Game(grid, newBoard);
        game.displayOnBoard();
        grid.setOnMouseClicked(e -> {
            int x = (int)e.getX()/100;
            int y = (int)e.getY()/100;
            game.doClickTwoPlayer(x, y);
        });
        return grid;
    }

    private Background setBackgroundBoard(Image image) {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }

    private GridPane createGridPane(Background background) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        createRows(grid);
        createColumns(grid);
        grid.setPadding(new Insets(-15, -15, -15, -15));
        return grid;
    }

    private GridPane createMenuPane(Background background) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        return grid;
    }

    private void createColumns(GridPane grid) {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100 / 8);

        for (int i = 0; i < 8; i++) {
            grid.getColumnConstraints().add(cc);
        }
    }

    private void createRows(GridPane grid) {
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100 / 8);

        for (int i = 0; i < 8; i++) {
            grid.getRowConstraints().add(rc);
        }
    }
}
