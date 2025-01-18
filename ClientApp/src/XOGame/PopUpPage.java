package XOGame;

import XOControllers.HomePageController;
import XOControllers.OfflinePageController;
import static XOGame.OfflinePage.playerO;
import static XOGame.OfflinePage.playerX;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpPage {

    public final Stage dialog;
    public static TextField username1;
    public static TextField username2;
    public Button okButton;

    public PopUpPage(Stage stage) {
        dialog = new Stage();
        dialog.setTitle("Enter Usernames");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label label1 = new Label("Player 1:");
        username1 = new TextField();
        Label label2 = new Label("Player 2:");
        username2 = new TextField();

        okButton = new Button("OK");

        grid.add(label1, 0, 0);
        grid.add(username1, 1, 0);
        grid.add(label2, 0, 1);
        grid.add(username2, 1, 1);
        grid.add(okButton, 1, 2);

        Scene dialogScene = new Scene(grid, 300, 150);
        dialog.setScene(dialogScene);
    }

    public void show() {
        dialog.showAndWait();
    }

    public void close() {
        dialog.close();
    }
}
