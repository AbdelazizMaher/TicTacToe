/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import static XOGame.HomePage.userName;
import XOGame.OnlinePage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class OnlinePageController extends OnlinePage{
    private boolean isPaused = false; 
    public OnlinePageController(Stage stage){
        backButton.setOnMouseClicked(e -> {
            AvailableUserPageController availablePage = new AvailableUserPageController(stage);
            Scene scene = new Scene(availablePage);
            stage.setScene(scene);
        });
        recordButton.setOnMouseClicked(e -> {
            Image recImage;
            if (isPaused) {
                recImage = new Image(getClass().getResourceAsStream("/media/record.png"));
            } else {
                recImage = new Image(getClass().getResourceAsStream("/media/stop.png"));
            }
            ImageView recImageView = new ImageView(recImage);
            recImageView.setFitHeight(40);
            recImageView.setFitWidth(40);
            recordButton.setGraphic(recImageView);         
            isPaused = !isPaused;
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(Stage stage, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Error");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
