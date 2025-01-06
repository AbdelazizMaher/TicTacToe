package serverapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ServerGUI extends AnchorPane {

    protected final ImageView imageView;
    protected final ToggleButton stateToggleButton;
    protected final ImageView imageView0;
    protected final Button chartButton;

    public ServerGUI() {

        imageView = new ImageView();
        stateToggleButton = new ToggleButton();
        imageView0 = new ImageView();
        chartButton = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(580.0);
        setPrefWidth(780.0);

        imageView.setFitHeight(580.0);
        imageView.setFitWidth(780.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("/media/xo.jpg").toExternalForm()));

        stateToggleButton.setLayoutX(52.0);
        stateToggleButton.setLayoutY(216.0);
        stateToggleButton.setMnemonicParsing(false);
        stateToggleButton.setPrefHeight(130.0);
        stateToggleButton.setPrefWidth(191.0);
        stateToggleButton.getStyleClass().add("button1");
        stateToggleButton.getStylesheets().add("/styles/Stylesheet.css");

        imageView0.setFitHeight(75.0);
        imageView0.setFitWidth(50.0);
        imageView0.setLayoutX(730.0);
        imageView0.setLayoutY(14.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/media/avatar.png").toExternalForm()));

        chartButton.setLayoutX(526.0);
        chartButton.setLayoutY(216.0);
        chartButton.setMnemonicParsing(false);
        chartButton.setPrefHeight(130.0);
        chartButton.setPrefWidth(191.0);
        chartButton.getStylesheets().add("/styles/Stylesheet.css");

        getChildren().add(imageView);
        getChildren().add(stateToggleButton);
        getChildren().add(imageView0);
        getChildren().add(chartButton);

    }
}
