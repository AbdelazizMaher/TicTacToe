package XOGame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AvailableUsersPage extends AnchorPane {

    protected final VBox vBox;
    protected final AnchorPane anchorPane;
    protected final Button backButton;
    protected final ArrayList<Button> buttons;
    protected final Label label;
    protected final ScrollPane scrollPane;
    public  static ListView<VBox> listView;
    protected final ImageView imageView;
    protected ArrayList<VBox> rows = new ArrayList<>();
    public AvailableUsersPage() {
        vBox = new VBox();
        anchorPane = new AnchorPane();
        backButton = new Button();
        buttons = new ArrayList<>();
        label = new Label();
        scrollPane = new ScrollPane();
        listView = new ListView<>();
        imageView = new ImageView();

        // Root container setup
        setId("AnchorPane");
        setPrefHeight(580.0);
        setPrefWidth(780.0);

        // VBox setup
        vBox.setPrefHeight(580.0);
        vBox.setPrefWidth(780.0);

        // AnchorPane (Header) setup
        anchorPane.setPrefHeight(50.0);
        anchorPane.setPrefWidth(780.0);
        anchorPane.getStyleClass().add("sidebar");
        anchorPane.getStylesheets().add("/styles/Stylesheet.css");

        // Back button
        backButton.setLayoutX(8.0);
        backButton.setLayoutY(1.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(41.0);
        backButton.setPrefWidth(35.0);
        backButton.getStylesheets().add("/styles/Stylesheet.css");
        backButton.setTextFill(javafx.scene.paint.Color.WHITE);
        backButton.setFont(new Font("Arial", 12.0));

        // Label (Title)
        label.setLayoutX(329.0);
        label.setLayoutY(11.0);
        label.setText("Available Players");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Arial Bold", 18.0));

        // AnchorPane Header
        anchorPane.getChildren().addAll(backButton, label);

        // ScrollPane setup
        scrollPane.setPrefHeight(500.0); // Adjust the height to desired value
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Populate ListView
  
        listView.getItems().addAll(rows);

        // Add ListView to ScrollPane
        scrollPane.setContent(listView);

        // ImageView setup
        imageView.setFitHeight(404.0);
        imageView.setFitWidth(395.0);
        imageView.setLayoutX(373.0);
        imageView.setLayoutY(179.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("/media/squidgame.jpg").toExternalForm()));

        // Set layout constraints for ScrollPane in VBox
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        // Add components to VBox
        vBox.getChildren().addAll(anchorPane, scrollPane);

        // Add to root
        getChildren().addAll(vBox, imageView);
    }
}
