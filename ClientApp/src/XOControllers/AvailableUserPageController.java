/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import static ClientHandler.ClientHandler.sendRequest;
import XOGame.AvailableUsersPage;
import static XOGame.HomePage.userName;
import static XOGame.OnlinePage.playerO;
import static XOGame.OnlinePage.playerX;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.application.Platform;

/**
 *
 * @author nerme
 */
public class AvailableUserPageController extends AvailableUsersPage {

    static String onlineList = "";

    protected static Thread thread;
    protected static boolean inGame;
    public static boolean isStarting;

    static int userScore;
    static int opponentscore;

    private Button sendButton;


    public AvailableUserPageController(Stage stage) {
        
        sendRequest("sendAvailablePlayers" + "#@$");
        backButtonEvent(stage);
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(() -> {
                while (!inGame) {
                    String serverResponse = ClientHandler.getResponse();
                    System.out.println("response" + serverResponse);
                    StringTokenizer responseMsgTokens = new StringTokenizer(serverResponse, "#@$");
                    String status = responseMsgTokens.nextToken();
                    switch (status) {
                        case "sendAvailablePlayers":
                            onlineList = responseMsgTokens.nextToken();
                            if (!onlineList.isEmpty()) {
                                updateList();
                            }
                            break;
                        case "invitation":
                            inGame = false;
                            isStarting = false;
                            playerX = null;
                            playerO = null;
                            OnlinePageController.opponentName = responseMsgTokens.nextToken();
                            handleInvitationRequest(OnlinePageController.opponentName, stage);
                            OnlinePageController.score1 = opponentscore = Integer.parseInt(responseMsgTokens.nextToken());
                            OnlinePageController.score2 = userScore = Integer.parseInt(responseMsgTokens.nextToken());
                            break;
                        case "accepted":
                            Platform.runLater(() -> {    
                                OnlinePageController.exit = false;
                                OnlinePageController.again=false;
                                showInformationAlert(stage, "your inivitation has been accepted");
                                OnlinePageController.opponentName = responseMsgTokens.nextToken();
                                OnlinePageController.score1 = userScore = Integer.parseInt(responseMsgTokens.nextToken());
                                OnlinePageController.score2 = opponentscore = Integer.parseInt(responseMsgTokens.nextToken());
                                setPlayersNames(userName, OnlinePageController.opponentName);
                                inGame = true;
                                isStarting = true;
                                Scene scene = new Scene(new OnlinePageController(stage));
                                stage.setScene(scene);
                                sendButton.setDisable(false);
                            });
                            thread.stop();
                             {
                                try {
                                    thread.join();
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(AvailableUserPageController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                        case "declined":
                            Platform.runLater(() -> {
                                showErrorAlert(stage, "your inivitation has been declined");
                                inGame = false;
                                isStarting = false;
                                playerX = null;
                                playerO = null;
                                sendButton.setDisable(false);
                            });
                            break;
                        case "Error":
                            Platform.runLater(() -> {
                                showErrorAlert(stage, "Failed to connect to client");
                            });
                            break;
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
    }

    private void handleClickedButtonInvitation(Button button, String player) {
        sendButton = button;
        button.setOnAction(e -> {
            inGame = false;
            isStarting = false;
            playerX = null;
            playerO = null;
            //updateBoardState(false);
            ClientHandler.sendRequest("sendInvitaion" + "#@$" + player + "#@$");
            button.setDisable(true);
        });
    }

    private void backButtonEvent(Stage stage) {
        backButton.setOnMouseClicked(e -> {
            Platform.runLater(() -> {
                rows.clear();
                buttons.clear();
                Scene scene2 = new Scene(new HomePageController(stage));
                stage.setScene(scene2);
            });
        });
    }

    private void handleInvitationRequest(String opponent, Stage stage) {
        Platform.runLater(() -> {
            boolean isInvitationAccepted = showRequestAlert("Game Invitation", "Player " + opponent + " has invited you to a game. Do you accept?", stage);
            inGame = isInvitationAccepted;
            if (isInvitationAccepted) {
                setPlayersNames(opponent, userName);
                sendRequest("invitationResponse" + "#@$" + "accept" + "#@$" + opponent);
                //isStarting = true;
                OnlinePageController.exit = false;
                OnlinePageController.again=false;
                Scene scene = new Scene(new OnlinePageController(stage));
                stage.setScene(scene);
            } else {
                inGame = false;
                isStarting = false;
                playerX = null;
                playerO = null;
                sendRequest("invitationResponse" + "#@$" + "decline" + "#@$" + opponent);
            }
        });

        if (inGame) {
            try {
                thread.stop();
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(AvailableUserPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        final boolean[] responseReceived = {false};

        Timeline timeout = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            if (!responseReceived[0]) {
                alert.setResult(declineButton);
                alert.hide();
            }
        }));
        timeout.setCycleCount(1);
        timeout.play();

        alert.showAndWait().ifPresent(response -> {
            responseReceived[0] = true;
            if (response == acceptButton) {
                retVal[0] = true;
            } else if (response == declineButton) {
                retVal[0] = false;
            }
        });

        timeout.stop();

        return retVal[0];
    }

    private void updateList() {
        Platform.runLater(() -> {
            rows.clear();
            buttons.clear();
            if (onlineList != null && !onlineList.isEmpty()) {
                String[] playersArray = onlineList.substring(1, onlineList.length() - 1).split(", ");
                for (String player : playersArray) {
                    if (!player.isEmpty()) {
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

                        handleClickedButtonInvitation(inviteButton, playerName.getText());
                    }
                }
                listView.getItems().setAll(rows);
            }
        });
    }

    private void setPlayersNames(String player1, String player2) {
        playerX = player1;
        playerO = player2;
    }

    private void showErrorAlert(Stage stage, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Error");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void showInformationAlert(Stage stage, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Accepted");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
