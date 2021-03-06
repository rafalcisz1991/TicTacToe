package com.kodilla.tictactoe;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class Square extends Rectangle {

    private static final int INITIAL_SIZE = 80;
    private int column, row;
    private final AppController appController;

    private boolean isSquareUsed;
    private String shape;

    public Square(int column, int row, Paint fill, AppController appController, String shape) {
        super(INITIAL_SIZE, INITIAL_SIZE, fill);
        this.column = column;
        this.row = row;
        this.appController = appController;
        this.isSquareUsed = false;
        this.shape = shape;
        this.setOnMouseClicked(event -> {
            try {
                appController.handleOnMouseClicked(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean getIsSquareUsed() {
        return isSquareUsed;
    }

    public void setSquareUsed(boolean squareUsed) {
        isSquareUsed = squareUsed;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (isSquareUsed != square.isSquareUsed) return false;
        return Objects.equals(shape, square.shape);
    }
}
