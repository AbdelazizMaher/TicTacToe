/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.HistoryPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eman_
 */
public class HistoryPageController extends HistoryPage {
    public HistoryPageController(Stage stage){
        backButton.setOnMouseClicked(e ->{
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
        });
        
        listView.setOnMouseClicked(e->{
        Scene scene = new Scene(new RecordPageController(stage));
            stage.setScene(scene);
        });
    }
}
