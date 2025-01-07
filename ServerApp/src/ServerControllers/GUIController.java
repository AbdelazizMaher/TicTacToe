/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerControllers;

import ServerHandler.ServerHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import serverapp.ServerGUI;

/**
 *
 * @author Abdel
 */
public class GUIController extends ServerGUI {

    ServerHandler server;
    
    public GUIController() {
        stateToggleButton.setOnAction(event -> {
            if (stateToggleButton.isSelected()) {
                stateToggleButton.getStyleClass().removeAll("button1");
                stateToggleButton.getStyleClass().add("button2");
            } else {
                stateToggleButton.getStyleClass().removeAll("button2");
                stateToggleButton.getStyleClass().add("button1");
                server = new ServerHandler();
                server.startServer();
            }
        }
        );

        chartButton.setOnAction(event -> {
            BarChart<String, Number> barChart = createBarChart();
            barChart.getStylesheets().add("/styles/Stylesheet.css");

            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Users Statistics");
            dialog.getDialogPane().setContent(barChart);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            dialog.show();
        });
    }

    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("User Status");
        yAxis.setLabel("Count");

        XYChart.Series<String, Number> onlineSeries = new XYChart.Series<>();
        onlineSeries.setName("Online Users");
        onlineSeries.getData().add(new XYChart.Data<>("Online", 10));

        XYChart.Series<String, Number> offlineSeries = new XYChart.Series<>();
        offlineSeries.setName("Offline Users");
        offlineSeries.getData().add(new XYChart.Data<>("Offline", 20));

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.getData().addAll(onlineSeries, offlineSeries);

        return barChart;
    }

}
