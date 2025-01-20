/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApp;

import SnakeLadderGame.SnakesAndLaddersController;
import XOControllers.HomePageController;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Adham
 */
public class MainPageController extends MainPage {

    public MainPageController(Stage stage) {
        xoGameButton.setOnMouseClicked(e -> {
            HomePageController root = new HomePageController(stage);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        });

        snakeLadderGameButton.setOnMouseClicked(e -> {
            SnakesAndLaddersController root = new SnakesAndLaddersController(stage);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        });

    }

}
