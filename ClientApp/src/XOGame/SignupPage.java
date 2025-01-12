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

public abstract class SignupPage extends AnchorPane {

    protected final ImageView imageView;
    protected final AnchorPane anchorPane;
    protected final TextField usernameTextField;
    protected final PasswordField passwordTextField;
    protected final PasswordField confirmPasswordTextField;
    protected final ToggleButton registerButton;
    protected final Text text;
    protected final Label loginLabel;
    protected final Line line;
    protected final Line line0;
    protected final Label label0;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Button backButton;

    public SignupPage() {

        imageView = new ImageView();
        anchorPane = new AnchorPane();
        usernameTextField = new TextField();
        passwordTextField = new PasswordField();
        confirmPasswordTextField = new PasswordField();
        registerButton = new ToggleButton();
        text = new Text();
        loginLabel = new Label();
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

        imageView.setFitHeight(580.0);
        imageView.setFitWidth(780.0);
        imageView.setPreserveRatio(false);
        
        backButton.setPrefSize(60, 41);
        Image backImage = new Image(getClass().getResourceAsStream("/media/back2.png"));
        ImageView backImageView = new ImageView(backImage);
        backImageView.setFitHeight(40);
        backImageView.setFitWidth(40);
        backButton.setGraphic(backImageView);

        anchorPane.setLayoutX(239.0);
        anchorPane.setLayoutY(191.0);
        anchorPane.setPrefHeight(375.0);
        anchorPane.setPrefWidth(306.0);
        anchorPane.setStyle("-fx-background-color: #131218;");

        usernameTextField.setLayoutX(43.0);
        usernameTextField.setLayoutY(75.0);
        usernameTextField.setPrefHeight(40.0);
        usernameTextField.setPrefWidth(220.0);
        usernameTextField.getStyleClass().add("text-field");
        usernameTextField.setPromptText("username");
        usernameTextField.setStyle("-fx-background-radius: 20; -fx-border-radius: 20;");

        passwordTextField.setFocusTraversable(false);
        passwordTextField.setLayoutX(43.0);
        passwordTextField.setLayoutY(129.0);
        passwordTextField.setPrefHeight(40.0);
        passwordTextField.setPrefWidth(220.0);
        passwordTextField.setPromptText("Password");
        passwordTextField.setStyle("-fx-background-radius: 20; -fx-border-radius: 20;");

        confirmPasswordTextField.setLayoutX(43.0);
        confirmPasswordTextField.setLayoutY(182.0);
        confirmPasswordTextField.setPrefHeight(40.0);
        confirmPasswordTextField.setPrefWidth(220.0);
        confirmPasswordTextField.setPromptText("confirm Password");
        confirmPasswordTextField.setStyle("-fx-background-radius: 20; -fx-border-radius: 20;");

        registerButton.setLayoutX(109.0);
        registerButton.setLayoutY(250.0);
        registerButton.setMnemonicParsing(false);
        registerButton.setPrefHeight(30.0);
        registerButton.setPrefWidth(90.0);
        registerButton.setStyle("-fx-background-color: #e61409;");
        registerButton.setText("Register");
        registerButton.setTextFill(javafx.scene.paint.Color.WHITE);
        registerButton.setFont(new Font("Arial Bold", 16.0));

        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setLayoutX(45.0);
        text.setLayoutY(308.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Already have an account?");
        text.setWrappingWidth(215.6708984375);
        text.setFont(new Font("System Bold", 14.0));

        loginLabel.setLayoutX(219.0);
        loginLabel.setLayoutY(282.0);
        loginLabel.setPrefHeight(42.0);
        loginLabel.setPrefWidth(64.0);
        loginLabel.setText("login");
        loginLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        loginLabel.setFont(new Font("System Bold Italic", 14.0));

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
        imageView0.setFitWidth(50.0);
        imageView0.setLayoutX(10.0);
        imageView0.setLayoutY(12.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/media/xo.jpg").toExternalForm()));

        imageView1.setFitHeight(60.0);
        imageView1.setFitWidth(80.0);
        imageView1.setLayoutX(226.0);
        imageView1.setLayoutY(315.0);
        imageView1.setPickOnBounds(true);
        imageView1.setImage(new Image(getClass().getResource("/media/xomini.jpg").toExternalForm()));

       getChildren().addAll(imageView,backButton);
        anchorPane.getChildren().add(usernameTextField);
        anchorPane.getChildren().add(passwordTextField);
        anchorPane.getChildren().add(confirmPasswordTextField);
        anchorPane.getChildren().add(registerButton);
        anchorPane.getChildren().add(text);
        anchorPane.getChildren().add(loginLabel);
        anchorPane.getChildren().add(line);
        anchorPane.getChildren().add(line0);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(imageView1);
        getChildren().add(anchorPane);

    }
}
