/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.RecordPage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class RecordPageController extends RecordPage {

    private boolean isStopped = false;
    private Line winningLine;

    public RecordPageController(Stage stage, String filePath) {
        super(stage);

        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        parsePlayers(fileName);

        Vector<String> movesRecord = RecordController.readFile(filePath);
        processMoves(movesRecord);

        backButton.setOnMouseClicked(e -> {
            Scene scene = new Scene(new HistoryPageController(stage, HistoryPageController.activeMode, HistoryPageController.activeName));
            stage.setScene(scene);
        });

        rewatchButton.setOnMouseClicked(e -> {
            resetGame();
            isStopped = false;
            processMoves(movesRecord);
        });

        stopButton.setOnMouseClicked(e -> {
            isStopped = true;
        });

    }

    private void processMoves(Vector<String> movesRecord) {
        Thread thread = new Thread(() -> {
            for (String record : movesRecord) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

                String[] parts = record.split("#");
                String moveORline = parts[0];

                if (moveORline.equals("move")) {
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);
                    String shape = parts[3];

                    updateButton(row, col, shape);
                } else if (moveORline.equals("line")) {
                    double startX = Double.parseDouble(parts[1]);
                    double startY = Double.parseDouble(parts[2]);
                    double endX = Double.parseDouble(parts[3]);
                    double endY = Double.parseDouble(parts[4]);

                    drawWinningLine(startX, startY, endX, endY);
                }
                if (isStopped) {
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void updateButton(int row, int col, String shape) {
        Platform.runLater(() -> {
            Button button = buttons[row][col];
            button.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
            button.setText(shape);
        });
    }

    private void drawWinningLine(double startX, double startY, double endX, double endY) {
        Platform.runLater(() -> {
            winningLine = new Line(startX, startY, endX, endY);
            winningLine.setStroke(Color.RED);
            winningLine.setStrokeWidth(5);

            borderPane.getChildren().add(winningLine);
        });
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setStyle("-fx-background-color: rgba(169, 169, 169, 0.5); -fx-border-color: red; -fx-text-fill: black;");
                buttons[row][col].setText("");
            }
        }
        if (winningLine != null) {
            borderPane.getChildren().remove(winningLine);
            winningLine = null;
        }
    }

    private void parsePlayers(String fileName) {
        String[] parts = fileName.split("_");

        String[] player1Details = parts[0].split("#");
        String player1 = player1Details[0];
        String player1Shape = player1Details[1];

        String[] player2Details = parts[1].split("#");
        String player2 = player2Details[0];
        String player2Shape = player2Details[1];

        playerXLabel.setText(player1 + " (" + player1Shape + ")");
        playerOLabel.setText(player2 + " (" + player2Shape + ")");

    }

}
