/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.OfflinePage;
import XOGameBoard.TicTacToe;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class OfflinePageController extends OfflinePage{
    private boolean isPaused = false;
    private TicTacToe XOgame;
    
    public OfflinePageController(Stage stage){
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
    }
    
    private void processMove(int row, int col) {
    }
    
    private void updateScore() {
    
    }
    
    private void resetGame() {
    
    }
    
    private void drawWinningLine() {
    
    }
}
