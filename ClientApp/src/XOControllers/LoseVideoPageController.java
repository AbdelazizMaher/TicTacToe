/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import XOGame.LoseVideoPage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author Adham
 */
public class LoseVideoPageController extends LoseVideoPage {
    private File file;
    private Media media;
    
    public LoseVideoPageController() {
        // Set up the video
        Media media = new Media(getClass().getResource("/media/KILL!.mp4").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    } 

       
    
}
