/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesladders;


import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import static snakesladders.SnakesAndLadders.SQUARE_SIZE;

/**
 *
 * @author nerme
 */
public class SnakesAndLaddersController extends SnakesAndLadders {
    private final Map<String, String> ladderLine = new HashMap<>();
    private final Map<String, String> snakeLine = new HashMap<>();

    public SnakesAndLaddersController() {
        rollDiceButton.setOnAction(e -> {
            String diceResult = rollDice();
            rollDiceButton.setText(diceResult);
        });
        Platform.runLater(() -> drawSnakesAndLadders(gc));
    }

    private String rollDice() {
        int diceRoll = random.nextInt(6) + 1;
        System.out.println("Rolled: " + diceRoll);

        if (player1Turn) {
            player1Pos += diceRoll;
            if (player1Pos > 100) player1Pos = 100;
            movePlayer(player1, player1Pos);
        } else {
            player2Pos += diceRoll;
            if (player2Pos > 100) player2Pos = 100;
            movePlayer(player2, player2Pos);
        }
        player1Turn = !player1Turn;
        turnText.setText(player1Turn ? "Player 1's Turn" : "Player 2's Turn");

        return "Rolled: " + diceRoll;
    }

    private void movePlayer(Circle player, int position) {
        int row = (position - 1) / 10;
        int col = (position - 1) % 10; 
        int reversedRow = 9 - row;

        GridPane.setConstraints(player, col, reversedRow);

        adjustPlayerPositionForLaddersAndSnakes(player, row, col);
    }

    private void adjustPlayerPositionForLaddersAndSnakes(Circle player, int row, int col) {
        String start = row + "," + col;
        if (ladderLine.containsKey(start)) {
            String end = ladderLine.get(start);
            String[] endCoords = end.split(",");
            int endRow = Integer.parseInt(endCoords[0]);
            int endCol = Integer.parseInt(endCoords[1]);
            row = endRow;
            col = endCol;
        }

        start = row + "," + col;
        if (snakeLine.containsKey(start)) {
            String end = snakeLine.get(start);
            String[] endCoords = end.split(",");
            int endRow = Integer.parseInt(endCoords[0]);
            int endCol = Integer.parseInt(endCoords[1]);
            row = endRow;
            col = endCol;
        }

        int reversedRow = 9 - row; 
        GridPane.setConstraints(player, col, reversedRow);
    }

    private void drawSnakesAndLadders(GraphicsContext gc) {
        ladderLine.put("0,1", "5,5");
        snakeLine.put("8,0", "5,0");

        for (Map.Entry<String, String> entry : ladderLine.entrySet()) {
            String start = entry.getKey();
            String end = entry.getValue();
            String[] startCoords = start.split(",");
            String[] endCoords = end.split(",");

            int startRow = Integer.parseInt(startCoords[0]);
            int startCol = Integer.parseInt(startCoords[1]);
            int endRow = Integer.parseInt(endCoords[0]);
            int endCol = Integer.parseInt(endCoords[1]);

            drawLadder(gc, startRow, startCol, endRow, endCol);
        }

        for (Map.Entry<String, String> entry : snakeLine.entrySet()) {
            String start = entry.getKey();
            String end = entry.getValue();
            String[] startCoords = start.split(",");
            String[] endCoords = end.split(",");

            int startRow = Integer.parseInt(startCoords[0]);
            int startCol = Integer.parseInt(startCoords[1]);
            int endRow = Integer.parseInt(endCoords[0]);
            int endCol = Integer.parseInt(endCoords[1]);

            drawSnake(gc, startRow, startCol, endRow, endCol);
        }
    }

    private void drawLadder(GraphicsContext gc, int startRow, int startCol, int endRow, int endCol) {
        double startX = startCol * SQUARE_SIZE + SQUARE_SIZE / 2;
        double startY = (9 - startRow) * SQUARE_SIZE + SQUARE_SIZE / 2;
        double endX = endCol * SQUARE_SIZE + SQUARE_SIZE / 2;
        double endY = (9 - endRow) * SQUARE_SIZE + SQUARE_SIZE / 2;

        gc.setStroke(Color.GREEN);
        gc.setLineWidth(2);
        gc.strokeLine(startX, startY, endX, endY);
    }

    private void drawSnake(GraphicsContext gc, int startRow, int startCol, int endRow, int endCol) {
        double startX = startCol * SQUARE_SIZE + SQUARE_SIZE / 2;
        double startY = (9 - startRow) * SQUARE_SIZE + SQUARE_SIZE / 2;
        double endX = endCol * SQUARE_SIZE + SQUARE_SIZE / 2;
        double endY = (9 - endRow) * SQUARE_SIZE + SQUARE_SIZE / 2;

        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.setLineDashes(10,5);
        gc.setLineDashOffset(0);
        gc.strokeLine(startX, startY, endX, endY);
    }
}
