/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import XOGame.AvailableUsersPage;
import XOGame.OnlinePage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 * @author nerme
 */
public class AvailableUserPageController extends AvailableUsersPage{
    String messageRequest;
    
    public AvailableUserPageController(Stage stage) {
        backButton.setOnMouseClicked(e -> {
            HomePageController root = new HomePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2); 
        });
        
//        AvailableUsersPage availableUsersPage = new AvailableUsersPage();    //WHY ?????
//        Scene scene = new Scene(availableUsersPage);
//        stage.setScene(scene);
        for(Button b : buttons){
            b.setOnAction(e -> {
//                messageRequest="send invitaion#@$"+"chico"+"#@$"+pass+"#@$";
//                ClientHandler.sendRequest(text);
                
                
                
                
            Scene scene2 = new Scene(new OnlinePageController(stage));
            stage.setScene(scene2); 
            });                 
        }
    }
}
