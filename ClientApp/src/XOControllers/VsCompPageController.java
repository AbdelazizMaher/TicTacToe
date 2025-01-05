/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.VsCompPage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class VsCompPageController extends VsCompPage{
    private boolean isPaused = false;
    public VsCompPageController(Stage stage){
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
            }
            ImageView recImageView = new ImageView(recImage);
            recImageView.setFitHeight(40);
            recImageView.setFitWidth(40);
            recordButton.setGraphic(recImageView);         
            isPaused = !isPaused;
        });
    }
}
