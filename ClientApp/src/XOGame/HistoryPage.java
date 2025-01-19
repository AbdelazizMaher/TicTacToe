package XOGame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public abstract class HistoryPage extends AnchorPane {

    protected final VBox vBox;
    protected final AnchorPane anchorPane;
    protected final Button backButton;
    protected final Label label;
    protected final ScrollPane scrollPane;
    protected final ListView listView;
    protected final ImageView imageView;

    public HistoryPage() {

        vBox = new VBox();
        anchorPane = new AnchorPane();
        backButton = new Button();
        label = new Label();
        scrollPane = new ScrollPane();
        listView = new ListView();
        imageView = new ImageView();

        setId("AnchorPane");
        setPrefHeight(580.0);
        setPrefWidth(780.0);

        vBox.setPrefHeight(580.0);
        vBox.setPrefWidth(780.0);

        anchorPane.setPrefHeight(70.0);
        anchorPane.setPrefWidth(780.0);
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
        label.setText("Player History");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Arial Bold", 18.0));

        scrollPane.setPrefHeight(580.0);
        scrollPane.setPrefWidth(780.0);

        listView.setPrefHeight(580.0);
        listView.setPrefWidth(780.0);
        scrollPane.setContent(listView);

        imageView.setFitHeight(404.0);
        imageView.setFitWidth(395.0);
        imageView.setLayoutX(373.0);
        imageView.setLayoutY(179.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("/media/squidgame.jpg").toExternalForm()));

        anchorPane.getChildren().add(backButton);
        anchorPane.getChildren().add(label);
        vBox.getChildren().add(anchorPane);
        vBox.getChildren().add(scrollPane);
        getChildren().add(vBox);
        getChildren().add(imageView);

    }
}
