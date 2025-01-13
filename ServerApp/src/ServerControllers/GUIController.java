/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerControllers;

import GraphHandler.GraphHandler;
import ServerHandler.ServerHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import ServerGameApp.ServerGUI;

/**
 *
 * @author Abdel
 */
public class GUIController extends ServerGUI {

    ServerHandler server;

    public GUIController(Stage stage) {

        BarChart<String, Number> barChart = createBarChart();
        GraphHandler.setBarChart(barChart);

        stateToggleButton.setOnAction(e -> {
            if (stateToggleButton.isSelected()) {
                switchToStopButton();

                server = new ServerHandler();
                server.startServer();
            } else {
                switchToPlayButton();
                server.stopServer();
            }
        }
        );

        chartButton.setOnAction(e -> {
            barChart.getStylesheets().add("/styles/Stylesheet.css");

            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Users Statistics");
            dialog.getDialogPane().setContent(barChart);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            dialog.show();
        });

        stage.setOnCloseRequest(e -> {
            if (server != null && server.isConnected()) {
                server.stopServer();
            }
        });
    }

    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("User Status");
        yAxis.setLabel("Count");

        XYChart.Series<String, Number> onlineSeries = new XYChart.Series<>();
        onlineSeries.setName("Online Users");
        onlineSeries.getData().add(new XYChart.Data<>("Online", GraphHandler.onlineUsers));

        XYChart.Series<String, Number> offlineSeries = new XYChart.Series<>();
        offlineSeries.setName("Offline Users");
        offlineSeries.getData().add(new XYChart.Data<>("Offline", GraphHandler.offlineUsers));

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.getData().addAll(onlineSeries, offlineSeries);

        return barChart;
    }

    private void switchToPlayButton() {
        stateToggleButton.getStyleClass().removeAll("button2");
        stateToggleButton.getStyleClass().add("button1");
    }

    private void switchToStopButton() {
        stateToggleButton.getStyleClass().removeAll("button1");
        stateToggleButton.getStyleClass().add("button2");
    }
}
