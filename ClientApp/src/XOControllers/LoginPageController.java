/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.LoginPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eman_
 */
public class LoginPageController extends LoginPage {

    public LoginPageController(Stage stage) {

        loginButton.setOnAction(e -> {
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
        });

        backButton.setOnMouseClicked(e -> {
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
        });

        registerLabel.setOnMouseClicked(e -> {
            Scene scene = new Scene(new SignupPageController(stage));
            stage.setScene(scene);
        });

    }

}
