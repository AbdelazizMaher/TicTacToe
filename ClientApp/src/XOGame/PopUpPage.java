package XOGame;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import XOGame.OfflinePage;
import java.util.function.BiConsumer;
import javafx.scene.control.Alert;

public class PopUpPage {

    private final Stage dialog;
    public static TextField username1;
    public static TextField username2;

    public PopUpPage(Stage owner, BiConsumer<String, String> onCloseClicked) {
        dialog = new Stage();
        dialog.setTitle("Enter Usernames");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label label1 = new Label("Player 1:");
        username1 = new TextField();
        Label label2 = new Label("Player 2:");
        username2 = new TextField();

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            String user1 = username1.getText().trim();
            String user2 = username2.getText().trim();
            if (!user1.isEmpty() && !user2.isEmpty() && !user1.equals(user2)) {
                OfflinePage.updatePlayerLabels(user1,user2);
                dialog.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                if(user1.equals(user2))
                    alert.setContentText("Both usernames must be different.");
                else
                    alert.setContentText("Both usernames must be entered.");
                alert.showAndWait();
            }
        });

        dialog.setOnCloseRequest(e -> {
            onCloseClicked.accept(null, null);
        });

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
}
