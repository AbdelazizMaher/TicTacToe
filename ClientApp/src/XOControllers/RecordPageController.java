/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.RecordPage;
import XOGame.VsCompPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class RecordPageController extends RecordPage{
    public RecordPageController(Stage stage){
        super(stage);
        backButton.setOnMouseClicked(e->{
          Scene scene = new Scene(new HistoryPageController(stage));
          stage.setScene(scene);
        });
        
    }
}
