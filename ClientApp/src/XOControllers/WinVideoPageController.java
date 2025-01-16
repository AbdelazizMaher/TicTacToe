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

    private final Stage mainStage; // Reference to the main stage (game board)
    private final Stage videoStage; // New stage for the video
    private final Media media;
    private final MediaPlayer mediaPlayer;

    public WinVideoPageController(Stage mainStage) {
        this.mainStage = mainStage;

        // Set up the video media and media player
        media = new Media(getClass().getResource("/media/SMILE!.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);

        // Create a new stage for the video
        videoStage = new Stage(StageStyle.DECORATED); // Optional: customize stage style
        MediaView mediaView = new MediaView(mediaPlayer);

        // Wrap the MediaView in a layout
        StackPane videoRoot = new StackPane(mediaView);
        Scene videoScene = new Scene(videoRoot, 800, 600); // Adjust dimensions as needed
        videoStage.setScene(videoScene);

        // Set up media player behavior
        mediaPlayer.setAutoPlay(true);

        // Ensure transition back to main stage when the video ends
        mediaPlayer.setOnEndOfMedia(this::returnToMainStage);

        // Handle manual close of the video stage
        videoStage.setOnCloseRequest(event -> returnToMainStage());
    }

    // Show the video
    public void playVideo() {
        Platform.runLater(() -> {
            System.out.println("Playing video. Hiding the main stage.");
            mainStage.hide(); // Hide the main stage
            videoStage.show(); // Show the video stage
        });
    }

    // Method to handle returning to the main stage
    private void returnToMainStage() {
        Platform.runLater(() -> {
            System.out.println("Returning to the main stage.");
            videoStage.close(); // Close the video stage
            mainStage.show(); // Show the main stage
            mainStage.toFront(); // Bring the main stage to the front
        });
    }
}
