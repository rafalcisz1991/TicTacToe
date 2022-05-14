package com.kodilla.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TicTacToeController {

    Path path = Paths.get("C:/Users/Dell/IdeaProjects/TicTacToe/src/main/resources/files/Wyniki.txt");

    private final Map<String, Square> squares = new HashMap<>();
    private final Image cross = new Image("files/cross1.png", 100, 100,
            false, true);
    private final Image circle = new Image("files/nought1.png", 100, 100,
            false, true);
    private Image userShape;
    private Image computerShape;

    private boolean didPlayerWon;
    private boolean didComputerWon;
    private boolean computerMoved;

    private final Scores scores = new Scores(this);

    public TicTacToeController() throws IOException {
    }

    public void handleOnMouseClicked(Square square) {
        int col;
        int row;

        Random random = new Random();
        scores.readScore();

        boolean playerMoved = false;
        if (!didPlayerWon && !didComputerWon) {
            if (!square.getIsSquareUsed()) {
                square.setFill(new ImagePattern(this.userShape));
                square.setSquareUsed(true);
                square.setShape(this.userShape.toString());
                playerMoved = true;
            }

            for (int c = 0; c < 3; c++) {
                if (squares.get(c + "-0").equals(square) && squares.get(c + "-1").equals(square) &&
                        squares.get(c + "-2").equals(square)) {
                    setdidPlayerWon(true);
                    playerWon();
                }
            }
            for (int r = 0; r < 3; r++) {
                if (!didPlayerWon && squares.get("0-" + r).equals(square) && squares.get("1-" + r).equals(square) &&
                        squares.get("2-" + r).equals(square)) {
                    setdidPlayerWon(true);
                    playerWon();
                }
            }
            if (!didPlayerWon && squares.get("0-0").equals(square) && squares.get("1-1").equals(square) &&
                    squares.get("2-2").equals(square)) {
                setdidPlayerWon(true);
                playerWon();
            }
            if (!didPlayerWon && squares.get("2-0").equals(square) && squares.get("1-1").equals(square) &&
                    squares.get("0-2").equals(square)) {
                setdidPlayerWon(true);
                playerWon();
            }
        }

        long freeSquares = squares.values().stream()
                .filter(s -> !s.getIsSquareUsed())
                .count();

        if (!didComputerWon && !didPlayerWon && playerMoved && freeSquares > 0) {
            do {
                col = random.nextInt(3);
                row = random.nextInt(3);
                Square computerMove = squares.get(col + "-" + row);

                if (!computerMove.getIsSquareUsed()) {
                    computerMove.setFill(new ImagePattern(this.computerShape));
                    computerMove.setSquareUsed(true);
                    computerMove.setShape(this.computerShape.toString());
                    computerMoved = true;
                }

                for (int c = 0; c < 3; c++) {
                    if (squares.get(c + "-0").equals(computerMove) && squares.get(c + "-1").equals(computerMove) &&
                            squares.get(c + "-2").equals(computerMove)) {
                        setdidComputerWon(true);
                        computerWon();
                    }
                }
                for (int r = 0; r < 3; r++) {
                    if (!didComputerWon && squares.get("0-" + r).equals(computerMove) &&
                            squares.get("1-" + r).equals(computerMove) && squares.get("2-" + r).equals(computerMove)) {
                        setdidComputerWon(true);
                        computerWon();
                    }
                }
                if (!didComputerWon && squares.get("0-0").equals(computerMove) &&
                        squares.get("1-1").equals(computerMove) && squares.get("2-2").equals(computerMove)) {
                    setdidComputerWon(true);
                    computerWon();
                }
                if (!didComputerWon && squares.get("2-0").equals(computerMove) &&
                        squares.get("1-1").equals(computerMove) && squares.get("0-2").equals(computerMove)) {
                    setdidComputerWon(true);
                    computerWon();
                }
            } while (!computerMoved);
        }
        computerMoved = false;
        if (!didPlayerWon && !didComputerWon && freeSquares == 0) {
            draw();
        }
        scores.writeFile();
    }

    public void savingScore() {
        try(BufferedWriter writer = Files.newBufferedWriter(path))
        {
            Date nowDate = new Date();
            writer.write("Data: " + nowDate);
            writer.newLine();
            writer.write("Zapisuje wynik " + "Gracz:" + scores.getPlayerSavedScore() + "-"
                    + scores.getComputerSavedScore() + ":komputer");
        } catch(IOException e) {
        System.out.println("wystąpił błąd: " + e);
        }
    }

    private void playerWon() {
        BoxesCreator.showEndBox("You won!");
        scores.setPlayerWon();
    }

    private void computerWon() {
        BoxesCreator.showEndBox("Computer won!");
        scores.setComputerWon();
    }

    private void draw() {
        BoxesCreator.showEndBox("Draw");
    }

    public Image getCross() {
        return cross;
    }

    public Image getCircle() {
        return circle;
    }

    public void setUserShape(Image userShape) {
        this.userShape = userShape;
    }

    public void setComputerShape(Image computerShape) {
        this.computerShape = computerShape;
    }

    void addSquare(String coordinates, Square square) {
        squares.put(coordinates, square);
    }

    public void setdidPlayerWon(boolean didPlayerWon) {
        this.didPlayerWon = didPlayerWon;
    }

    public void setdidComputerWon(boolean didComputerWon) {
        this.didComputerWon = didComputerWon;
    }

    public Scores getScores() {
        return scores;
    }
}
