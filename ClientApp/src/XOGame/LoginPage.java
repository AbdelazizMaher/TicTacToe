package XOGame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class LoginPage extends AnchorPane {

    protected final ImageView imageView;
    protected final AnchorPane anchorPane;
    protected final TextField usernameTextField;
    protected final PasswordField passwordTextField;
    protected final ToggleButton loginButton;
    protected final Text text;
    protected final Label registerLabel;
    protected final Line line;
    protected final Line line0;
    protected final Label label0;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Button backButton;

    public LoginPage() {

        imageView = new ImageView();
        anchorPane = new AnchorPane();
        usernameTextField = new TextField();
        passwordTextField = new PasswordField();
        loginButton = new ToggleButton();
        text = new Text();
        registerLabel = new Label();
        line = new Line();
        line0 = new Line();
        label0 = new Label();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        backButton = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(580.0);
        setPrefWidth(780.0);
        getStyleClass().add("bodybg");
        getStylesheets().add("/styles/Stylesheet.css");

        backButton.setPrefSize(60, 41);
        Image backImage = new Image(getClass().getResourceAsStream("/media/back2.png"));
        ImageView backImageView = new ImageView(backImage);
        backImageView.setFitHeight(40);
        backImageView.setFitWidth(40);
        backButton.setGraphic(backImageView);

        imageView.setFitHeight(580.0);
        imageView.setFitWidth(780.0);
        imageView.setPreserveRatio(false);

        anchorPane.setLayoutX(239.0);
        anchorPane.setLayoutY(191.0);
        anchorPane.setPrefHeight(375.0);
        anchorPane.setPrefWidth(306.0);
        anchorPane.setStyle("-fx-background-color: #BC8F8F;");

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
        loginButton.setStyle("-fx-background-color:  #A52A2A;");
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

        registerLabel.setLayoutX(207.0);
        registerLabel.setLayoutY(282.0);
        registerLabel.setPrefHeight(42.0);
        registerLabel.setPrefWidth(64.0);
        registerLabel.setText("Register");
        registerLabel.setTextFill(javafx.scene.paint.Color.web("#A52A2A"));
        registerLabel.setFont(new Font("System Bold Italic", 14.0));

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

        label0.setLayoutX(109.0);
        label0.setLayoutY(24.0);
        label0.setPrefHeight(1.0);
        label0.setPrefWidth(90.0);
        label0.setText("TicTacToe");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("Javanese Text", 18.0));

        imageView0.setFitHeight(40.0);
        imageView0.setFitWidth(43.0);
        imageView0.setLayoutX(9.0);
        imageView0.setLayoutY(10.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/media/xo.jpg").toExternalForm()));

        imageView1.setFitHeight(51.0);
        imageView1.setFitWidth(75.0);
        imageView1.setLayoutX(232.0);
        imageView1.setLayoutY(320.0);
        imageView1.setPickOnBounds(true);
        imageView1.setImage(new Image(getClass().getResource("/media/xomini.jpg").toExternalForm()));

        getChildren().addAll(imageView, backButton);
        anchorPane.getChildren().add(usernameTextField);
        anchorPane.getChildren().add(passwordTextField);
        anchorPane.getChildren().add(loginButton);
        anchorPane.getChildren().add(text);
        anchorPane.getChildren().add(registerLabel);
        anchorPane.getChildren().add(line);
        anchorPane.getChildren().add(line0);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(imageView1);
        getChildren().add(anchorPane);

    }
}
