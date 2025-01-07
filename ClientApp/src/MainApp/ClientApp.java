/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApp;


import XOGame.WinVideoPage;
import XOControllers.WinVideoPageController;
import XOControllers.HomePageController;
import XOGame.HistoryPage;
import XOGame.HomePage;
import XOGame.LoginPage;
import XOGame.OnlinePage;
import XOGame.SignupPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abdel
 */
public class ClientApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = new HomePageController(stage); 
        Parent root = new WinVideoPageController();
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
