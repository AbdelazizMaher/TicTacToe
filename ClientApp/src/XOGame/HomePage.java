package XOGame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public abstract class HomePage extends AnchorPane {

    protected final ImageView imageView;
    protected final AnchorPane anchorPane;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Line line;
    protected final Line line0;
    protected final Line line1;
    protected final Line line2;
    protected final Label tictactoeLabel;
    protected final ImageView imageView2;
    protected final Label welcometoourtictactoeLabel;
    protected final Button historyButton;
    protected final Button signupButton;
    protected final Button loginButton;
    protected final Button logoutButton;
    protected final Button playvscomputerButton;
    protected final ImageView imageView3;
    protected final Button playofflineButton;
    protected final ImageView imageView4;
    protected final Button playonlineButton;
    protected final ImageView imageView5;
    protected final ImageView imageView6;
    protected final Label label;
    protected final DropShadow dropShadow;
    protected final Label label0;
    protected final DropShadow dropShadow0;
    protected final Label label1;
    protected final DropShadow dropShadow1;

    public static String userName = "";
    protected final Label logedinUserNameLabel;

    public HomePage() {

        imageView = new ImageView();
        anchorPane = new AnchorPane();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        line = new Line();
        line0 = new Line();
        line1 = new Line();
        line2 = new Line();
        tictactoeLabel = new Label();
        imageView2 = new ImageView();
        welcometoourtictactoeLabel = new Label();
        historyButton = new Button();
        signupButton = new Button();
        loginButton = new Button();
        logoutButton = new Button();
        playvscomputerButton = new Button();
        imageView3 = new ImageView();
        playofflineButton = new Button();
        imageView4 = new ImageView();
        playonlineButton = new Button();
        imageView5 = new ImageView();
        imageView6 = new ImageView();
        label = new Label();
        dropShadow = new DropShadow();
        label0 = new Label();
        dropShadow0 = new DropShadow();
        label1 = new Label();
        dropShadow1 = new DropShadow();
        logedinUserNameLabel = new Label();

        setId("AnchorPane");
        setPrefHeight(580.0);
        setPrefWidth(780.0);
        getStyleClass().add("bodybg");
        getStylesheets().add("/styles/Stylesheet.css");

        imageView.setFitHeight(580.0);
        imageView.setFitWidth(780.0);
        imageView.setPreserveRatio(false);

        anchorPane.setPrefHeight(580.0);
        anchorPane.setPrefWidth(80.0);
        anchorPane.getStyleClass().add("sidebar");
        anchorPane.getStylesheets().add("/styles/Stylesheet.css");

        imageView0.setFitHeight(50.0);
        imageView0.setFitWidth(55.0);
        imageView0.setLayoutX(15.0);
        imageView0.setLayoutY(9.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/media/xo.jpg").toExternalForm()));

        imageView1.setFitHeight(70.0);
        imageView1.setFitWidth(70.0);
        imageView1.setLayoutX(5.0);
        imageView1.setLayoutY(526.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("/media/avatar.png").toExternalForm()));

        line.setEndX(-39.09999084472656);
        line.setEndY(-7.62939453125E-6);
        line.setLayoutX(117.0);
        line.setLayoutY(62.0);
        line.setStartX(-114.99999237060547);
        line.setStartY(-7.62939453125E-6);
        line.setStroke(javafx.scene.paint.Color.WHITE);

        line0.setEndX(-39.09999084472656);
        line0.setEndY(-7.62939453125E-6);
        line0.setLayoutX(117.0);
        line0.setLayoutY(66.0);
        line0.setStartX(-114.99999237060547);
        line0.setStartY(-7.62939453125E-6);
        line0.setStroke(javafx.scene.paint.Color.WHITE);

        line1.setEndX(-39.09999084472656);
        line1.setEndY(-7.62939453125E-6);
        line1.setLayoutX(116.0);
        line1.setLayoutY(524.0);
        line1.setStartX(-114.99999237060547);
        line1.setStartY(-7.62939453125E-6);
        line1.setStroke(javafx.scene.paint.Color.WHITE);

        line2.setEndX(-39.09999084472656);
        line2.setEndY(-7.62939453125E-6);
        line2.setLayoutX(116.0);
        line2.setLayoutY(520.0);
        line2.setStartX(-114.99999237060547);
        line2.setStartY(-7.62939453125E-6);
        line2.setStroke(javafx.scene.paint.Color.WHITE);

        tictactoeLabel.setLayoutX(91.0);
        tictactoeLabel.setText("TicTacToe");
        tictactoeLabel.setTextFill(javafx.scene.paint.Color.valueOf("#131218"));
        tictactoeLabel.setFont(new Font("Javanese Text", 18.0));

        welcometoourtictactoeLabel.setLayoutX(91.0);
        welcometoourtictactoeLabel.setLayoutY(41.0);
        welcometoourtictactoeLabel.setText("WELCOME TO OUR TICTACTOE GAME ..!");
        welcometoourtictactoeLabel.setTextFill(javafx.scene.paint.Color.valueOf("#131218"));
        welcometoourtictactoeLabel.setFont(new Font("Arial Bold", 16.0));

        getChildren().add(imageView);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(imageView1);
        anchorPane.getChildren().add(line);
        anchorPane.getChildren().add(line0);
        anchorPane.getChildren().add(line1);
        anchorPane.getChildren().add(line2);
        getChildren().add(anchorPane);
        getChildren().add(tictactoeLabel);
        getChildren().add(welcometoourtictactoeLabel);

        if (userName.isEmpty()) {
            historyButton.setLayoutX(577.0);
            historyButton.setLayoutY(15.0);
            historyButton.setMnemonicParsing(false);
            historyButton.setPrefHeight(29.0);
            historyButton.setPrefWidth(50.0);
            historyButton.setStyle("-fx-background-color: #e61409;");
            historyButton.setText("History");
            historyButton.setTextFill(javafx.scene.paint.Color.WHITE);
            historyButton.setFont(new Font("Arial Bold", 12.0));
            getChildren().add(historyButton);

            signupButton.setLayoutX(633.0);
            signupButton.setLayoutY(15.0);
            signupButton.setMnemonicParsing(false);
            signupButton.setPrefHeight(29.0);
            signupButton.setPrefWidth(50.0);
            signupButton.setStyle("-fx-background-color: #e61409;");
            signupButton.setText("Sign Up");
            signupButton.setTextFill(javafx.scene.paint.Color.WHITE);
            signupButton.setFont(new Font("Arial Bold", 12.0));
            getChildren().add(signupButton);

            AnchorPane.setLeftAnchor(loginButton, 691.0);
            loginButton.setLayoutX(691.0);
            loginButton.setLayoutY(15.0);
            loginButton.setMnemonicParsing(false);
            loginButton.setPrefHeight(29.0);
            loginButton.setPrefWidth(50.0);
            loginButton.setStyle("-fx-background-color: #e61409;");
            loginButton.setText("Login");
            loginButton.setTextFill(javafx.scene.paint.Color.WHITE);
            loginButton.setFont(new Font("Arial Bold", 12.0));
            getChildren().add(loginButton);

            imageView2.setFitHeight(75.0);
            imageView2.setFitWidth(50.0);
            imageView2.setLayoutX(732.0);
            imageView2.setLayoutY(12.0);
            imageView2.setPickOnBounds(true);
            imageView2.setPreserveRatio(true);
            imageView2.setImage(new Image(getClass().getResource("/media/avatar.png").toExternalForm()));
            getChildren().add(imageView2);
        } else {
            logedinUserNameLabel.setText("Hi, " + userName);
            logedinUserNameLabel.setTextFill(javafx.scene.paint.Color.valueOf("#131218"));
            logedinUserNameLabel.setFont(new Font("Javanese Text", 16.0));
            logedinUserNameLabel.setWrapText(false);
            logedinUserNameLabel.setMaxWidth(100);
            HBox hbox = new HBox(0);
            hbox.getChildren().add(logedinUserNameLabel);
            hbox.setLayoutX(540.0);
            hbox.setLayoutY(6.0);
            getChildren().add(hbox);

            historyButton.setLayoutX(633.0);
            historyButton.setLayoutY(15.0);
            historyButton.setMnemonicParsing(false);
            historyButton.setPrefHeight(29.0);
            historyButton.setPrefWidth(50.0);
            historyButton.setStyle("-fx-background-color: #e61409;");
            historyButton.setText("History");
            historyButton.setTextFill(javafx.scene.paint.Color.WHITE);
            historyButton.setFont(new Font("Arial Bold", 12.0));
            getChildren().add(historyButton);

            logoutButton.setLayoutX(691.0);
            logoutButton.setLayoutY(15.0);
            logoutButton.setMnemonicParsing(false);
            logoutButton.setPrefHeight(29.0);
            logoutButton.setPrefWidth(50.0);
            logoutButton.setStyle("-fx-background-color: #e61409;");
            logoutButton.setText("Logout");
            logoutButton.setTextFill(javafx.scene.paint.Color.WHITE);
            logoutButton.setFont(new Font("Arial Bold", 12.0));
            getChildren().add(logoutButton);

            imageView2.setFitHeight(50.0);
            imageView2.setFitWidth(50.0);
            imageView2.setLayoutX(745.0);
            imageView2.setLayoutY(3.0);
            imageView2.setPickOnBounds(true);
            imageView2.setPreserveRatio(true);
            imageView2.setImage(new Image(getClass().getResource("/media/userAvatar.jpg").toExternalForm()));
            getChildren().add(imageView2);
        }
        playvscomputerButton.setLayoutX(265.0);
        playvscomputerButton.setLayoutY(219.0);
        playvscomputerButton.setMnemonicParsing(false);
        playvscomputerButton.setPrefHeight(100.0);
        playvscomputerButton.setPrefWidth(235.0);
        playvscomputerButton.setStyle("-fx-background-color: #131218;");
        playvscomputerButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playvscomputerButton.setTextFill(javafx.scene.paint.Color.WHITE);
        playvscomputerButton.setFont(new Font("Arial Bold", 14.0));

        imageView3.setFitHeight(100.0);
        imageView3.setFitWidth(200.0);
        imageView3.setPickOnBounds(true);
        imageView3.setImage(new Image(getClass().getResource("/media/playvscomputer.jpg").toExternalForm()));
        playvscomputerButton.setGraphic(imageView3);

        playofflineButton.setLayoutX(265.0);
        playofflineButton.setLayoutY(338.0);
        playofflineButton.setMnemonicParsing(false);
        playofflineButton.setPrefHeight(100.0);
        playofflineButton.setPrefWidth(235.0);
        playofflineButton.setStyle("-fx-background-color: #131218;");
        playofflineButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playofflineButton.setTextFill(javafx.scene.paint.Color.WHITE);
        playofflineButton.setFont(new Font("Arial Bold", 14.0));

        imageView4.setFitHeight(100.0);
        imageView4.setFitWidth(215.0);
        imageView4.setPickOnBounds(true);
        imageView4.setImage(new Image(getClass().getResource("/media/playoffline.jpg").toExternalForm()));
        playofflineButton.setGraphic(imageView4);

        playonlineButton.setLayoutX(266.0);
        playonlineButton.setLayoutY(457.0);
        playonlineButton.setMnemonicParsing(false);
        playonlineButton.setPrefHeight(100.0);
        playonlineButton.setPrefWidth(235.0);
        playonlineButton.setStyle("-fx-background-color: #131218;");
        playonlineButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playonlineButton.setTextFill(javafx.scene.paint.Color.WHITE);
        playonlineButton.setFont(new Font("Arial Bold", 14.0));

        imageView5.setFitHeight(100.0);
        imageView5.setFitWidth(185.0);
        imageView5.setPickOnBounds(true);
        imageView5.setImage(new Image(getClass().getResource("/media/playonline.jpg").toExternalForm()));
        playonlineButton.setGraphic(imageView5);

        imageView6.setFitHeight(400.0);
        imageView6.setFitWidth(235.0);
        imageView6.setLayoutX(544.0);
        imageView6.setLayoutY(180.0);
        imageView6.setPickOnBounds(true);
        imageView6.setImage(new Image(getClass().getResource("/media/o.jpg").toExternalForm()));

        label.setLayoutX(270.0);
        label.setLayoutY(224.0);
        label.setText("PLAY VS COMPUTER");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Arial Bold", 15.0));

        label.setEffect(dropShadow);

        label0.setLayoutX(270.0);
        label0.setLayoutY(344.0);
        label0.setText("PLAY OFFLINE");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("Arial Bold", 15.0));

        label0.setEffect(dropShadow0);

        label1.setLayoutX(271.0);
        label1.setLayoutY(462.0);
        label1.setText("PLAY ONLINE");
        label1.setTextFill(javafx.scene.paint.Color.WHITE);
        label1.setFont(new Font("Arial Bold", 15.0));

        label1.setEffect(dropShadow1);

        getChildren().add(playvscomputerButton);
        getChildren().add(playofflineButton);
        getChildren().add(playonlineButton);
        getChildren().add(imageView6);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);

    }
}
