/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import static ClientHandler.ClientHandler.sendRequest;
import java.util.StringTokenizer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Abdel
 */
public class IncomingInvitationController {

    public IncomingInvitationController(Stage stage) {
        Thread thread = new Thread(() -> {
            while (ClientHandler.isConnected()) {
                String message = ClientHandler.getResponse();
                StringTokenizer requestMsgTokens = new StringTokenizer(message, "#@$");

                String opponent = requestMsgTokens.nextToken();
                handleInvitationRequest(opponent, stage);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void handleInvitationRequest(String opponent, Stage stage) {
        Platform.runLater(() -> {
            boolean isInvitationAccepted = showRequestAlert("Game Invitation", "Player " + opponent + " has invited you to a game. Do you accept?");
            if (isInvitationAccepted) {
                sendRequest("invitationResponse" + "#@$" + "accept" + "#@$" + opponent);

                Scene scene = new Scene(new OnlinePageController(stage));
                stage.setScene(scene);
            } else {
                sendRequest("invitationResponse" + "#@$" + "decline" + "#@$" + opponent);
            }
        });
    }

    private boolean showRequestAlert(String title, String contentMessage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
