package XOGame;

import XOControllers.OnlinePageController;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AvailableUsersPage extends AnchorPane {

    protected final VBox vBox;
    protected final AnchorPane anchorPane;
    protected final Button backButton;
    protected final ArrayList<Button> buttons;
    protected final Label label;
    protected final ScrollPane scrollPane;
    public final ListView<HBox> listView;

    public AvailableUsersPage() {
        vBox = new VBox();
        anchorPane = new AnchorPane();
        backButton = new Button();
        buttons = new ArrayList<>();
        label = new Label();
        scrollPane = new ScrollPane();
        listView = new ListView<>();

        setId("AnchorPane");
        setPrefHeight(580.0);
        setPrefWidth(780.0);

        vBox.setPrefHeight(580.0);
        vBox.setPrefWidth(780.0);

        anchorPane.setPrefHeight(50.0);
        anchorPane.setPrefWidth(789.0);
        anchorPane.getStyleClass().add("sidebar");
        anchorPane.getStylesheets().add("/styles/Stylesheet.css");

        backButton.setLayoutX(8.0);
        backButton.setLayoutY(1.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(41.0);
        backButton.setPrefWidth(35.0);
        backButton.getStylesheets().add("/styles/Stylesheet.css");
        backButton.setTextFill(javafx.scene.paint.Color.WHITE);
        backButton.setFont(new Font("Arial", 12.0));

        label.setLayoutX(329.0);
        label.setLayoutY(11.0);
        label.setText("Available Players");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Arial Bold", 18.0));
        
        anchorPane.setPadding(new Insets(7, 0, 0, 0));
        anchorPane.getChildren().addAll(backButton, label);

        scrollPane.setPrefHeight(580.0);
        scrollPane.setPrefWidth(780.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        ArrayList<HBox> rows = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String playerName = "Player " + (i + 1);
            int playerScore = (i + 2) * 20 + 60;

            HBox row = new HBox(20);
            Label nameLabel = new Label(playerName);
            nameLabel.setFont(new Font("Arial", 16));

            Label scoreLabel = new Label("Score: " + playerScore);
            scoreLabel.setFont(new Font("Arial", 16));

            Button sendButton = new Button("Send Invitation " + (i + 1));
            sendButton.setStyle("-fx-background-color: transparent; " +"-fx-text-fill: blue; " + "-fx-font-size: 14px; "+"-fx-underline: true;");
            buttons.add(sendButton);
            row.getChildren().addAll(nameLabel, scoreLabel, sendButton);
            rows.add(row);
        }

        listView.getItems().addAll(rows);
        scrollPane.setContent(listView);

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        vBox.getChildren().addAll(anchorPane, scrollPane);
        getChildren().add(vBox);
    }
}
