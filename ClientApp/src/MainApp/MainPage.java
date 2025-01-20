package MainApp;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class MainPage extends AnchorPane {

    protected final ImageView imageView;
    protected final Button xoGameButton;
    protected final Button snakeLadderGameButton;
    protected final ImageView imageView0;

    public MainPage() {

        imageView = new ImageView();
        xoGameButton = new Button();
        snakeLadderGameButton = new Button();
        imageView0 = new ImageView();

        setId("AnchorPane");
        setPrefHeight(580.0);
        setPrefWidth(780.0);

        imageView.setFitHeight(580.0);
        imageView.setFitWidth(780.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("/media/SquidGameMenu.jpg").toExternalForm()));

        xoGameButton.setLayoutX(214.0);
        xoGameButton.setLayoutY(528.0);
        xoGameButton.setMnemonicParsing(false);
        xoGameButton.setPrefHeight(30.0);
        xoGameButton.setPrefWidth(150.0);
        xoGameButton.setStyle("-fx-background-color: #e61409;");
        xoGameButton.setText("XO Game");
        xoGameButton.setTextFill(javafx.scene.paint.Color.WHITE);
        xoGameButton.setFont(new Font("Arial Bold", 12.0));

        snakeLadderGameButton.setLayoutX(422.0);
        snakeLadderGameButton.setLayoutY(528.0);
        snakeLadderGameButton.setMnemonicParsing(false);
        snakeLadderGameButton.setPrefHeight(30.0);
        snakeLadderGameButton.setPrefWidth(150.0);
        snakeLadderGameButton.setStyle("-fx-background-color: #e61409;");
        snakeLadderGameButton.setText("Snake-Ladder Game");
        snakeLadderGameButton.setTextFill(javafx.scene.paint.Color.WHITE);
        snakeLadderGameButton.setFont(new Font("Arial Bold", 12.0));

        imageView0.setFitHeight(75.0);
        imageView0.setFitWidth(55.0);
        imageView0.setLayoutX(366.0);
        imageView0.setLayoutY(504.0);
        imageView0.setPickOnBounds(true);
        imageView0.setImage(new Image(getClass().getResource("/media/smallAvatars.jpg").toExternalForm()));

        getChildren().add(imageView);
        getChildren().add(xoGameButton);
        getChildren().add(snakeLadderGameButton);
        getChildren().add(imageView0);

    }
}
