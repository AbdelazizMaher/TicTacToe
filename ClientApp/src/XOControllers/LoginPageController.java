/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import static ClientHandler.ClientHandler.getAvailablePlayers;
import XOGame.LoginPage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author eman_
 */
public class LoginPageController extends LoginPage {

    public LoginPageController(Stage stage) {

        loginButton.setOnAction(e -> {
            String name = usernameTextField.getText().trim();
            String pass = passwordTextField.getText().trim();
            String info = "signIn#@$"+name+"#@$"+pass+"#@$";
            if(!name.isEmpty() && !pass.isEmpty()){
                if(ClientHandler.startConnection(info)){
                    Thread thread = new Thread(()->{
                        String message = ClientHandler.getResponse();
                        if(message.equals("Signed In")){
                                Platform.runLater(()->{
                                    String online = getAvailablePlayers("sendAvailablePlayers#@$");
                                    Scene scene = new Scene(new AvailableUserPageController(stage,online));
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
                }else{
                    showAlert("Server Error","server is out");
                }}
                else{
                   showAlert("SignIn Error","All fields can't be empty");     
                }
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
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
