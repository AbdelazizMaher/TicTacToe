/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import static ClientHandler.ClientHandler.sendRequest;
import XOGame.AvailableUsersPage;
import XOGame.OnlinePage;
import java.util.StringTokenizer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 * @author nerme
 */
public class AvailableUserPageController extends AvailableUsersPage {

    public AvailableUserPageController(Stage stage) {
        backButtonEvent(stage);
        handleClickedButtonInvitation();

        Thread thread = new Thread(() -> {
            while (ClientHandler.isConnected()) {
                String serverResponse = ClientHandler.getResponse();
                StringTokenizer responseMsgTokens = new StringTokenizer(serverResponse, "#@$");

                String status = responseMsgTokens.nextToken();
                switch (status) {
                    case "invitation":
                        String opponent = responseMsgTokens.nextToken();
                        handleInvitationRequest(opponent, stage);
                        break;
                    case "accepted":
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.initOwner(stage);
                            alert.setTitle("Accepted");
                            alert.setContentText("your inivitation has been accepted");
                            alert.showAndWait();
                            Scene scene = new Scene(new OnlinePageController(stage));
                            stage.setScene(scene);
                        });
                        break;
                    case "declined":
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.initOwner(stage);
                            alert.setTitle("declined");
                            alert.setContentText("your inivitation has been declined");
                            alert.showAndWait();
                        });
                        break;
                    case "Error":
                        Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.initOwner(stage);
                                alert.setTitle("Error");
                                alert.setContentText("Failed to connect to client");
                                alert.showAndWait();
                            });
                        break;
                }
            }
        }
        );
        thread.setDaemon(true);
        thread.start();
    }

    private void handleClickedButtonInvitation() {
        for (Button button : buttons) {
            button.setOnAction(e -> {
                String messageRequest = "sendInvitaion" + "#@$" + "zizo" + "#@";   //replace with playerName from listView
                ClientHandler.sendRequest(messageRequest);
            });
        }
    }

    private void backButtonEvent(Stage stage) {
        backButton.setOnMouseClicked(e -> {
            HomePageController root = new HomePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        });
    }

    private void handleInvitationRequest(String opponent, Stage stage) {
        Platform.runLater(() -> {
            boolean isInvitationAccepted = showRequestAlert("Game Invitation", "Player " + opponent + " has invited you to a game. Do you accept?", stage);
            if (isInvitationAccepted) {
                sendRequest("invitationResponse" + "#@$" + "accept" + "#@$" + "hello");

                Scene scene = new Scene(new OnlinePageController(stage));
                stage.setScene(scene);
            } else {
                sendRequest("invitationResponse" + "#@$" + "decline" + "#@$" + "hello");
            }
        });
    }

    private boolean showRequestAlert(String title, String contentMessage, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentMessage);

        ButtonType acceptButton = new ButtonType("Accept");
        ButtonType declineButton = new ButtonType("Decline");
        alert.getButtonTypes().setAll(acceptButton, declineButton);

        final Boolean[] retVal = {false};
        alert.showAndWait().ifPresent(response -> {
            if (response == acceptButton) {
                retVal[0] = true;
            } else if (response == declineButton) {
                retVal[0] = false;
            }
        });

        return retVal[0];
    }
}
