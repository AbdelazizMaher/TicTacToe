/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.SignupPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eman_
 */
public class SignupPageController extends SignupPage {

    public SignupPageController(Stage stage) {
        registerButton.setOnAction(e -> {
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
        }
        );

        backButton.setOnMouseClicked(e -> {
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
        });

        loginLabel.setOnMouseClicked(e -> {
            Scene scene = new Scene(new LoginPageController(stage));
            stage.setScene(scene);
        });
    }

}
