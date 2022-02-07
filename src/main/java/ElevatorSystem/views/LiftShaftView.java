package ElevatorSystem.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LiftShaftView extends AnchorPane {

    private final ImageView bgImageView;
    private final ImageView fgImageView;

    private Image image = null;


    public LiftShaftView() {

        //create background image holder
        bgImageView = new ImageView();
        this.getChildren().add(bgImageView);
        setTopAnchor(bgImageView, 0.0);
        setBottomAnchor(bgImageView, 0.0);
        setRightAnchor(bgImageView, 0.0);
        setLeftAnchor(bgImageView, 0.0);


        //create foreground image holder
        fgImageView = new ImageView();
        this.getChildren().add(fgImageView);
        setTopAnchor(fgImageView, 0.0);
        setBottomAnchor(fgImageView, 0.0);
        setLeftAnchor(fgImageView, 0.0);
        setRightAnchor(fgImageView, 0.0);

    }

    public void setBackground(Image regular) {
        this.image = regular;
    }
}
