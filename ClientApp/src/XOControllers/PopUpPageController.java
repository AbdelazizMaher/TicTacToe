package XOControllers;

import XOGame.PopUpPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PopUpPageController {

    public PopUpPageController(Stage stage) {
        PopUpPage prompt = new PopUpPage(stage, (user1, user2) -> {
            HomePageController root = new HomePageController(stage);
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        });
        prompt.show();
    }
}
