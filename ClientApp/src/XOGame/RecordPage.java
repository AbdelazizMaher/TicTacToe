package XOGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class RecordPage extends AnchorPane {

    protected Button[][] buttons = new Button[3][3];
    private String playerX = "Player X";
    private String playerO = "Player O";
    protected Label playerXLabel;
    protected Label playerOLabel;
    protected final Button backButton;
    protected final Button rewatchButton;
    protected final Button stopButton;
    protected BorderPane borderPane;

    public RecordPage(Stage stage) {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(200);
        anchorPane.setPrefWidth(320);

        ImageView imageView = new ImageView(new Image("/media/xo.jpg"));
        imageView.setFitHeight(580);
        imageView.setFitWidth(780);
        imageView.setPreserveRatio(false);

        borderPane = new BorderPane();
        borderPane.setPrefHeight(580);
        borderPane.setPrefWidth(780);

        BorderPane topSection = new BorderPane();
        HBox topHBox = new HBox();
        topHBox.setMaxWidth(Double.MAX_VALUE);
        topHBox.setMinWidth(Double.MIN_VALUE);
        topHBox.setPrefHeight(46);
        topHBox.setPrefWidth(780);
        topHBox.setStyle("-fx-background-color: black;");

        backButton = new Button();
        backButton.setPrefSize(60, 41);
        Image backImage = new Image(getClass().getResourceAsStream("/media/back2.png"));
        ImageView backImageView = new ImageView(backImage);
        backImageView.setFitHeight(40);
        backImageView.setFitWidth(40);
        backButton.setGraphic(backImageView);
        backButton.setStyle("-fx-background-color: transparent;");

        VBox playerOneVBox = new VBox();
        playerOneVBox.setAlignment(javafx.geometry.Pos.CENTER);
        playerOneVBox.setPrefHeight(48);
        playerOneVBox.setPrefWidth(589);
        playerOneVBox.setSpacing(5);

        Label playerOneLabel = new Label("Record");
        playerOneLabel.setPrefHeight(35);
        playerOneLabel.setPrefWidth(150);
        playerOneLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        playerOneLabel.setFont(new Font(24));
        playerOneLabel.setPadding(new Insets(0, 0, 0, 60));

        playerOneVBox.getChildren().add(playerOneLabel);

        topHBox.getChildren().addAll(backButton, playerOneVBox);
        topSection.setTop(topHBox);

        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(javafx.geometry.Pos.CENTER);
        bottomHBox.setSpacing(10);

        stopButton = new Button();
        stopButton.setMaxWidth(Double.MAX_VALUE);
        stopButton.setMinWidth(Double.MIN_VALUE);
        stopButton.setPrefHeight(38);
        stopButton.setPrefWidth(100);
        stopButton.setStyle("-fx-background-color: transparent;");
        Image stopImage = new Image(getClass().getResourceAsStream("/media/stop.png"));
        ImageView stopImageView = new ImageView(stopImage);
        stopImageView.setFitHeight(40);
        stopImageView.setFitWidth(40);
        stopButton.setGraphic(stopImageView);

        rewatchButton = new Button();
        rewatchButton.setMaxWidth(Double.MAX_VALUE);
        rewatchButton.setMinWidth(Double.MIN_VALUE);
        rewatchButton.setPrefHeight(38);
        rewatchButton.setPrefWidth(110);
        rewatchButton.setStyle("-fx-background-color: transparent;");
        Image rewatchImage = new Image(getClass().getResourceAsStream("/media/restart.png"));
        ImageView rewatchImageView = new ImageView(rewatchImage);
        rewatchImageView.setFitHeight(40);
        rewatchImageView.setFitWidth(40);
        rewatchButton.setGraphic(rewatchImageView);
        HBox.setMargin(rewatchButton, new Insets(0, 0, 0, 30));

        bottomHBox.getChildren().addAll(stopButton, rewatchButton);

        VBox centerVBox = new VBox();
        centerVBox.setAlignment(javafx.geometry.Pos.CENTER);
        centerVBox.setSpacing(10);

        AnchorPane playerXAnchorPane = new AnchorPane();
        playerXAnchorPane.setPrefHeight(50);
        playerXAnchorPane.setPrefWidth(780);

        playerXLabel = new Label(playerX + " - X");
        playerXLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        playerXLabel.setFont(new Font("Arial BOLD", 22));
        AnchorPane.setLeftAnchor(playerXLabel, 10.0); // Fixed position from the left
        AnchorPane.setTopAnchor(playerXLabel, 10.0);

        playerOLabel = new Label(playerO + " - O");
        playerOLabel.setFont(new Font("Arial BOLD", 22));
        playerOLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        AnchorPane.setRightAnchor(playerOLabel, 10.0); // Fixed position from the right
        AnchorPane.setTopAnchor(playerOLabel, 10.0);

        playerXAnchorPane.getChildren().addAll(playerXLabel, playerOLabel);

        GridPane gridPane = new GridPane();
        gridPane.setMaxHeight(Double.MAX_VALUE);
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.setPrefHeight(421);
        gridPane.setPrefWidth(450);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button cell = new Button();
                cell.setPrefSize(150, 150);
                cell.setStyle("-fx-background-color: rgba(169, 169, 169, 0.5); -fx-border-color: red; -fx-text-fill: black;");
                buttons[row][col] = cell;
                GridPane.setRowIndex(cell, row);
                GridPane.setColumnIndex(cell, col);
                gridPane.getChildren().add(cell);
            }
        }
        centerVBox.getChildren().addAll(playerXAnchorPane, gridPane);

        borderPane.setTop(topSection);
        borderPane.setCenter(centerVBox);
        borderPane.setBottom(bottomHBox);

        anchorPane.getChildren().addAll(imageView, borderPane);

        anchorPane.setPrefSize(780, 580);

        setPrefSize(780, 580);
        getChildren().add(anchorPane);
    }
}
