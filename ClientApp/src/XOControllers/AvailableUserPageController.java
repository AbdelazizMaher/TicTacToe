/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import static ClientHandler.ClientHandler.getResponse;
import ClientHandler.ClientHandler;
import static ClientHandler.ClientHandler.sendRequest;
import XOGame.AvailableUsersPage;
import static XOGame.HomePage.username;
import static XOGame.OnlinePage.playerX;
import static XOGame.OnlinePage.playerO;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author nerme
 */
public class AvailableUserPageController extends AvailableUsersPage{
    String onlineList = "";
    String opponentName;
    public AvailableUserPageController(Stage stage) {
        
        backButtonEvent(stage);
        Thread thread = new Thread(() -> {
            while (ClientHandler.isConnected()) {
                sendRequest("sendAvailablePlayers#@$");
                String serverResponse = ClientHandler.getResponse();
                StringTokenizer responseMsgTokens = new StringTokenizer(serverResponse, "#@$");
                String status = responseMsgTokens.nextToken();
                switch (status) {
                    case "sendAvailablePlayers":{
                            onlineList = responseMsgTokens.nextToken();                            
                        if(!onlineList.isEmpty()){
                            Platform.runLater(()->{
                            updateList();
                            });
                        }
                        break;
                    }                 
                    case "invitation":                       
                        String opponent = responseMsgTokens.nextToken();
                        opponentName=opponent;
                        handleInvitationRequest(opponent, stage);
                        break;
                    case "accepted":
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.initOwner(stage);
                            alert.setTitle("Accepted");
                            alert.setContentText("your inivitation has been accepted");
                            alert.showAndWait();
                            playerX = username;
                            playerO = opponentName;
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
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                                break;                
                }                    
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void handleClickedButtonInvitation(String player) {
   
        for (Button button : buttons) {
            button.setOnAction(e -> {
                ClientHandler.sendRequest("sendInvitaion" + "#@$" + player + "#@"); //replace with playerName from listView
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
                sendRequest("invitationResponse" + "#@$" + "accept" + "#@$" + opponent);

                Scene scene = new Scene(new OnlinePageController(stage));
                stage.setScene(scene);
            } else {
                sendRequest("invitationResponse" + "#@$" + "decline" + "#@$" + opponent);
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
    public void updateList(){
        rows.clear();
        buttons.clear();
        if (onlineList != null && !onlineList.isEmpty()) {
            String[] playersArray = onlineList.substring(1, onlineList.length() - 1).split(", ");
            for (String player : playersArray) {          

                if(!player.isEmpty()){
                    StringTokenizer info = new StringTokenizer(player, "*");
                    VBox row = new VBox(5);
                    Label playerName = new Label(info.nextToken());
                    playerName.setFont(new Font("Arial", 16));
                    Label playerScore = new Label(info.nextToken());
                    playerScore.setFont(new Font("Arial", 14));
                    Button inviteButton = new Button("Send Invitation");
                    inviteButton.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 14px; -fx-underline: true;");
                    buttons.add(inviteButton);
                    row.getChildren().addAll(playerName, playerScore, inviteButton);
                    rows.add(row);
                    handleClickedButtonInvitation(playerName.getText());
                }
            }
            listView.getItems().setAll(rows);       
        }
    }
}
