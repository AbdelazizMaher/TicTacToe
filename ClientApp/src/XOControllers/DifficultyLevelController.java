/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Abdel
 */
public class DifficultyLevelController implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button backButton;
    @FXML
    private ImageView iconImageView;
    @FXML
    private ImageView barImageView;
    @FXML
    private Button easyLevelButton;
    @FXML
    private ImageView easyLevelImageView;
    @FXML
    private Button mediumLevelButton;
    @FXML
    private ImageView mediumLevelImageView;
    @FXML
    private Button hardLevelButton;
    @FXML
    private ImageView hardLevelImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
