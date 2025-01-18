/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.DifficultyLevel;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdel
 */
public class DifficultyLevelController extends DifficultyLevel {

    public static final int EASY_LEVEL = 0;
    public static final int MEDIUM_LEVEL = 1;
    public static final int HARD_LEVEL = 2;

    private int difficultyLevel = -1;

    public DifficultyLevelController(Stage stage) {

        backButton.setOnAction(e -> {
            Scene scene = new Scene(new HomePageController(stage));
            stage.setScene(scene);
        });

        easyLevelButton.setOnAction(e -> {
            difficultyLevel = EASY_LEVEL;

            Scene scene = new Scene(new VsCompPageController(stage, difficultyLevel));
            stage.setScene(scene);
        });

        mediumLevelButton.setOnAction(e -> {
            difficultyLevel = MEDIUM_LEVEL;

            Scene scene = new Scene(new VsCompPageController(stage, difficultyLevel));
            stage.setScene(scene);
        });

        hardLevelButton.setOnAction(e -> {
            difficultyLevel = HARD_LEVEL;

            Scene scene = new Scene(new VsCompPageController(stage, difficultyLevel));
            stage.setScene(scene);
        });
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
