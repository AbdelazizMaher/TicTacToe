package XOControllers;

import XOGame.PopUpPage;
import javafx.scene.Scene;
import javafx.stage.Stage;
import XOGame.OfflinePage;
import javafx.scene.Node;
import javafx.scene.control.Alert;

public class PopUpPageController {

    private final PopUpPage popUpPage;

    public PopUpPageController(Stage stage) {
        popUpPage = new PopUpPage(stage);
        popUpPage.okButton.setOnAction(e -> {
            String user1 = PopUpPage.username1.getText().trim();
            String user2 = PopUpPage.username2.getText().trim();
            if (!user1.isEmpty() && !user2.isEmpty() && !user1.equals(user2)) {               
                popUpPage.close();
                OfflinePage.updatePlayerLabels(user1, user2);
                OfflinePage.playOfflineLabel.setText("Play Offline");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                if (user1.equals(user2)) {
                    alert.setContentText("Both usernames must be different.");
                } else {
                    alert.setContentText("Both usernames must be entered.");
                }                
                alert.showAndWait();
            }
        });
        popUpPage.dialog.setOnCloseRequest(e -> {
            HomePageController homePage = new HomePageController(stage);
            Scene homeScene = new Scene(homePage);
            stage.setScene(homeScene);
        });
        popUpPage.show();
    }
}