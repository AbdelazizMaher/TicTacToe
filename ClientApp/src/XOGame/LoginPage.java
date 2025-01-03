package XOGame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoginPage extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final TextField usernameTextField;
    protected final TextField passwordTextField;
    protected final Button loginButton;
    protected final Text text;
    protected final Label RegisterLabel;
    protected final Line line;
    protected final Line line0;
    protected final Label gameNameLabel;

    public LoginPage() {

        anchorPane = new AnchorPane();
        usernameTextField = new TextField();
        passwordTextField = new TextField();
        loginButton = new Button();
        text = new Text();
        RegisterLabel = new Label();
        line = new Line();
        line0 = new Line();
        gameNameLabel = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(580.0);
        setPrefWidth(780.0);
        getStyleClass().add("bodybg");
        getStylesheets().add("/Stylesheet.css");

        anchorPane.setLayoutX(239.0);
        anchorPane.setLayoutY(191.0);
        anchorPane.setPrefHeight(375.0);
        anchorPane.setPrefWidth(306.0);
        anchorPane.setStyle("-fx-background-color: #131218;");

        usernameTextField.setLayoutX(43.0);
        usernameTextField.setLayoutY(97.0);
        usernameTextField.setPrefHeight(40.0);
        usernameTextField.setPrefWidth(220.0);
        usernameTextField.getStyleClass().add("text-field");
        usernameTextField.setPromptText("username");
        usernameTextField.setStyle("-fx-background-radius: 20; -fx-border-radius: 20;");
        

        passwordTextField.setFocusTraversable(false);
        passwordTextField.setLayoutX(43.0);
        passwordTextField.setLayoutY(161.0);
        passwordTextField.setPrefHeight(40.0);
        passwordTextField.setPrefWidth(220.0);
        passwordTextField.setPromptText("Password");
        passwordTextField.setStyle("-fx-background-radius: 20; -fx-border-radius: 20;");

        loginButton.setLayoutX(109.0);
        loginButton.setLayoutY(243.0);
        loginButton.setMnemonicParsing(false);
        loginButton.setPrefHeight(30.0);
        loginButton.setPrefWidth(90.0);
        loginButton.setStyle("-fx-background-color: #e61409;");
        loginButton.setText("Login");
        loginButton.setTextFill(javafx.scene.paint.Color.WHITE);
        loginButton.setFont(new Font("Arial Bold", 16.0));

        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setLayoutX(46.0);
        text.setLayoutY(308.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Don't have an account?");
        text.setWrappingWidth(215.6708984375);
        text.setFont(new Font("System Bold", 14.0));

        RegisterLabel.setLayoutX(207.0);
        RegisterLabel.setLayoutY(282.0);
        RegisterLabel.setPrefHeight(42.0);
        RegisterLabel.setPrefWidth(64.0);
        RegisterLabel.setText("Register");
        RegisterLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        RegisterLabel.setFont(new Font("System Bold Italic", 14.0));

        line.setEndX(150.0);
        line.setLayoutX(153.0);
        line.setLayoutY(324.0);
        line.setStartX(-153.0);
        line.setStroke(javafx.scene.paint.Color.WHITE);

        line0.setEndX(150.0);
        line0.setLayoutX(155.0);
        line0.setLayoutY(332.0);
        line0.setStartX(-153.0);
        line0.setStroke(javafx.scene.paint.Color.WHITE);

        gameNameLabel.setLayoutX(109.0);
        gameNameLabel.setLayoutY(24.0);
        gameNameLabel.setPrefHeight(1.0);
        gameNameLabel.setPrefWidth(90.0);
        gameNameLabel.setText("TicTacToe");
        gameNameLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        gameNameLabel.setFont(new Font("Javanese Text", 18.0));

        anchorPane.getChildren().add(usernameTextField);
        anchorPane.getChildren().add(passwordTextField);
        anchorPane.getChildren().add(loginButton);
        anchorPane.getChildren().add(text);
        anchorPane.getChildren().add(RegisterLabel);
        anchorPane.getChildren().add(line);
        anchorPane.getChildren().add(line0);
        anchorPane.getChildren().add(gameNameLabel);
        getChildren().add(anchorPane);

    }
}
