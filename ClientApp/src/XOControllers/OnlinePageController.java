/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.OnlinePage;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class OnlinePageController extends OnlinePage{
    public OnlinePageController(Stage stage){
        backButton.setOnMouseClicked(e -> {
            Scene scene2 = new Scene(new AvailableUserPageController(stage));
            stage.setScene(scene2); 
        });
    }
}
