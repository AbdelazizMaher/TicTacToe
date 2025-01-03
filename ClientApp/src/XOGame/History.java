package XOGame;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class History extends AnchorPane {

    protected final VBox vBox;
    protected final AnchorPane anchorPane;
    protected final HBox hBox;
    protected final Label playerLabel;
    protected final Label historyLabel;
    protected final ImageView avatarImageView;
    protected final Button historyButton;
    protected final ScrollPane scrollPane;
    protected final ListView listView;
    protected final ImageView mainImageView;

    public History() {

        vBox = new VBox();
        anchorPane = new AnchorPane();
        hBox = new HBox();
        playerLabel = new Label();
        historyLabel = new Label();
        avatarImageView = new ImageView();
        historyButton = new Button();
        scrollPane = new ScrollPane();
        listView = new ListView();
        mainImageView = new ImageView();

        setId("AnchorPane");
        setPrefHeight(580.0);
        setPrefWidth(780.0);
        getStylesheets().add("/styles/Stylesheet.css");

        vBox.setPrefHeight(580.0);
        vBox.setPrefWidth(780.0);

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);
        anchorPane.getStyleClass().add("sidebar");
        anchorPane.getStylesheets().add("/styles/Stylesheet.css");

        hBox.setLayoutX(206.0);
        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(330.0);

        playerLabel.setPrefHeight(57.0);
        playerLabel.setPrefWidth(73.0);
        playerLabel.setText("Player ");
        playerLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playerLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        playerLabel.setFont(new Font("Arial Bold", 18.0));

        historyLabel.setPrefHeight(57.0);
        historyLabel.setPrefWidth(73.0);
        historyLabel.setText("History");
        historyLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        historyLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        historyLabel.setFont(new Font("Arial Bold", 18.0));
        hBox.setPadding(new Insets(20.0, 0.0, 0.0, 100.0));

        avatarImageView.setFitHeight(75.0);
        avatarImageView.setFitWidth(50.0);
        avatarImageView.setLayoutX(730.0);
        avatarImageView.setLayoutY(14.0);
        avatarImageView.setPickOnBounds(true);
        avatarImageView.setPreserveRatio(true);
        avatarImageView.setImage(new Image(getClass().getResource("/media/avatar.png").toExternalForm()));

        historyButton.setLayoutX(14.0);
        historyButton.setLayoutY(9.0);
        historyButton.setMnemonicParsing(false);
        historyButton.setPrefHeight(48.0);
        historyButton.setPrefWidth(50.0);
        historyButton.getStylesheets().add("/styles/Stylesheet.css");
        historyButton.setTextFill(javafx.scene.paint.Color.WHITE);
        historyButton.setFont(new Font("Arial", 12.0));

        scrollPane.setPrefHeight(580.0);
        scrollPane.setPrefWidth(780.0);

        listView.setPrefHeight(479.0);
        listView.setPrefWidth(780.0);
        listView.getItems().addAll("Item 1", "Item 2", "Item 3", "Item 4");
        scrollPane.setContent(listView);

        mainImageView.setFitHeight(371.0);
        mainImageView.setFitWidth(209.0);
        mainImageView.setLayoutX(554.0);
        mainImageView.setLayoutY(190.0);
        mainImageView.setPickOnBounds(true);
        mainImageView.setImage(new Image(getClass().getResource("/media/o.jpg").toExternalForm()));

        hBox.getChildren().add(playerLabel);
        hBox.getChildren().add(historyLabel);
        anchorPane.getChildren().add(hBox);
        anchorPane.getChildren().add(avatarImageView);
        anchorPane.getChildren().add(historyButton);
        vBox.getChildren().add(anchorPane);
        vBox.getChildren().add(scrollPane);
        getChildren().add(vBox);
        getChildren().add(mainImageView);

    }
}
