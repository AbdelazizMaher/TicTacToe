/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.PopUpRegisterPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nerme
 */
public class PopUpRegisterController extends PopUpRegisterPage{
    public PopUpRegisterController(Stage stage){        
        super(stage);
        loginLink.setOnAction(e -> {
            Scene scene = new Scene(new LoginPageController(stage));
            stage.setScene(scene);
            popupStage.close();
        });
        signUpLink.setOnAction(e -> {
            Scene scene = new Scene(new SignupPageController(stage));
            stage.setScene(scene);
            popupStage.close();
        });
    }
}
