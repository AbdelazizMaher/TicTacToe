/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOGame;

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class PopUpLogOut {
    public Stage popupStage;
    public Hyperlink yesLink;
    public Hyperlink noUpLink;

    public PopUpLogOut(Stage ownerStage) {
        this.popupStage = new Stage();
        this.popupStage.initModality(Modality.APPLICATION_MODAL);
        this.popupStage.initOwner(ownerStage);
        this.popupStage.setTitle("Log Out");

        Label messageLabel = new Label("Are you Sure you want to log out?");

        yesLink = new Hyperlink("yes");
        noUpLink = new Hyperlink("no");

        VBox popupLayout = new VBox(10, messageLabel, yesLink, noUpLink);
        popupLayout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene popupScene = new Scene(popupLayout, 250, 150);
        this.popupStage.setScene(popupScene);
    }

    public void show() {
        this.popupStage.showAndWait();
    }
}



