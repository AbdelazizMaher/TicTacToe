/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import XOGame.SignupPage;
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
            String pass = passwordTextField.getText().trim();
            String conf = confirmPasswordTextField.getText().trim();
            String info = "signUp#@$"+name+"#@$"+pass+"#@$";
                if(ClientHandler.startConnection(info)){               
                    Thread thread = new Thread(()->{ 
                        String message = ClientHandler.getResponse();
                        if(message.equals("Signed Up")){
                                Platform.runLater(()->{
                                    Scene scene = new Scene(new HomePageController(stage));
                                    stage.setScene(scene);
                                });
                            }
                            else{
                                Platform.runLater(()->{
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
