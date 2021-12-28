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
    private final FlowPane menuButtons = new FlowPane(Orientation.VERTICAL);

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

    private GridPane showWinner(Stage stage) {
        Background background = setBackgroundBoard(checkBoard);
        GridPane grid = createMenuPane(background);
        setMenuButton(grid, stage);
        return grid;
    }

    private void setMenuButton(GridPane grid, Stage stage) {
        setStartbutton(stage);
        menuButtons.setVgap(20);
        setExitButton();
        menuButtons.setAlignment(Pos.CENTER);
        grid.add(menuButtons, 0, 0);
    }

    private void setExitButton() {
        Button treminateGame = new Button();
        treminateGame.setGraphic(new ImageView(exitGame));
        treminateGame.setBackground(null);
        treminateGame.setOnAction(event -> System.exit(0));
        menuButtons.getChildren().add(treminateGame);
    }

    private void setStartbutton(Stage stage) {
        Button startNewGame = new Button();
        startNewGame.setGraphic(new ImageView(newGame));
        startNewGame.setBackground(null);
        startNewGame.setOnAction(event -> {
            GridPane grid = startANewGame();
            showScene(stage, grid);
        });
        menuButtons.getChildren().add(startNewGame);
    }

    private GridPane startANewGame() {
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
