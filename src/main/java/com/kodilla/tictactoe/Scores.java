package com.kodilla.tictactoe;

import java.io.*;
import java.util.Objects;

public class Scores {

    private int playerSavedScore = 0;
    private int computerSavedScore = 0;
    private final AppController appController;

    private final ClassLoader classLoader = getClass().getClassLoader();

    private final File scores = new File(Objects.requireNonNull(classLoader.getResource("files/Wyniki.txt"))
            .getFile());

    public Scores(AppController appController) {
        this.appController = appController;
        readScore();
    }

    public void setPlayerWon(){
        playerSavedScore++;
        writeFile();
    }

    public void setComputerWon(){
        computerSavedScore++;
        writeFile();
    }

    public void writeFile(){
        writeFile(playerSavedScore + " - " + computerSavedScore);
    }

    public void writeFile(String value) {
        try {
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(scores, false));
            outputWriter.write(value);
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readScore() {
        String fileText = "0 - 0";
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(scores));
            fileText = inputReader.readLine();
            System.out.println(fileText);
            inputReader.close();
        } catch (Exception e) {
            writeFile("0 - 0");
        }

        String[] parts = fileText.split(" - ");
        String playerScoreString = parts[0];
        String computerScoreString = parts[1];

        this.playerSavedScore = Integer.parseInt(playerScoreString);
        this.computerSavedScore = Integer.parseInt(computerScoreString);
    }

    public int getPlayerSavedScore() {
        return playerSavedScore;
    }

    public int getComputerSavedScore() {
        return computerSavedScore;
    }

    @Override
    public String toString() {
        return "Current scoring: " +
                "Player " + playerSavedScore + "-" +
                computerSavedScore + " Computer";
    }
}
