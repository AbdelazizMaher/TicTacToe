package XOGame;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

public abstract class WinVideoPage extends AnchorPane {

    protected final MediaView mediaView;

    public WinVideoPage() {

        mediaView = new MediaView();

        setId("AnchorPane");
        setPrefHeight(380.0);
        setPrefWidth(675.0);

        mediaView.setFitHeight(380.0);
        mediaView.setFitWidth(675.0);

        getChildren().add(mediaView);

    }
}
