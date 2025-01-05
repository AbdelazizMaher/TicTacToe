/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.HomePage;
import XOGame.HomePage;
import XOGame.OfflinePage;
import XOGame.OnlinePage;
import XOGame.VsCompPage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class HomePageController extends HomePage {

    public HomePageController(Stage stage) {

        signupButton.setOnAction(e -> {
            Scene scene = new Scene(new SignupPageController(stage));
            stage.setScene(scene);
        });

        loginButton.setOnAction(e -> {
            Scene scene = new Scene(new LoginPageController(stage));
            stage.setScene(scene);
        });

        historyButton.setOnAction(e -> {
            Scene scene = new Scene(new HistoryPageController(stage));
            stage.setScene(scene);
        });

        playvscomputerButton.setOnMouseClicked(e -> {
            DifficultyLevelController root = new DifficultyLevelController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        });

        playofflineButton.setOnMouseClicked(e -> {
            Scene scene2 = new Scene(new OfflinePageController(stage));
            stage.setScene(scene2);
            Platform.runLater(() -> {
                new PopUpPageController(stage);
            });
        });

        playonlineButton.setOnMouseClicked(e -> {
            Scene scene2 = new Scene(new AvailableUserPageController(stage));
            stage.setScene(scene2);
        });
    }
}
