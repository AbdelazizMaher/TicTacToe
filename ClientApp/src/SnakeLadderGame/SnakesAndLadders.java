package SnakeLadderGame;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.util.Random;

public class SnakesAndLadders extends AnchorPane {
    protected static final int BOARD_SIZE = 10;
    protected static final int SQUARE_SIZE = 60;
    protected Circle player1, player2;
    protected int player1Pos = 1, player2Pos = 1;
    protected boolean player1Turn = true;
    protected Random random = new Random();
    protected ImageView diceImageView;
    protected ImageView avatar1ImageView, avatar2ImageView;
    protected Text turnText;
    protected GraphicsContext gc;

    public SnakesAndLadders() {
        
        GridPane grid = new GridPane();
        grid.setVgap(0);
        grid.setHgap(0);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(SQUARE_SIZE, SQUARE_SIZE);
                grid.add(cell, col, row);
            }
        }

        
        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("/SnakeLadderGame/media/GameBoard.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        grid.setBackground(new Background(background));

       
        player1 = new Circle(SQUARE_SIZE / 4);
        player1.setFill(Color.LIGHTGREEN);
        grid.add(player1, 0, 9);

        player2 = new Circle(SQUARE_SIZE / 4);
        player2.setFill(Color.BLACK);
        grid.add(player2, 0, 9);

        
        diceImageView = new ImageView();
        diceImageView.setFitWidth(50);
        diceImageView.setFitHeight(50);

        
        avatar1ImageView = new ImageView(new Image(getClass().getResource("/SnakeLadderGame/media/avatar1.png").toExternalForm()));
        avatar1ImageView.setFitWidth(60);
        avatar1ImageView.setFitHeight(60);

        avatar2ImageView = new ImageView(new Image(getClass().getResource("/SnakeLadderGame/media/avatar2.png").toExternalForm()));
        avatar2ImageView.setFitWidth(60);
        avatar2ImageView.setFitHeight(60);

        HBox controlPanel = new HBox(10, avatar1ImageView, diceImageView, avatar2ImageView);
        controlPanel.setStyle("-fx-padding: 10;");
        controlPanel.setAlignment(Pos.CENTER);

        Canvas canvas = new Canvas(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE);
        gc = canvas.getGraphicsContext2D();

        StackPane gamePane = new StackPane();
        gamePane.getChildren().addAll(grid, canvas);
        gamePane.setAlignment(Pos.CENTER);  

        BorderPane root = new BorderPane();
        root.setCenter(gamePane);
        root.setBottom(controlPanel);

        root.setBackground(new Background(new BackgroundImage(
                new Image(getClass().getResource("/SnakeLadderGame/media/SquidLogoBackground.png").toExternalForm()),  
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        getChildren().add(root);
    }
}
