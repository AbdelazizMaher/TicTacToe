/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import static XOGame.HomePage.userName;
import XOGame.OnlinePage;
import XOGameBoard.TicTacToe;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class OnlinePageController extends OnlinePage {

    private boolean isPaused = false;
    private TicTacToe xoGame;
    private Line winningLine;
    public static String userName;
    public static String opponentName;
    private Stage stage;

    public OnlinePageController(Stage stage) {
        this.stage = stage;
        initializeGameButtonsHandlers();
        backButton.setOnMouseClicked(e -> {
            ClientHandler.sendRequest("withdraw");
            AvailableUserPageController availablePage = new AvailableUserPageController(stage);
            Scene scene = new Scene(availablePage);
            stage.setScene(scene);
        });
        recordButton.setOnMouseClicked(e -> {
            Image recImage;
            if (isPaused) {
                recImage = new Image(getClass().getResourceAsStream("/media/record.png"));
            } else {
                recImage = new Image(getClass().getResourceAsStream("/media/stop.png"));
            }
            ImageView recImageView = new ImageView(recImage);
            recImageView.setFitHeight(40);
            recImageView.setFitWidth(40);
            recordButton.setGraphic(recImageView);
            isPaused = !isPaused;
        });
        replayButton.setOnMouseClicked(e -> {
            ClientHandler.sendRequest("sendInvitaion" + "#@$" + userName + "#@$");
        });
    }

    private void initializeGameButtonsHandlers() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int rowButton = row;
                final int colButton = col;
                buttons[row][col].setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
                buttons[row][col].setOnAction(e -> {
                    processMove(rowButton, colButton);
                });
            }
        }
    }

    private void processMove(int row, int col) {
        if (xoGame.makeMove(row, col)) {

            if (xoGame.isWinningMove(row, col) && winningLine == null) {
                //1-send request with the winningmove & drawWinningLine();
                ClientHandler.sendRequest("winningMove" + "#@$" + row + "#@$" + col + "#@$");
                updateScore();
                Scene scene = new Scene(new WinVideoPageController(stage));
                stage.setScene(scene);
            } else if (xoGame.isDraw()) {
                //2-send request game is draw;
                ClientHandler.sendRequest("drawMove" + "#@$" + row + "#@$" + col + "#@$");
            } else {
                //3-send request with the normalmove 
                ClientHandler.sendRequest("normalMove" + "#@$" + row + "#@$" + col + "#@$");
            }
        }
    }

    private void updateScore() {

    }

    private void drawWinningLine() {
        int[] winningLineIndices = xoGame.getWinningLine();

        Button btn1 = buttons[winningLineIndices[0]][winningLineIndices[1]];
        Button btn3 = buttons[winningLineIndices[4]][winningLineIndices[5]];

        Point2D point1 = btn1.localToScene(btn1.getWidth() / 2, btn1.getHeight() / 2);
        Point2D point3 = btn3.localToScene(btn3.getWidth() / 2, btn3.getHeight() / 2);

        double startX = point1.getX();
        double startY = point1.getY();
        double endX = point3.getX();
        double endY = point3.getY();

        winningLine = new Line(startX, startY, endX, endY);

        winningLine.setStroke(Color.RED);
        winningLine.setStrokeWidth(5);

        borderPane.getChildren().add(winningLine);
    }
}
