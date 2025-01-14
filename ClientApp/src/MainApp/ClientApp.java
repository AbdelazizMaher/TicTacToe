/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApp;


import XOControllers.HomePageController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abdel
 */
public class ClientApp extends Application {

    HomePageController root;
    
    @Override
    public void start(Stage stage) throws Exception {
        root = new HomePageController(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
