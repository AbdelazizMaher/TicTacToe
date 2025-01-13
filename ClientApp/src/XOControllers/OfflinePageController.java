/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.OfflinePage;
import XOGameBoard.TicTacToe;
import javafx.geometry.Bounds;
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

    private boolean isPaused = false;
    private TicTacToe xoGame;
    private Line winningLine; 
    public String player1;
    public String player2;
    private Stage stage;

    public OfflinePageController(Stage stage) {
        this.stage = stage;
        xoGame = new TicTacToe();

        backButton.setOnMouseClicked(e -> {
            HomePageController root = new HomePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        });
        recordButton.setOnMouseClicked(e -> {
            Image recImage;
            if (isPaused) {
                recImage = new Image(getClass().getResourceAsStream("/media/record.png"));
            } else {
                recImage = new Image(getClass().getResourceAsStream("/media/stop.png"));
            }
            ImageView recImageView = new ImageView(recImage);
            recImageView.setFitHeight(40);
            recImageView.setFitWidth(40);
            recordButton.setGraphic(recImageView);
            isPaused = !isPaused;
        });
        
        replayButton.setOnMouseClicked(e -> {
            resetGame();
        });

        String[] players = xoGame.assignXOToPlayer();
        player1 = players[0];
        player2 = players[1];

        initializeGameButtonsHandlers();

    }

    public void setOfflineToPlayerTurn() {
        if (player1.equals("X")) {
            playerOneLabel.setText(user1 + " turn");
        } else {
            playerOneLabel.setText(user2 + " turn");
        }
    }

    private void initializeGameButtonsHandlers() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int rowButton = row;
                final int colButton = col;
                buttons[row][col].setOnAction(e -> {
                    processMove(rowButton, colButton);
                });
            }
        }
    }

    private void processMove(int row, int col) {
        if (xoGame.makeMove(row, col)) {
            buttons[row][col].setText(xoGame.getCurrentPlayer());
            if (xoGame.isWinningMove(row, col)) {
                drawWinningLine();
                updateScore();
            } else if (xoGame.isDraw()) {
            } else {
                xoGame.switchPlayer();
                setOfflineToPlayerTurn();
            }
        }
    }





}
