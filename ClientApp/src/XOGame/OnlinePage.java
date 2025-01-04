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

public class OnlinePage extends AnchorPane {
    private int score;
    private int score2;
    private Button[][] buttons = new Button[3][3];
    private String playerX = "Player X";
    private String playerO = "Player O";
    protected Button backButton;
    public OnlinePage() {
        score = 0;
        score2 = 0;
        
        // Create the AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(200);
        anchorPane.setPrefWidth(320);

        // Create and configure the ImageView
        ImageView imageView = new ImageView();
        imageView.setFitHeight(580);
        imageView.setFitWidth(780);
        imageView.setPreserveRatio(false);
        imageView.setImage(new Image(getClass().getResource("/media/xo.jpg").toExternalForm()));

        // Create and configure the BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefHeight(580);
        borderPane.setPrefWidth(780);

        // Top Section
        BorderPane topSection = new BorderPane();
        HBox topHBox = new HBox();
        topHBox.setPrefHeight(32);
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

        HBox.setMargin(backButton, new Insets(0, 0, 0, 0));
        
        VBox playerOneVBox = new VBox();
        playerOneVBox.setAlignment(javafx.geometry.Pos.CENTER);
        playerOneVBox.setPrefHeight(48);
        playerOneVBox.setPrefWidth(589);
        playerOneVBox.setSpacing(5);
        
        Label playerOneLabel = new Label("Player Online");
        playerOneLabel.setPrefHeight(35);
        playerOneLabel.setPrefWidth(203);
        playerOneLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        playerOneLabel.setFont(new Font(24));
        playerOneLabel.setPadding(new Insets(0, 0, 0, 60));

        playerOneVBox.getChildren().add(playerOneLabel);

        Button recordButton = new Button("Record");
        recordButton.setMaxWidth(Double.MAX_VALUE);
        recordButton.setPrefHeight(46);
        recordButton.setPrefWidth(100);
        recordButton.setStyle("-fx-background-color: #e61409; -fx-font-size: 20px;");
        recordButton.setTextFill(javafx.scene.paint.Color.BLACK);
        HBox.setMargin(recordButton, new Insets(0, 0, 0, 20));

        topHBox.getChildren().addAll(backButton, playerOneVBox, recordButton);
        topSection.setTop(topHBox);

        // Center Section
        VBox centerVBox = new VBox();
        centerVBox.setAlignment(javafx.geometry.Pos.CENTER);
        centerVBox.setSpacing(10);

        // Player X and Score Labels
        HBox playerXHBox = new HBox();
        playerXHBox.setAlignment(javafx.geometry.Pos.CENTER);
        playerXHBox.setSpacing(10);
        
        Label playerXLabel = new Label(playerX+" - X");
        playerXLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        playerXLabel.setFont(new Font(24));

        Label scoreLabelX = new Label("Score "+score);
        scoreLabelX.setTextFill(javafx.scene.paint.Color.BLACK);
        scoreLabelX.setFont(new Font(24));

        playerXHBox.getChildren().addAll(playerXLabel, scoreLabelX);

        // GridPane
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
        centerVBox.getChildren().addAll(playerXHBox, gridPane);
        
        // Bottom Section
        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(javafx.geometry.Pos.CENTER);
        bottomHBox.setPrefHeight(21);
        bottomHBox.setPrefWidth(780);
        bottomHBox.setSpacing(10);
        
        Label playerOLabel = new Label(playerO+" - O");
        playerOLabel.setFont(new Font(24));
        playerOLabel.setPrefHeight(33);
        playerOLabel.setPrefWidth(145);

        Label scoreLabelO = new Label("Score "+score2);
        scoreLabelO.setFont(new Font(24));

        bottomHBox.getChildren().addAll(playerOLabel, scoreLabelO);

        borderPane.setTop(topSection);
        borderPane.setCenter(centerVBox);
        borderPane.setBottom(bottomHBox);

        // Set the background image and add the BorderPane to the AnchorPane
        anchorPane.getChildren().addAll(imageView, borderPane);

        anchorPane.setPrefSize(780, 580);

        setPrefSize(780, 580);
        getChildren().add(anchorPane);
    }
}
