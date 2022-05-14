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

public class AppController {

    Path path = Paths.get("C:/Users/Dell/IdeaProjects/TicTacToe/src/main/resources/files/Wyniki.txt");

    private final Map<String, Squares> squares = new HashMap<>();
    private final Image cross = new Image("files/cross.png", 100, 100,
            false, true);
    private final Image circle = new Image("files/circle.png", 100, 100,
            false, true);
    private Image userShape;
    private Image computerShape;

    private boolean didPlayerWon;
    private boolean didComputerWon;
    private boolean computerMoved;

    private final Scoring scoring = new Scoring(this);

    public AppController() throws IOException {
    }

    public void handleOnMouseClicked(Squares squares) {
        int col;
        int row;

        Random random = new Random();
        scoring.readScore();

        boolean playerMoved = false;
        if (!didPlayerWon && !didComputerWon) {
            if (!squares.getIsSquareUsed()) {
                squares.setFill(new ImagePattern(this.userShape));
                squares.setSquareUsed(true);
                squares.setShape(this.userShape.toString());
                playerMoved = true;
            }

            for (int c = 0; c < 3; c++) {
                if (this.squares.get(c + "-0").equals(squares) && this.squares.get(c + "-1").equals(squares) &&
                        this.squares.get(c + "-2").equals(squares)) {
                    setdidPlayerWon(true);
                    playerWon();
                }
            }
            for (int r = 0; r < 3; r++) {
                if (!didPlayerWon && this.squares.get("0-" + r).equals(squares) && this.squares.get("1-" + r).equals(squares) &&
                        this.squares.get("2-" + r).equals(squares)) {
                    setdidPlayerWon(true);
                    playerWon();
                }
            }
            if (!didPlayerWon && this.squares.get("0-0").equals(squares) && this.squares.get("1-1").equals(squares) &&
                    this.squares.get("2-2").equals(squares)) {
                setdidPlayerWon(true);
                playerWon();
            }
            if (!didPlayerWon && this.squares.get("2-0").equals(squares) && this.squares.get("1-1").equals(squares) &&
                    this.squares.get("0-2").equals(squares)) {
                setdidPlayerWon(true);
                playerWon();
            }
        }

        long freeSquares = this.squares.values().stream()
                .filter(s -> !s.getIsSquareUsed())
                .count();

        if (!didComputerWon && !didPlayerWon && playerMoved && freeSquares > 0) {
            do {
                col = random.nextInt(3);
                row = random.nextInt(3);
                Squares computerMove = this.squares.get(col + "-" + row);

                if (!computerMove.getIsSquareUsed()) {
                    computerMove.setFill(new ImagePattern(this.computerShape));
                    computerMove.setSquareUsed(true);
                    computerMove.setShape(this.computerShape.toString());
                    computerMoved = true;
                }

                for (int c = 0; c < 3; c++) {
                    if (this.squares.get(c + "-0").equals(computerMove) && this.squares.get(c + "-1").equals(computerMove) &&
                            this.squares.get(c + "-2").equals(computerMove)) {
                        setdidComputerWon(true);
                        computerWon();
                    }
                }
                for (int r = 0; r < 3; r++) {
                    if (!didComputerWon && this.squares.get("0-" + r).equals(computerMove) &&
                            this.squares.get("1-" + r).equals(computerMove) && this.squares.get("2-" + r).equals(computerMove)) {
                        setdidComputerWon(true);
                        computerWon();
                    }
                }
                if (!didComputerWon && this.squares.get("0-0").equals(computerMove) &&
                        this.squares.get("1-1").equals(computerMove) && this.squares.get("2-2").equals(computerMove)) {
                    setdidComputerWon(true);
                    computerWon();
                }
                if (!didComputerWon && this.squares.get("2-0").equals(computerMove) &&
                        this.squares.get("1-1").equals(computerMove) && this.squares.get("0-2").equals(computerMove)) {
                    setdidComputerWon(true);
                    computerWon();
                }
            } while (!computerMoved);
        }

        computerMoved = false;
         if (!didPlayerWon && !didComputerWon && freeSquares == 0) {
            draw();
        }
        scoring.writeFile();
    }

    public void savingScore() {
        try(BufferedWriter writer = Files.newBufferedWriter(path)) {
            Date nowDate = new Date();
            writer.write("Data: " + nowDate);
            writer.newLine();
            writer.write("Zapisuje wynik " + "Gracz:" + scoring.getPlayerSavedScore() + " - "
                    + scoring.getComputerSavedScore() + ":komputer");
        } catch(IOException e) {
            System.out.println("wystąpił błąd: " + e);
        }
    }

    private void playerWon() {
        BoxCreator.showEndBox( "You won!");
        scoring.setPlayerWon();
    }

    private void computerWon() {
        BoxCreator.showEndBox("Computer won!");
        scoring.setComputerWon();
    }

    private void draw() {
        BoxCreator.showEndBox("Draw");
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

    void addSquare(String coordinates, Squares squares) {
        this.squares.put(coordinates, squares);
    }

    public void setdidPlayerWon(boolean didPlayerWon) {
        this.didPlayerWon = didPlayerWon;
    }

    public void setdidComputerWon(boolean didComputerWon) {
        this.didComputerWon = didComputerWon;
    }

    public Scoring getScores() {
        return scoring;
    }
}
