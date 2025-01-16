/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import ClientHandler.ClientHandler;
import static ClientHandler.ClientHandler.getResponse;
import static ClientHandler.ClientHandler.sendRequest;
import XOGame.OnlinePage;
import java.util.StringTokenizer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import XOGameBoard.TicTacToe;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author nerme
 */
public class OnlinePageController extends OnlinePage{
    private boolean isPaused = false;
    private TicTacToe xoGame;
    private Line winningLine;
    Integer row;
    Integer col;
    Alert alert; 
    public static String userName;
    public static String opponentName;
    Stage stage;
    static boolean again = false;
    public OnlinePageController(Stage stage) {
        this.stage = stage;
        initializeGameButtonsHandlers();
        xoGame = new TicTacToe();
        Thread thread = new Thread(() -> {
            while (true) {
                String serverResponse = getResponse();
                System.out.println(serverResponse);
                StringTokenizer responseMsgTokens = new StringTokenizer(serverResponse, "#@$");
                String status = responseMsgTokens.nextToken();

                switch (status) {
                    case "normalMove":
                        //enable
                        enableMove();
                        row = Integer.parseInt(responseMsgTokens.nextToken());
                        col = Integer.parseInt(responseMsgTokens.nextToken());
                        drawMove(row, col);
                        break;
                    case "ormalMove":
                        //enable
                        enableMove();
                        row = Integer.parseInt(responseMsgTokens.nextToken());
                        col = Integer.parseInt(responseMsgTokens.nextToken());
                        drawMove(row, col);
                        break;
                    case "losingMove":
                        
                            enableMove();
                            row = Integer.parseInt(responseMsgTokens.nextToken());
                            col = Integer.parseInt(responseMsgTokens.nextToken());
                            
                            drawMove(row, col);
                            for (int row = 0; row < 3; row++) {
                                for (int col = 0; col < 3; col++) {
                                    System.out.println(buttons[row][col]);
                                }
                            }
                            xoGame.isWinningMove(row, col);
                            System.out.println(xoGame.getWinningLine());
                            drawWinningLine();
                            disableMove();
                        
                        break;
                    case "draw":
                        row = Integer.parseInt(responseMsgTokens.nextToken());
                        col = Integer.parseInt(responseMsgTokens.nextToken());
                        drawMove(row, col);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Draw");
                        alert.setContentText("You're draw!!");
                        alert.showAndWait();
                        for (int row = 0; row < 3; row++) {
                            for (int col = 0; col < 3; col++) {
                                buttons[row][col].setDisable(true);
                            }
                        }
                        break;
                    case "withdraw":
                        showAlert("Withdraw", "Unfortunantly you opponent has left the game");
                        Scene scene = new Scene(new AvailableUserPageController(stage));
                        stage.setScene(scene);
                        break;
                    case "invitation":
                        String opponent = responseMsgTokens.nextToken();
                        handleInvitationRequest(opponent, stage);
                        break;

                    case "accepted":
                        Platform.runLater(() -> {
                            clearBoard();
                            enableMove();
                            showAlert("Accepted", "your inivitation has been accepted");
                            again = true;
                            initializeGameButtonsHandlers();
                        });
                        break;
                    case "declined":
                        Platform.runLater(() -> {
                            showErrorAlert(stage, "your inivitation has been declined");
                            Scene scene1 = new Scene(new AvailableUserPageController(stage));
                            stage.setScene(scene1);
                        });
                        break;
                    default:
                        if(!again){
                            enableMove();
                        }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
       
        

        backButton.setOnMouseClicked(e -> {
            ClientHandler.sendRequest("withdraw");
            Scene scene = new Scene(new AvailableUserPageController(stage));
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
            ClientHandler.sendRequest("sendInvitaion" + "#@$" + opponentName + "#@$");
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
                    disableMove();
                }); 
                if(!again){
                        disableMove();
                }
            }
        }
    }

    private void processMove(int row, int col) {
        if (xoGame.makeMove(row, col)) {
            buttons[row][col].setText(xoGame.getCurrentPlayer());
            disableMove();
            if (xoGame.isWinningMove(row, col) && winningLine == null) {
                //1-send request with the winningmove & drawWinningLine();
                ClientHandler.sendRequest("winningMove" + "#@$" + row + "#@$" + col + "#@$");
                System.out.println(xoGame.getWinningLine());
                drawWinningLine();
                updateScore();
                disableMove();
//                Scene scene = new Scene(new WinVideoPageController(stage));
//                stage.setScene(scene);
            } else if (xoGame.isDraw()) {
                //2-send request game is draw;
                ClientHandler.sendRequest("drawMove" + "#@$" + row + "#@$" + col + "#@$");
            } else {
                //3-send request with the normalmove 
                ClientHandler.sendRequest("normalMove" + "#@$" + row + "#@$" + col + "#@$");
                xoGame.switchPlayer();
                disableMove();
            }
        }
    }

    private void updateScore() {

    }

    private void drawWinningLine() {
Platform.runLater(() -> {
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
        });
    }

    private void drawMove(int row, int col) {
        Platform.runLater(() -> {
        if (xoGame.makeMove(row, col)) {
            buttons[row][col].setText(xoGame.getCurrentPlayer());
            xoGame.switchPlayer(); 
        }
        });
    }


    private void handleInvitationRequest(String opponent, Stage stage) {
        Platform.runLater(() -> {
            boolean isInvitationAccepted = showRequestAlert("Game Invitation", "Player " + opponent + " has invited you to a game. Do you accept?", stage);
            if (isInvitationAccepted) {
                sendRequest("invitationResponse" + "#@$" + "accept" + "#@$" + opponent);
                clearBoard();
                disableMove();
                again = true;
            } else {
                sendRequest("invitationResponse" + "#@$" + "decline" + "#@$" + opponent);
            }
        });
    }

    private boolean showRequestAlert(String title, String contentMessage, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentMessage);

        ButtonType acceptButton = new ButtonType("Accept");
        ButtonType declineButton = new ButtonType("Decline");
        alert.getButtonTypes().setAll(acceptButton, declineButton);

        final Boolean[] retVal = {false};
        alert.showAndWait().ifPresent(response -> {
            if (response == acceptButton) {
                retVal[0] = true;
            } else if (response == declineButton) {
                retVal[0] = false;
            }
        });

        return retVal[0];
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(Stage stage, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Error");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    protected void disableMove(){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }
    private void enableMove(){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(false);
            }
        }
    }
    private void clearBoard(){
        isPaused = false;
        winningLine = new Line();
        xoGame = new TicTacToe();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }
   
}