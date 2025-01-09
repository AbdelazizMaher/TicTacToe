/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import static ClientHandler.ClientHandler.getResponse;
import static ClientHandler.ClientHandler.sendRequest;
import XOGame.AvailableUsersPage;
import XOGame.OnlinePage;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    String onlineList = null;
    public AvailableUserPageController(Stage stage) {        
        Thread thread = new Thread(()->{
            while(true){
                try {
                    sendRequest("sendAvailablePlayers#@$");
                    onlineList = getResponse();
                    Platform.runLater(()->{
                        updateList();
                    });
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }            
        });
        thread.setDaemon(true);
        thread.start();
        
        
        backButton.setOnMouseClicked(e -> {
            HomePageController root = new HomePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2); 
        });
        AvailableUsersPage availableUsersPage = new AvailableUsersPage();
        Scene scene = new Scene(availableUsersPage);
        stage.setScene(scene);
        for(Button b : buttons){
            b.setOnAction(e -> {
            Scene scene2 = new Scene(new OnlinePageController(stage));
            stage.setScene(scene2); 
            });                 
        }
    }
    public void updateList(){
        rows.clear();
        buttons.clear();
        if (onlineList != null && !onlineList.isEmpty()) {
            String[] playersArray = onlineList.substring(1, onlineList.length() - 1).split(", ");
            for (String player : playersArray) {          

                if(!player.isEmpty()){
                    StringTokenizer info = new StringTokenizer(player, "#@$");
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
                }
            }
            listView.getItems().setAll(rows);       
        }
    }
}
