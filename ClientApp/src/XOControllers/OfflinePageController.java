/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.OfflinePage;
import XOGameBoard.TicTacToe;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class OfflinePageController extends OfflinePage {

    private boolean isRecording = false;
    private TicTacToe xoGame;
    private Line winningLine;
    public String player1;
    public String player2;
    private Stage stage;

    public OfflinePageController(Stage stage) {
        xoGame = new TicTacToe();

        backButton.setOnMouseClicked(e -> {
            HomePageController root = new HomePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        });
        recordButton.setOnMouseClicked(e -> {
            if (!isRecording) {
                isRecording = true;
                changeRecordButton();
                RecordController.setPlayersName(user1, user2);
                RecordController.setPlayersShapes("X", "O");
                RecordController.createFile("offline");
            }

        });

        replayButton.setOnMouseClicked(e -> {
            resetGame();
        });

        String[] players = xoGame.assignXOToPlayer();
        player1 = players[0];
        player2 = players[1];

        initializeGameButtonsHandlers();

    }

    public void setOfflineLabelToPlayerTurn() {
        String currentPlayer = xoGame.getCurrentPlayer();
        if (currentPlayer.equals("X")) {
            playOfflineLabel.setText(user1 + "'s turn (X)");
        } else {
            playOfflineLabel.setText(user2 + "'s turn (O)");
        }
    }

    private void initializeGameButtonsHandlers() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int rowButton = row;
                final int colButton = col;
                buttons[row][col].setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
                buttons[row][col].setOnAction(e -> {
                    processMove(rowButton, colButton);
                });
            }
        }
    }

    private void processMove(int row, int col) {
        if (xoGame.makeMove(row, col)) {
            buttons[row][col].setText(xoGame.getCurrentPlayer());
            if (isRecording) {
                RecordController.saveMove(row, col, xoGame.getCurrentPlayer());
            }
            if (xoGame.isWinningMove(row, col) && winningLine == null) {
                drawWinningLine();
                stopRecording();
                updateScore();
            } else if (xoGame.isDraw()) {
                stopRecording();
            } else if (winningLine != null) {
                resetGame();
            } else {
                xoGame.switchPlayer();
                setOfflineLabelToPlayerTurn();
            }
        }
    }

    private void updateScore() {
        if (xoGame.getCurrentPlayer().equals("X")) {
            score1 += 5;
        } else {
            score2 += 5;
        }
        scoreLabelX.setText("Scores " + score1 + ":" + score2);
    }

    private void resetGame() {
        stopRecording();
        xoGame.resetBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        if (winningLine != null) {
            borderPane.getChildren().remove(winningLine);
            winningLine = null;
        }
    }

    private void drawWinningLine() {
        int[] winningLineIndices = xoGame.getWinningLine();

        Button btn1 = buttons[winningLineIndices[0]][winningLineIndices[1]];
        Button btn3 = buttons[winningLineIndices[4]][winningLineIndices[5]];

        Point2D point1 = btn1.localToScene(btn1.getWidth() / 2, btn1.getHeight() / 2);
        Point2D point3 = btn3.localToScene(btn3.getWidth() / 2, btn3.getHeight() / 2);

        double startX = point1.getX();
        double startY = point1.getY();
        double endX = point3.getX();
        double endY = point3.getY();

        if (isRecording) {
            RecordController.saveLine(startX, startY, endX, endY);
        }

        winningLine = new Line(startX, startY, endX, endY);

        winningLine.setStroke(Color.RED);
        winningLine.setStrokeWidth(5);

        borderPane.getChildren().add(winningLine);
    }

    private void changeRecordButton() {
        Image recImage;
        if (isRecording) {
            recImage = new Image(getClass().getResourceAsStream("/media/stop.png"));
        } else {
            recImage = new Image(getClass().getResourceAsStream("/media/record.png"));
        }
        ImageView recImageView = new ImageView(recImage);
        recImageView.setFitHeight(40);
        recImageView.setFitWidth(40);
        recordButton.setGraphic(recImageView);
    }

    private void stopRecording() {
        if (isRecording) {
            isRecording = false;
            changeRecordButton();
            RecordController.closeRecordConection();
        }
    }

}
