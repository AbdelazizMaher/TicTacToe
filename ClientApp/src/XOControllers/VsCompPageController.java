/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.VsCompPage;
import XOGameBoard.TicTacToe;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class VsCompPageController extends VsCompPage {

    private boolean isRecording = false;
    private TicTacToe xoGame;
    private Line winningLine;
    int count;
    int difficultyLevel;
    private Stage stage;

    public VsCompPageController(Stage stage, int difficultyLevel) {
        xoGame = new TicTacToe();
        this.difficultyLevel = difficultyLevel;
        backButton.setOnMouseClicked(e -> {
            Scene scene2 = new Scene(new DifficultyLevelController(stage));
            stage.setScene(scene2);
        });
        recordButton.setOnMouseClicked(e -> {
            if (!isRecording) {
                isRecording = true;
                changeRecordButton();
                RecordController.setPlayersName("player", "computer");
                RecordController.setPlayersShapes("X", "O");
                RecordController.createFile("offline");
            }
        });

        replayButton.setOnMouseClicked(e -> {
            resetGame();
        });

        String[] players = xoGame.assignXOToPlayer();

        initializeGameButtonsHandlers();
    }

    public void setOnlineLabelToPlayerTurn() {
        String currentPlayer = xoGame.getCurrentPlayer();
        if (currentPlayer.equals("X")) {
            playerOneLabel.setText(playerX + "'s turn (X)");
        } else {
            playerOneLabel.setText(playerO + "'s turn (O)");
        }
    }

    private void initializeGameButtonsHandlers() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int rowButton = row;
                final int colButton = col;
                buttons[row][col].setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
                buttons[row][col].setOnAction(e -> {
                    if (xoGame.getCurrentPlayer().equals("X")) {
                        processMove(rowButton, colButton);

                    }
                    if (xoGame.getCurrentPlayer().equals("O")) {

                        if (difficultyLevel == 0) {
                            computerEasyMove();
                        } else {
                            computerMediumHardMove();
                            System.out.println("dfgdffgdrff sefeffref---------  " + difficultyLevel);
                        }

                    }
                });
            }
        }

    }

    private void processMove(int row, int col) {
        if (xoGame.makeMove(row, col)) {
            if (isRecording) {
                RecordController.saveMove(row, col, "X");
            }
            buttons[row][col].setText(xoGame.getCurrentPlayer());
            count++;
            if (xoGame.isWinningMove(row, col) && winningLine == null) {
                drawWinningLine();
                updateScore();
                stopRecording();
                showAlert(stage, "Congratulations", "You have won the game");
            } else if (xoGame.isDraw()) {
                stopRecording();
                showAlert(stage, "Draw", "Game Draw");
            } else if (winningLine != null) {
                resetGame();
            } else {
                xoGame.switchPlayer();
                setOnlineLabelToPlayerTurn();
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
        count = 0;
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

    void computerEasyMove() {
        Random rand = new Random();
        int rowComp = rand.nextInt(3);
        int colComp = rand.nextInt(3);
        while (!xoGame.makeMove(rowComp, colComp) && count < 9) {
            rowComp = rand.nextInt(3);
            colComp = rand.nextInt(3);
        }
        if (count < 9) {
            if (isRecording) {
                RecordController.saveMove(rowComp, colComp, "O");
            }
            buttons[rowComp][colComp].setText(xoGame.getCurrentPlayer());
            count++;
        }
        if (xoGame.isWinningMove(rowComp, rowComp) && winningLine == null) {
            drawWinningLine();
            stopRecording();
            updateScore();
            showAlert(stage, "Hard Luck", "You lost, better luck next time");
        } else if (xoGame.isDraw()) {
            stopRecording();
        } else if (winningLine != null) {
            resetGame();
        } else {
            xoGame.switchPlayer();
            setOnlineLabelToPlayerTurn();
        }
    }

    private int minimax(int depth, boolean isMaximizing, int r, int c) {
        if (difficultyLevel == 1) {
            if (xoGame.isWinningMove(1,2 )) {
                return isMaximizing ? -10 : 10;
            } else if (xoGame.isDraw()) {
                return 0;
            }
        } else {
            if (xoGame.isWinningMove(r, c)) {
                return isMaximizing ? -10 : 10;
            } else if (xoGame.isDraw()) {
                return 0;
            }
        }

        int best = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (xoGame.board[row][col] == null) {

                    xoGame.board[row][col] = isMaximizing ? "O" : "X";

                    int currentScore = minimax(depth + 1, !isMaximizing, row, col);

                    xoGame.board[row][col] = null;

                    if (isMaximizing) {
                        best = Math.max(best, currentScore);
                    } else {
                        best = Math.min(best, currentScore);
                    }
                }
            }
        }

        return best;
    }

    private void computerMediumHardMove() {
        int bestVal = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;
        int moveVal;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (xoGame.board[row][col] == null) {

                    xoGame.board[row][col] = "O";
                    moveVal = minimax(0, false, row, col);

                    xoGame.board[row][col] = null;
                    if (moveVal > bestVal) {
                        bestRow = row;
                        bestCol = col;
                        bestVal = moveVal;
                    }
                }
            }
        }

        xoGame.board[bestRow][bestCol] = "O";
        buttons[bestRow][bestCol].setText("O");

        if (isRecording) {
            RecordController.saveMove(bestRow, bestCol, "O");
        }

        if (xoGame.isWinningMove(bestRow, bestCol) && winningLine == null) {
            drawWinningLine();
            stopRecording();
            updateScore();
            showAlert(stage, "Hard Luck", "You lost, better luck next time");
        } else if (xoGame.isDraw()) {
            stopRecording();
        } else if (winningLine != null) {
            resetGame();
        } else {
            xoGame.switchPlayer();
            setOnlineLabelToPlayerTurn();
        }
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

    private void showAlert(Stage stage, String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setHeaderText(title);
        alert.setTitle("");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
