package XOGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PlayOfflinePage extends AnchorPane {
    private int score;
    private int score2;
    private Button[][] buttons = new Button[3][3];
    private String playerX = "Player X";
    private String playerO = "Player O";

    public PlayOfflinePage(Stage stage) {
        score = 0;
        score2 = 0;

        Button backButton = new Button();
        backButton.setStyle("-fx-background-color: #e61409; -fx-font-size: 20px; -fx-background-radius: 50%;");
        backButton.setTextFill(Color.WHITE);
        
        ImageView backarrow = new ImageView(new Image("/media/backarrow.png"));
        backarrow.setFitHeight(40);
        backarrow.setFitWidth(40);
        backButton.setGraphic(backarrow);
        backButton.setOnAction(e -> {
            HomePage root = new HomePage(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2); 
        });
        Button recordButton = new Button("Record");
        recordButton.setStyle("-fx-background-color: #e61409;");
        recordButton.setTextFill(Color.WHITE);
        recordButton.setFont(new Font("Arial", 18.0));

        Label playerXLabel = new Label(playerX + " - X"+"       "+"Score: " + score);
        playerXLabel.setFont(new Font(25));
        playerXLabel.setTextFill(Color.RED);

        Label playerOLabel = new Label(playerO + " - O"+"       "+"Score: " + score2);
        playerOLabel.setFont(new Font(25));
        playerOLabel.setTextFill(Color.BLACK);

        HBox playerXHBox = new HBox(10, playerXLabel);
        playerXHBox.setAlignment(Pos.CENTER);
        playerXHBox.setPadding(new Insets(0, 0, 0, 20));

        VBox topVBox = new VBox(5, playerXHBox);
        topVBox.setAlignment(Pos.CENTER);

        BorderPane topSection = new BorderPane();
        topSection.setPadding(new Insets(10));
        topSection.setLeft(backButton);
        topSection.setCenter(topVBox);
        topSection.setStyle("-fx-background-color: black;");

        HBox rightSection = new HBox(10,recordButton);
        rightSection.setAlignment(Pos.CENTER);
        topSection.setRight(rightSection);

        VBox bottomVBox = new VBox(5, playerOLabel);
        bottomVBox.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER); 
        gridPane.setPrefSize(450, 450);
        gridPane.setPadding(new Insets(0));
        gridPane.setVgap(0);
        gridPane.setHgap(0);

        for (int i = 0; i < 3; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(150));
            gridPane.getRowConstraints().add(new RowConstraints(150));
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setPrefSize(150, 150);
                button.setFont(new Font(50));
                button.setStyle("-fx-background-color: rgba(169, 169, 169, 0.5); -fx-border-color: red; -fx-text-fill: black;");
                buttons[row][col] = button;

                GridPane.setRowIndex(button, row);
                GridPane.setColumnIndex(button, col);
                gridPane.getChildren().add(button);
            }
        }

        Image image = new Image("/media/xo.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(780);
        imageView.setFitHeight(580);
        imageView.setPreserveRatio(false);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topSection);
        mainLayout.setCenter(gridPane);
        mainLayout.setBottom(bottomVBox);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, mainLayout);

        stackPane.setPrefSize(780, 580);

        setPrefSize(780, 580);
        getChildren().add(stackPane);
    }
}