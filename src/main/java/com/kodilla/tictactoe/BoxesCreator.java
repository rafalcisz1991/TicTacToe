package com.kodilla.tictactoe;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoxesCreator {

    public static void showEndBox(String winning) {
        Stage endWindow = new Stage();
        endWindow.setOnCloseRequest(Event::consume);

        Button winningButton = new Button();
        winningButton.setText(winning);
        winningButton.setAlignment(Pos.CENTER);
        HBox.setMargin(winningButton, new Insets(10));
        winningButton.setOnAction(e -> {
            endWindow.close();
        });

        HBox hBox3 = new HBox(20);
        hBox3.setAlignment(Pos.BOTTOM_CENTER);
        hBox3.getChildren().addAll(winningButton);

        Scene scene = new Scene(hBox3);
        endWindow.resizableProperty().setValue(false);
        endWindow.setScene(scene);
        endWindow.showAndWait();
    }

    public static void createDisplayBox(AppController controller) {
        Stage window = new Stage();
        window.setHeight(500);
        window.setWidth(500);
        window.setOnCloseRequest(Event::consume);

        TicTacToeApp.display();

        Label popupLabel = new Label("Wybierz symbol.\n O czy X?");
        popupLabel.setStyle(" -fx-font-size: 36; -fx-text-alignment: center; -fx-wrap-text: true;");

        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().add(popupLabel);

        Button chooseOButton = new Button();
        chooseOButton.setText("Wybierz O");
        chooseOButton.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(chooseOButton, new Insets(5));
        chooseOButton.setOnAction(e -> {
            controller.setUserShape(controller.getCircle());
            controller.setComputerShape(controller.getCross());
            window.close();
        });

        Button chooseXButton = new Button();
        chooseXButton.setText("Wybierz X");
        chooseXButton.setAlignment(Pos.CENTER_RIGHT);
        HBox.setMargin(chooseXButton, new Insets(5));
        chooseXButton.setOnAction(e -> {
            controller.setUserShape(controller.getCross());
            controller.setComputerShape(controller.getCircle());
            window.close();
        });

        HBox hBox2 = new HBox(20);
        hBox2.setAlignment(Pos.BOTTOM_CENTER);
        hBox2.getChildren().addAll(chooseOButton, chooseXButton);

        VBox layout = new VBox(100);
        layout.setPadding(new Insets(0, 0, 50, 0));
        layout.getChildren().addAll(hBox1, hBox2);
        layout.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene = new Scene(layout);
        window.resizableProperty().setValue(false);
        window.setScene(scene);
        window.showAndWait();
    }
}
