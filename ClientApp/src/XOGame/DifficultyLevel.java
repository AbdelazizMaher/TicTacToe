package XOGame;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class DifficultyLevel extends AnchorPane {

    protected final AnchorPane mainAnchorPane;
    protected final Button backButton;
    protected final Label label;
    protected final ImageView iconImageView;
    protected final ImageView imageView;
    protected final ImageView barImageView;
    protected final HBox hBox;
    protected final Button easyLevelButton;
    protected final ImageView easyLevelImageView;
    protected final Button mediumLevelButton;
    protected final ImageView mediumLevelImageView;
    protected final Button hardLevelButton;
    protected final ImageView hardLevelImageView;

    public DifficultyLevel() {

        mainAnchorPane = new AnchorPane();
        backButton = new Button();
        label = new Label();
        iconImageView = new ImageView();
        imageView = new ImageView();
        barImageView = new ImageView();
        hBox = new HBox();
        easyLevelButton = new Button();
        easyLevelImageView = new ImageView();
        mediumLevelButton = new Button();
        mediumLevelImageView = new ImageView();
        hardLevelButton = new Button();
        hardLevelImageView = new ImageView();

        setId("AnchorPane");
        setPrefHeight(580.0);
        setPrefWidth(780.0);

        mainAnchorPane.setPrefHeight(45.0);
        mainAnchorPane.setPrefWidth(789.0);
        mainAnchorPane.getStyleClass().add("sidebar");
        mainAnchorPane.getStylesheets().add("/styles/Stylesheet.css");
        mainAnchorPane.setPadding(new Insets(9, 1, 0, 0));
        
        backButton.setLayoutX(8.0);
        backButton.setLayoutY(1.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(41.0);
        backButton.setPrefWidth(35.0);
        backButton.getStylesheets().add("/styles/Stylesheet.css");
        backButton.setTextFill(javafx.scene.paint.Color.WHITE);
        backButton.setFont(new Font("Arial", 12.0));

        label.setLayoutX(279.0);
        label.setLayoutY(12.0);
        label.setText("Choose the Difficulty Level");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Arial Bold", 18.0));

        iconImageView.setFitHeight(28.0);
        iconImageView.setFitWidth(50.0);
        iconImageView.setLayoutX(738.0);
        iconImageView.setLayoutY(9.0);
        iconImageView.setPickOnBounds(true);
        iconImageView.setPreserveRatio(true);
        iconImageView.setImage(new Image(getClass().getResource("/media/xo.jpg").toExternalForm()));

        imageView.setFitHeight(542.0);
        imageView.setFitWidth(789.0);
        imageView.setLayoutY(47.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("/media/xo.jpg").toExternalForm()));

        barImageView.setFitHeight(174.0);
        barImageView.setFitWidth(817.0);
        barImageView.setLayoutX(-28.0);
        barImageView.setLayoutY(416.0);
        barImageView.setPickOnBounds(true);
        barImageView.setImage(new Image(getClass().getResource("/media/squids.png").toExternalForm()));

        hBox.setLayoutY(46.0);
        hBox.setPrefHeight(341.0);
        hBox.setPrefWidth(789.0);
        hBox.setSpacing(20.0);
        hBox.setPadding(new Insets(200.0, 0.0, 0.0, 20.0));

        easyLevelButton.setMnemonicParsing(false);
        easyLevelButton.setPrefHeight(138.0);
        easyLevelButton.setPrefWidth(235.0);
        easyLevelButton.setStyle("-fx-background-color: #131218;");
        easyLevelButton.getStyleClass().add("button-edge");
        easyLevelButton.getStylesheets().add("/styles/Stylesheet.css");
        easyLevelButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        easyLevelButton.setTextFill(javafx.scene.paint.Color.WHITE);
        easyLevelButton.setFont(new Font("Arial Bold", 14.0));

        easyLevelImageView.setFitHeight(100.0);
        easyLevelImageView.setFitWidth(185.0);
        easyLevelImageView.setPickOnBounds(true);
        easyLevelImageView.setImage(new Image(getClass().getResource("/media/level1.png").toExternalForm()));
        easyLevelButton.setGraphic(easyLevelImageView);

        mediumLevelButton.setMnemonicParsing(false);
        mediumLevelButton.setPrefHeight(138.0);
        mediumLevelButton.setPrefWidth(235.0);
        mediumLevelButton.setStyle("-fx-background-color: #131218;");
        mediumLevelButton.getStyleClass().add("button-edge");
        mediumLevelButton.getStylesheets().add("/FXML/../styles/Stylesheet.css");
        mediumLevelButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        mediumLevelButton.setTextFill(javafx.scene.paint.Color.WHITE);
        mediumLevelButton.setFont(new Font("Arial Bold", 14.0));

        mediumLevelImageView.setFitHeight(100.0);
        mediumLevelImageView.setFitWidth(185.0);
        mediumLevelImageView.setPickOnBounds(true);
        mediumLevelImageView.setImage(new Image(getClass().getResource("/media/level2.png").toExternalForm()));
        mediumLevelButton.setGraphic(mediumLevelImageView);

        hardLevelButton.setMnemonicParsing(false);
        hardLevelButton.setPrefHeight(138.0);
        hardLevelButton.setPrefWidth(235.0);
        hardLevelButton.setStyle("-fx-background-color: #131218;");
        hardLevelButton.getStyleClass().add("button-edge");
        hardLevelButton.getStylesheets().add("/styles/Stylesheet.css");
        hardLevelButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        hardLevelButton.setTextFill(javafx.scene.paint.Color.WHITE);
        hardLevelButton.setFont(new Font("Arial Bold", 14.0));

        hardLevelImageView.setFitHeight(100.0);
        hardLevelImageView.setFitWidth(185.0);
        hardLevelImageView.setPickOnBounds(true);
        hardLevelImageView.setImage(new Image(getClass().getResource("/media/level3.png").toExternalForm()));
        hardLevelButton.setGraphic(hardLevelImageView);

        mainAnchorPane.getChildren().add(backButton);
        mainAnchorPane.getChildren().add(label);
        mainAnchorPane.getChildren().add(iconImageView);
        getChildren().add(mainAnchorPane);
        getChildren().add(imageView);
        getChildren().add(barImageView);
        hBox.getChildren().add(easyLevelButton);
        hBox.getChildren().add(mediumLevelButton);
        hBox.getChildren().add(hardLevelButton);
        getChildren().add(hBox);

    }
}
