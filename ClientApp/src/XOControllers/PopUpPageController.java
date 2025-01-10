package XOControllers;

import XOGame.PopUpPage;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static XOControllers.OfflinePageController.playerX;
import static XOControllers.OfflinePageController.playerO;

public class PopUpPageController {

    public PopUpPageController(Stage stage) {
        new PopUpPage(stage);
    }
}
