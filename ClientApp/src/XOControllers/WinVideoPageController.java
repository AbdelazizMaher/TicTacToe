package XOControllers;

import XOGame.WinVideoPage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    private final Stage mainStage; // Reference to the main stage
    private final Stage videoStage; // New stage for the video
    private final Media media;
    private final MediaPlayer mediaPlayer;

    public WinVideoPageController(Stage mainStage) {
        this.mainStage = mainStage;

        // Set up the video media and media player
        media = new Media(getClass().getResource("/media/SMILE!.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);

        // Create a new stage for the video
        videoStage = new Stage(StageStyle.UNDECORATED); // Optional: customize stage style
        MediaView mediaView = new MediaView(mediaPlayer);
        Scene videoScene = new Scene(this, 800, 600); // Adjust dimensions as needed
        videoStage.setScene(videoScene);

        // Set up media player
        mediaPlayer.setAutoPlay(true);

        // Listen for the end of the video
        mediaPlayer.setOnEndOfMedia(() -> {
            videoStage.close(); // Close the video stage
            Platform.runLater(() -> mainStage.show()); // Show the main stage
        });
    }

    // Show the video
    public void playVideo() {
        mainStage.hide(); // Hide the main stage
        videoStage.show(); // Show the video stage
    }
}
