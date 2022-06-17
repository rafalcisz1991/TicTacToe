package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class TicTacToeApp extends Application {

    private final Image imageback = new Image("files/background1.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(800, 800,
                false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        AppController controller = new AppController();

        BoxesCreator.createDisplayBox(controller);

        GridPane grid = new GridPane();
        grid.setBackground(background);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setGridLinesVisible(true);

        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                Square square = new Square(col, row, Color.TRANSPARENT, controller, null);
                grid.add(square, col, row);
                controller.addSquare(col + "-" + row, square);
            }
        }

        primaryStage.setTitle("My own Tic Tac Toe app");
        primaryStage.setHeight(700);
        primaryStage.setWidth(700);

        Button newGameButton = new Button();
        newGameButton.setText("Launch new game");
        newGameButton.setAlignment(Pos.BOTTOM_RIGHT);
        newGameButton.setOnAction(e -> {
            TicTacToeApp app = new TicTacToeApp();
            try {
                app.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button cancelButton = new Button();
        cancelButton.setText("Exit game and save scores");
        cancelButton.setOnAction(event -> {
            try {
                controller.savingScore();
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.close();
        });

        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setBottom(newGameButton);
        border.setTop(cancelButton);

        Scene scene = new Scene(border, 1000, 1000);

        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void display() {
    }
}

