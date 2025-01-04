/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.HomePage;
import XOGame.OfflinePage;
import XOGame.OnlinePage;
import XOGame.VsCompPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class HomePageController extends HomePage{
    public HomePageController(Stage stage){
        playvscomputerButton.setOnMouseClicked(e -> {
            VsCompPage root = new VsCompPageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2); 
        });
        playofflineButton.setOnMouseClicked(e -> {
            OfflinePage root = new OfflinePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2); 
        });
        playonlineButton.setOnMouseClicked(e -> {
            OnlinePage root = new OnlinePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2); 
        });
    }
}