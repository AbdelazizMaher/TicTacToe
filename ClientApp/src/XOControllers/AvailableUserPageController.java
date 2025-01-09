/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.AvailableUsersPage;
import XOGame.OnlinePage;
import java.util.StringTokenizer;
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
    public AvailableUserPageController(Stage stage,String online) {
        rows.clear();
        buttons.clear();
        String[] playersArray = online.substring(1, online.length() - 1).split(", ");
        Platform.runLater(()->{
        for (String player : playersArray) {          
            System.out.println(player);
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
        listView.getItems().addAll(rows);       
        });
        
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
}
