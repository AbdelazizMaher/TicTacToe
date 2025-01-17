/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.HistoryPage;
import java.util.Vector;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eman_
 */
public class HistoryPageController extends HistoryPage {

    public static final int OFFLINE = 0;
    public static final int ONLINE = 1;

    public static int activeMode;
    public static String activeName;

    String filePath;

    public HistoryPageController(Stage stage, int mode, String username) {
        showRecords(mode, username);

        backButton.setOnMouseClicked(e -> {
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
        });

        listView.setOnMouseClicked(e -> {
            String selectedFile = listView.getSelectionModel().getSelectedItem().toString();

            Scene scene = new Scene(new RecordPageController(stage, filePath + selectedFile));
            stage.setScene(scene);
        });
    }

    private void showRecords(int mode, String username) {
        Vector<String> files = new Vector<>();
        if (mode == OFFLINE) {
            activeMode = OFFLINE;
            activeName = null;
            filePath = "../ClientApp/src/Record/" + "offline/";
            files = RecordController.getFileNamesFromDirectory(filePath);
        } else if (mode == ONLINE) {
            activeMode = ONLINE;
            activeName = username;
            filePath = "../ClientApp/src/Record/" + "online/" + username + "/";
            files = RecordController.getFileNamesFromDirectory(filePath);
        }
        listView.getItems().addAll(files);
    }
}
