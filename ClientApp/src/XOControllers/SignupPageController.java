/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import static XOControllers.AvailableUserPageController.inGame;
import XOGame.HomePage;
import XOGame.SignupPage;
import java.util.Vector;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author eman_
 */
public class SignupPageController extends SignupPage {

    public SignupPageController(Stage stage) {
        registerButton.setOnAction(e -> {
            String name = usernameTextField.getText().trim();
            String pass = passwordTextField.getText().trim();;
            String conf = confirmPasswordTextField.getText().trim();
            String info = "signUp#@$" + name + "#@$" + pass + "#@$";
            if (!name.isEmpty() && !pass.isEmpty() && !conf.isEmpty()) {
                if (name.length() >= 6 && pass.length() >= 6 && conf.length() >= 6) {
                    if (pass.equals(conf)) {
                        if (ClientHandler.startConnection(info)) {
                            Thread thread = new Thread(() -> {
                                String message = ClientHandler.getResponse();
                                if (message.equals("Signed Up")) {
                                    HomePage.userName = name;
                                    Platform.runLater(() -> {
                                        inGame=false;
                                        Scene scene = new Scene(new AvailableUserPageController(stage));
                                        stage.setScene(scene);
                                    });
                                } else {
                                    Platform.runLater(() -> {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Server Error");
                                        alert.setHeaderText(null);
                                        alert.setContentText(message);
                                        alert.showAndWait();
                                    });
                                }
                            });
                            thread.setDaemon(true);
                            thread.start();
                        } else {
                            showAlert("Server Error", "server is out");
                        }
                    } else {
                        showAlert("SignUp Error", "Password and confirm password must be the same");
                    }
                } else if (name.length() < 6) {
                    showAlert("SignUp Error", "username must be at least 6 digits");
                } else if (pass.length() < 6) {
                    showAlert("SignUp Error", "password must be at least 6 digits");
                } else {
                    showAlert("SignUp Error", "confirm password must be at least 6 digits");
                }
            } else {
                showAlert("SignUp Error", "All fields can't be empty");
            }
        });

        backButton.setOnMouseClicked(e -> {
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
        });

        loginLabel.setOnMouseClicked(e -> {
            Scene scene = new Scene(new LoginPageController(stage));
            stage.setScene(scene);
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
