/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphHandler;

import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Abdel
 */
public class GraphHandler {

    private static BarChart<String, Number> barChart;
    public static int onlineUsers = 0;
    public static int offlineUsers = 0;

    public static void setBarChart(BarChart<String, Number> chart) {
        barChart = chart;
    }

    public static void updateGraph(int onlineUsers, int offlineUsers) {
        Platform.runLater(() -> {
            XYChart.Series<String, Number> onlineSeries = barChart.getData().get(0);
            XYChart.Series<String, Number> offlineSeries = barChart.getData().get(1);

            onlineSeries.getData().get(0).setYValue(onlineUsers);
            offlineSeries.getData().get(0).setYValue(offlineUsers);
        });
    }
}
