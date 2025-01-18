/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.PopUpLogOut;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static XOGame.HomePage.userName;
import ClientHandler.ClientHandler;

/**
 *
 * @author nerme
 */
public class PopUpLogOutController extends PopUpLogOut {

    public PopUpLogOutController(Stage stage) {
        super(stage);
        yesLink.setOnAction(e -> {
            userName = "";
            String info = "logout#@$";
            ClientHandler.sendRequest(info);
            ClientHandler.closeConnection();
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
            popupStage.close();
        });
        noUpLink.setOnAction(e -> {
            popupStage.close();
        });
    }
}
