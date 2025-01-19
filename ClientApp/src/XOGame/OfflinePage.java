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
import static XOGame.PopUpPage.username1;
import static XOGame.PopUpPage.username2;

public abstract class OfflinePage extends AnchorPane {

    public int score1;
    public int score2;
    public GridPane gridPane;
    public Button[][] buttons = new Button[3][3];
    public static String playerX = "Player1";
    public static String playerO = "Player2";
    public static Label playOfflineLabel;
    protected Button backButton;
    protected Button replayButton;
    protected Button recordButton;
    public static Label playerXLabel;
    public static Label playerOLabel;
    public Label scoreLabelX;
    public BorderPane borderPane;
    public static String user1;
    public static String user2;

    public OfflinePage() {
        score1 = 0;
        score2 = 0;

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(200);
        anchorPane.setPrefWidth(320);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(580);
        imageView.setFitWidth(780);
        imageView.setPreserveRatio(false);
        imageView.setImage(new Image(getClass().getResource("/media/xo.jpg").toExternalForm()));

        borderPane = new BorderPane();
        borderPane.setPrefHeight(580);
        borderPane.setPrefWidth(780);

        BorderPane topSection = new BorderPane();
        HBox topHBox = new HBox();
        topHBox.setPrefHeight(32);
        topHBox.setPrefWidth(780);
        topHBox.setStyle("-fx-background-color: #BC8F8F;");

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

        playOfflineLabel = new Label("Play Offline");
        playOfflineLabel.setPrefHeight(35);
        playOfflineLabel.setPrefWidth(250);
        playOfflineLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        playOfflineLabel.setFont(new Font(24));
        playOfflineLabel.setPadding(new Insets(0, 0, 0, 100));

        playerOneVBox.getChildren().add(playOfflineLabel);

        replayButton = new Button();
        replayButton.setMaxWidth(Double.MAX_VALUE);
        replayButton.setStyle("-fx-font-size: 20px;");
        replayButton.setStyle("-fx-background-color: transparent;");
        replayButton.setTextFill(javafx.scene.paint.Color.BLACK);
        Image replayImage = new Image(getClass().getResourceAsStream("/media/restart.png"));
        ImageView replayImageView = new ImageView(replayImage);
        replayImageView.setFitHeight(40);
        replayImageView.setFitWidth(40);
        replayButton.setGraphic(replayImageView);

        recordButton = new Button();
        recordButton.setMaxWidth(Double.MAX_VALUE);
        recordButton.setStyle(" -fx-font-size: 20px;");
        recordButton.setStyle("-fx-background-color: transparent;");
        recordButton.setTextFill(javafx.scene.paint.Color.BLACK);
        Image recImage = new Image(getClass().getResourceAsStream("/media/record.png"));
        ImageView recImageView = new ImageView(recImage);
        recImageView.setFitHeight(40);
        recImageView.setFitWidth(40);
        recordButton.setGraphic(recImageView);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(replayButton, recordButton);

        topHBox.getChildren().addAll(backButton, playerOneVBox, hBox);
        topSection.setTop(topHBox);

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

        playerOLabel = new Label(playerO + " - O");
        playerOLabel.setFont(new Font("Arial BOLD", 22));
        playerOLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        AnchorPane.setRightAnchor(playerOLabel, 10.0); // Fixed position from the right

        playerXAnchorPane.getChildren().addAll(playerXLabel, playerOLabel);

        gridPane = new GridPane();
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

        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(javafx.geometry.Pos.CENTER);
        bottomHBox.setPrefHeight(21);
        bottomHBox.setPrefWidth(780);
        bottomHBox.setSpacing(10);

        scoreLabelX = new Label("Scores " + score1 + ":" + score2);
        scoreLabelX.setTextFill(javafx.scene.paint.Color.BLACK);
        scoreLabelX.setFont(new Font("Arial BOLD", 22));

        HBox.setMargin(scoreLabelX, new Insets(0, 20, 0, 0));
        bottomHBox.getChildren().addAll(scoreLabelX);

        borderPane.setTop(topSection);
        borderPane.setCenter(centerVBox);
        borderPane.setBottom(bottomHBox);

        anchorPane.getChildren().addAll(imageView, borderPane);

        anchorPane.setPrefSize(780, 580);

        setPrefSize(780, 580);
        getChildren().add(anchorPane);
    }

    public static void updatePlayerLabels(String player1, String player2) {
        user1 = player1;
        user2 = player2;
        playerXLabel.setText(player1 + " - X");
        playerOLabel.setText(player2 + " - O");
    }

}
