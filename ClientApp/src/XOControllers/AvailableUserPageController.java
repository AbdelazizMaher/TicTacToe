/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import XOGame.AvailableUsersPage;
import XOGame.OnlinePage;
import java.util.StringTokenizer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 * @author nerme
 */
public class AvailableUserPageController extends AvailableUsersPage {

    String messageRequest;
    String serverResponse;
    String message;
    StringTokenizer responseMsgTokens;

    Thread thread;

    public AvailableUserPageController(Stage stage) {

        thread = new Thread(() -> {
            
            while (true) {

                serverResponse = ClientHandler.getResponse();

                if (serverResponse != null) {
                    
                    responseMsgTokens = new StringTokenizer(serverResponse, "#@$");
                    
                    String status = responseMsgTokens.nextToken();
                    
                    switch (status) {
                        case "invitation": {
                            message = responseMsgTokens.nextToken();
                             System.out.println(message);
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Game Invitation");
                                alert.setHeaderText(message);
                                alert.showAndWait();
                            });
                        }

                        break;

                        case "success": {
                            message = responseMsgTokens.nextToken();
                             System.out.println(message);
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("success");
                                // alert.setHeaderText("You've received an invitation!");
                                alert.setContentText(message);
                                alert.show();
                            });
                        }

                        break;

                        case "Error": {
                            message = responseMsgTokens.nextToken();
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setContentText(message);
                                alert.showAndWait();
                            });
                        }

                        break;

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    //alert.setHeaderText("You've received an invitation!");
                    alert.setContentText("server Error");
                    alert.showAndWait();

                }
            }

        }
        );
        thread.start();

        backButton.setOnMouseClicked(e -> {
            HomePageController root = new HomePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        });

        AvailableUsersPage availableUsersPage = new AvailableUsersPage();    //WHY ?????
        Scene scene = new Scene(availableUsersPage);
        stage.setScene(scene);
        for (Button b : buttons) {
            b.setOnAction(e -> {
                messageRequest = "send invitaion#@$" + "chio" + "#@";   //replace with playerName
                ClientHandler.sendRequest(messageRequest);
                System.out.println("clicked on invitatiiiiiiiion");

//            Scene scene2 = new Scene(new OnlinePageController(stage));
//            stage.setScene(scene2); 
            });
        }
    }
}
