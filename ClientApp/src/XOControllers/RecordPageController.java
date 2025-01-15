/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.RecordPage;
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

    public RecordPageController(Stage stage, String filePath) {
        super(stage);

        Vector<String> movesRecord = RecordController.readFile(filePath);
        processMoves(movesRecord);

        backButton.setOnMouseClicked(e -> {
            Scene scene = new Scene(new HistoryPageController(stage, HistoryPageController.activeMode, HistoryPageController.activeName));
            stage.setScene(scene);
        });

    }

    private void processMoves(Vector<String> movesRecord) {
        Thread thread = new Thread(() -> {
            for (String record : movesRecord) {
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

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
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
        Line winningLine = new Line(startX, startY, endX, endY);
        winningLine.setStroke(Color.RED);
        winningLine.setStrokeWidth(5);

        borderPane.getChildren().add(winningLine);
        });
    }
}
