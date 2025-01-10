/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOGame;

/**
 *
 * @author nerme
 */

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpRegisterPage {
    public Stage popupStage;
    public Hyperlink loginLink;
    public Hyperlink signUpLink;
    public PopUpRegisterPage() {}

    public PopUpRegisterPage(Stage ownerStage) {
        this.popupStage = new Stage();
        this.popupStage.initModality(Modality.APPLICATION_MODAL);
        this.popupStage.initOwner(ownerStage);
        this.popupStage.setTitle("Authentication Required");

        Label messageLabel = new Label("You should login or sign up:");

        loginLink = new Hyperlink("Login");
        signUpLink = new Hyperlink("Sign Up");

        VBox popupLayout = new VBox(10, messageLabel, loginLink, signUpLink);
        popupLayout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene popupScene = new Scene(popupLayout, 250, 150);
        this.popupStage.setScene(popupScene);
    }

    public void show() {
        this.popupStage.showAndWait();
    }
}

