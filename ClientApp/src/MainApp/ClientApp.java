/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApp;


import XOControllers.HomePageController;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Abdel
 */
public class ClientApp extends Application {

    HomePageController root;
    public static boolean isSplashScreenLoaded = false;
    
    @Override
    public void start(Stage stage) throws Exception {
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SplashScreen.fxml"));
        Parent splashRoot = loader.load();

        Scene splashScene = new Scene(splashRoot, 780, 580);
        stage.setScene(splashScene);
        stage.show();

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), splashRoot);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), splashRoot);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(fadeOutEvent -> {
                
                try {
                    root = new HomePageController(stage);
                    Scene mainScene = new Scene(root);
                    stage.setScene(mainScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            fadeOut.play();
        });

        pause.play();
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
