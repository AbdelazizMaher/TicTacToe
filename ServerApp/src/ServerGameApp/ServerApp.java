/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerGameApp;

import ServerControllers.GUIController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abdel
 */
public class ServerApp extends Application {

    GUIController root;
    
    @Override
    public void start(Stage stage) throws Exception {
        root = new GUIController(stage);

        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/styles/Stylesheet.css").toString());

        stage.setScene(scene);
        stage.setTitle("TicTacToe Dashboard");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
