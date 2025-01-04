/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.HomePage;
import XOGame.OfflinePage;
import XOGame.VsCompPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class OfflinePageController extends OfflinePage{
    public OfflinePageController(Stage stage){
        backButton.setOnMouseClicked(e -> {
            HomePageController root = new HomePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2); 
        });
    }
}
