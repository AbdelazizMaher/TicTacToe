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
public class VsCompPageController extends VsCompPage{
    private boolean isPaused = false;
    private TicTacToe xoGame;
    private Line winningLine;
    int count;
    public VsCompPageController(Stage stage){
        xoGame = new TicTacToe();
        backButton.setOnMouseClicked(e -> {
            Scene scene2 = new Scene(new DifficultyLevelController(stage));
            stage.setScene(scene2); 
        });
        recordButton.setOnMouseClicked(e -> {
            Image recImage;
            if (isPaused) {
                recImage = new Image(getClass().getResourceAsStream("/media/record.png"));
            } else {
                recImage = new Image(getClass().getResourceAsStream("/media/stop.png"));
                //RecordController.createFile("comp",playerX,playerO);
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
                                computerMove();
                            } 
                        });
            }
        }
           
        }
    private void processMove(int row, int col) {
        if (xoGame.makeMove(row, col)) {
            if (isPaused) {
                RecordController.saveMove(row, col, "X");
            }
            buttons[row][col].setText(xoGame.getCurrentPlayer());
            count++;
            if (xoGame.isWinningMove(row, col) && winningLine == null) {
                drawWinningLine();
                updateScore();
                
            } else if (xoGame.isDraw()) {
                if (isPaused) {
                    RecordController.closeRecordConection();
                }
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
        isPaused = false;
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
        if (isPaused) {
                RecordController.saveLine(startX,startY,endX,endY);
                RecordController.closeRecordConection();
        }
        winningLine = new Line(startX, startY, endX, endY);

        winningLine.setStroke(Color.RED);
        winningLine.setStrokeWidth(5);

        borderPane.getChildren().add(winningLine);
    }
    void computerMove(){
        Random rand = new Random();
        int rowComp = rand.nextInt(3);
        int colComp = rand.nextInt(3);
        while(!xoGame.makeMove(rowComp, colComp)&&count<9){
            rowComp = rand.nextInt(3);
            colComp = rand.nextInt(3);
        }
        if(count<9){
            if (isPaused) {
                RecordController.saveMove(rowComp, colComp, "O");
            }
            buttons[rowComp][colComp].setText(xoGame.getCurrentPlayer());
            count++;
        }
        if (xoGame.isWinningMove(rowComp, rowComp) && winningLine == null) {
                drawWinningLine();
                updateScore();
            } else if (xoGame.isDraw()) {
                if (isPaused) {
                    RecordController.closeRecordConection();
                }
            } else if (winningLine != null) {
                resetGame();
            } else {
                xoGame.switchPlayer();
                setOnlineLabelToPlayerTurn();
            }
    }

}
