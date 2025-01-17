package XOControllers;

import XOGame.WinVideoPage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Adham
 */
public class WinVideoPageController extends WinVideoPage {

    private final Stage mainStage; 
    private final Stage videoStage; 
    private final Media media;
    private final MediaPlayer mediaPlayer;

    public WinVideoPageController(Stage mainStage) {
        this.mainStage = mainStage;

        media = new Media(getClass().getResource("/media/SMILE!.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);

        videoStage = new Stage(StageStyle.DECORATED); 
        MediaView mediaView = new MediaView(mediaPlayer);

        StackPane videoRoot = new StackPane(mediaView);
        Scene videoScene = new Scene(videoRoot, 800, 600);
        videoStage.setScene(videoScene);

        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(this::returnToMainStage);

        videoStage.setOnCloseRequest(event -> {
            mediaPlayer.stop(); 
            returnToMainStage();
        });
    }

    public void playVideo() {
        Platform.runLater(() -> {
            System.out.println("Playing video. Hiding the main stage.");
            mainStage.hide(); 
            videoStage.show(); 
        });
    }

    private void returnToMainStage() {
        Platform.runLater(() -> {
            System.out.println("Returning to the main stage.");
            mediaPlayer.stop();
            videoStage.close(); 
            mainStage.show(); 
            mainStage.toFront(); 
        });
    }
}
