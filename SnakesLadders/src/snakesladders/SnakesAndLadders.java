/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesladders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

/**
 *
 * @author nerme
 */
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
import javafx.scene.layout.AnchorPane;

public class SnakesAndLadders extends AnchorPane {

    protected static final int BOARD_SIZE = 10;
    protected static final int SQUARE_SIZE = 60;
    protected Circle player1, player2;
    protected int player1Pos = 1, player2Pos = 1;
    protected boolean player1Turn = true;
    protected Random random = new Random();
    protected Button[][] buttons = new Button[BOARD_SIZE][BOARD_SIZE];
    protected Button rollDiceButton;
    protected Text turnText;
    protected GraphicsContext gc;

    

    public SnakesAndLadders(){
        GridPane grid = new GridPane();
        grid.setVgap(2);
        grid.setHgap(2);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button cell = new Button();
                cell.setPrefSize(SQUARE_SIZE, SQUARE_SIZE);
                cell.setStyle("-fx-background-color: rgba(169, 169, 169, 0.5); -fx-border-color: red; -fx-text-fill: black;");
                buttons[row][col] = cell;
                GridPane.setRowIndex(cell, row);
                GridPane.setColumnIndex(cell, col);
                grid.getChildren().add(cell);
            }
        }

        player1 = new Circle(SQUARE_SIZE / 4);
        player1.setFill(Color.RED);
        grid.add(player1, 0, 9);

        player2 = new Circle(SQUARE_SIZE / 4);
        player2.setFill(Color.BLUE);
        grid.add(player2, 0, 9);

        rollDiceButton = new Button("Roll Dice");

        turnText = new Text("Player 1's Turn");

        HBox controlPanel = new HBox(10, rollDiceButton, turnText);
        controlPanel.setStyle("-fx-padding: 20;");
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(grid); // Add the grid at the bottom
        
        // Create a Canvas for drawing snakes and ladders
        Canvas canvas = new Canvas(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE);
        gc = canvas.getGraphicsContext2D();
        
        // Add canvas to the stack pane above the grid
        stackPane.getChildren().add(canvas);
        
        HBox root = new HBox(20, stackPane, controlPanel);
        getChildren().add(root);
        


        
    }

    
}
