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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author nerme
 */
public class OnlinePageController extends OnlinePage {

    private boolean isRecording = false;
    private TicTacToe xoGame;
    private Timeline moveTimer;
    private Line winningLine;
    Integer row;
    Integer col;
    Alert alert;
    public static String userName;
    public static String opponentName;
    Stage stage;
    static boolean again = false;
    boolean gameEnd;
    Thread thread;



    static boolean exit;

    public OnlinePageController(Stage stage) {
        this.stage = stage;
        initializeGameButtonsHandlers();
        initializeMoveTimer();
        xoGame = new TicTacToe();
        if (AvailableUserPageController.isStarting) {
            startMoveTimer();
        }
        thread = new Thread(() -> {
            while (!exit) {
                String serverResponse = getResponse();
                StringTokenizer responseMsgTokens = new StringTokenizer(serverResponse, "#@$");
                String status = responseMsgTokens.nextToken();

                switch (status) {
                    case "normalMove":
                        Platform.runLater(() -> {
                            enableMove();
                            row = Integer.parseInt(responseMsgTokens.nextToken());
                            col = Integer.parseInt(responseMsgTokens.nextToken());
                            drawMove(row, col);
                            startMoveTimer();
                        });
                        break;
                    case "losingMove":
                        Platform.runLater(() -> {
                            enableMove();
                            row = Integer.parseInt(responseMsgTokens.nextToken());
                            col = Integer.parseInt(responseMsgTokens.nextToken());
                            drawMove(row, col);
                            AvailableUserPageController.opponentscore += 5;
                            updateScore();
                            xoGame.isWinningMove(row, col);
                            drawWinningLine();
                            disableMove();
                            stopMoveTimer();
                            LoseVideoPageController videoController = new LoseVideoPageController(stage);
                            gameEnd = true;
                            videoController.playVideo();
                        });
                        break;
                    case "draw":
                        row = Integer.parseInt(responseMsgTokens.nextToken());
                        col = Integer.parseInt(responseMsgTokens.nextToken());
                        stopMoveTimer();
                        Platform.runLater(() -> {
                            drawMove(row, col);
                            showDrawAlert(stage, "Game Draw");
                            gameEnd = true;
                            for (int row = 0; row < 3; row++) {
                                for (int col = 0; col < 3; col++) {
                                    buttons[row][col].setDisable(true);
                                }
                            }
                            gameEnd = true;
                        });
                        break;
                    case "withdraw":
                        Platform.runLater(() -> {

                            showAlert("Withdraw", "Unfortunantly you opponent has left the game");
                            exit();
                            
                        });
                        break;
                    case "invitation":
                        String opponent = responseMsgTokens.nextToken();
                        handleInvitationRequest(opponent, stage);
                        break;

                    case "accepted":
                        Platform.runLater(() -> {
                            clearBoard();
                            startMoveTimer();
                            enableMove();
                            showAlert("Accepted", "your inivitation has been accepted");
                            updatePlayerLabels(HomePageController.userName, "X", AvailableUserPageController.userScore, OnlinePageController.opponentName, "O", AvailableUserPageController.opponentscore);
                            again = true;
                            gameEnd = false;
                            isRecording = false;
                            initializeGameButtonsHandlers();
                        });
                        break;
                    case "declined":
                        Platform.runLater(() -> {                           
                            showErrorAlert(stage, "your inivitation has been declined");
                            exit();
                        });
                        break;
                    default:
                        if (!again) {
                            enableMove();
                        }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        backButton.setOnMouseClicked(e -> {
            if (!gameEnd) {
                ClientHandler.sendRequest("withdraw");

            }    
            exit();

        });

        recordButton.setOnMouseClicked(e -> {
            if (!isRecording) {
                isRecording = true;
                changeRecordButton();
                RecordController.setPlayersName(HomePageController.userName, OnlinePageController.opponentName);
                if (AvailableUserPageController.isStarting) {
                    RecordController.setPlayersShapes("X", "O");
                } else {
                    RecordController.setPlayersShapes("O", "X");
                }
                AvailableUserPageController.isStarting = false;
                RecordController.createFile("online/" + HomePageController.userName);
            }
        });

        replayButton.setOnMouseClicked(e -> {
            stopMoveTimer();
            stopRecording();
            if (gameEnd) {
                ClientHandler.sendRequest("sendInvitaion" + "#@$" + opponentName + "#@$");
            } else {
                showAlert("error", "You must complete the game first");
            }
            sendRequest("updateScore#@$" + userName + "#@$" + score1 + "#@$" + opponentName + "#@$" + score2);

        });
    }

    private void initializeGameButtonsHandlers() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int rowButton = row;
                final int colButton = col;
                buttons[row][col].setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
                disableMove();
                buttons[row][col].setOnAction(e -> {
                    processMove(rowButton, colButton);
                });
                if (!again) {
                    disableMove();
                }
                if (again) {
                    enableMove();
                }
            }
        }
    }

    private void processMove(int row, int col) {
        if (xoGame.makeMove(row, col)) {
            buttons[row][col].setText(xoGame.getCurrentPlayer());
            if (isRecording) {
                RecordController.saveMove(row, col, xoGame.getCurrentPlayer());
            }
            stopMoveTimer();
            disableMove();

            if (xoGame.isWinningMove(row, col) && winningLine == null) {
                ClientHandler.sendRequest("winningMove" + "#@$" + row + "#@$" + col + "#@$");
                drawWinningLine();
                stopRecording();

                AvailableUserPageController.userScore += 5;
                updateScore();

                if (xoGame.getCurrentPlayer().equals("X")) {
                    OnlinePage.scoreLabelX.setText("Scores " + AvailableUserPageController.userScore + ":" + AvailableUserPageController.opponentscore);
                } else {
                    OnlinePage.scoreLabelX.setText("Scores " + AvailableUserPageController.opponentscore + ":" + AvailableUserPageController.userScore);
                }

                disableMove();

                showWinningVideo();
                stopMoveTimer();
                gameEnd = true;
            } else if (xoGame.isDraw()) {
                ClientHandler.sendRequest("drawMove" + "#@$" + row + "#@$" + col + "#@$");
                stopRecording();
                setBoardForDraw();
                stopMoveTimer();
                gameEnd = true;
            } else {
                ClientHandler.sendRequest("normalMove" + "#@$" + row + "#@$" + col + "#@$");
                xoGame.switchPlayer();
                disableMove();

            }
        }
//        else {
//            enableMove();
//        }
    }

    private void updateScore() {
        if (xoGame.getCurrentPlayer().equals("X")) {
            OnlinePage.scoreLabelX.setText("Scores " + AvailableUserPageController.userScore + ":" + AvailableUserPageController.opponentscore);
        } else {
            OnlinePage.scoreLabelX.setText("Scores " + AvailableUserPageController.opponentscore + ":" + AvailableUserPageController.userScore);
        }

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

        if (isRecording) {
            RecordController.saveLine(startX, startY, endX, endY);
        }
        winningLine = new Line(startX, startY, endX, endY);

        winningLine.setStroke(Color.RED);
        winningLine.setStrokeWidth(5);

        borderPane.getChildren().add(winningLine);

    }

    private void drawMove(int row, int col) {
        if (xoGame.makeMove(row, col)) {

            buttons[row][col].setText(xoGame.getCurrentPlayer());
            if (isRecording) {
                RecordController.saveMove(row, col, xoGame.getCurrentPlayer());
            }
            xoGame.switchPlayer();
        }
    }

    private void handleInvitationRequest(String opponent, Stage stage) {
        Platform.runLater(() -> {
            boolean isInvitationAccepted = showRequestAlert("Game Invitation", "Player " + opponent + " has invited you to a game. Do you accept?", stage);
            if (isInvitationAccepted) {
                sendRequest("invitationResponse" + "#@$" + "accept" + "#@$" + opponent);
                updatePlayerLabels(OnlinePageController.opponentName, "X", AvailableUserPageController.opponentscore, HomePageController.userName, "O", AvailableUserPageController.userScore);
                clearBoard();
                disableMove();
                again = true;
                gameEnd = false;
                isRecording = false;
            } else {                
                sendRequest("invitationResponse" + "#@$" + "decline" + "#@$" + opponent);

                
                exit();

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
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showTimeoutAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setContentText(content);
    }

    private void showErrorAlert(Stage stage, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Error");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void showDrawAlert(Stage stage, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Draw");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    protected void disableMove() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }

    private void enableMove() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(false);
            }
        }
    }

    private void clearBoard() {
        isRecording = false;
        xoGame = new TicTacToe();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        borderPane.getChildren().remove(winningLine);
        winningLine = null;
    }

    private void changeRecordButton() {
        Image recImage;
        if (isRecording) {
            recImage = new Image(getClass().getResourceAsStream("/media/stop.png"));
        } else {
            recImage = new Image(getClass().getResourceAsStream("/media/record.png"));
        }
        ImageView recImageView = new ImageView(recImage);
        recImageView.setFitHeight(40);
        recImageView.setFitWidth(40);
        recordButton.setGraphic(recImageView);
    }

    private void stopRecording() {
        if (isRecording) {
            isRecording = false;
            changeRecordButton();
            RecordController.closeRecordConection();
        }
    }

    private void showWinningVideo() {
        WinVideoPageController videoController = new WinVideoPageController(stage);
        videoController.playVideo();
    }

    private void setBoardForDraw() {
        Platform.runLater(() -> {
            showDrawAlert(stage, "Game Draw");
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    buttons[row][col].setDisable(true);
                }
            }
        });
    }

    private void initializeMoveTimer() {
        moveTimer = new Timeline();
        moveTimer.getKeyFrames().add(new KeyFrame(Duration.seconds(15), e -> {
            Platform.runLater(() -> {
                showTimeoutAlert("Timeout", "You took too long to make a move. You have withdrawn from the game.");
                ClientHandler.sendRequest("withdraw");
                gameEnd = true;
                Scene scene = new Scene(new AvailableUserPageController(stage));
                stage.setScene(scene);
            });
        }));
        moveTimer.setCycleCount(1);
    }

    private void startMoveTimer() {
        moveTimer.playFromStart();
    }

    private void stopMoveTimer() {
        if (moveTimer != null) {
            moveTimer.stop();
        }
    }
    private void exit(){
        sendRequest("sendAvailablePlayers" + "#@$");
        isRecording = false;
        exit = true;
        again = false;
        AvailableUserPageController.inGame =false;
        ClientHandler.sendRequest("exit");            
        gameEnd = true;
        stopMoveTimer();
        Scene scene = new Scene(new AvailableUserPageController(stage));
        stage.setScene(scene);
        try {
            thread.stop();
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AvailableUserPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
