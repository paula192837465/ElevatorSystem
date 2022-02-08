package ElevatorSystem.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LiftShaftView extends AnchorPane {

    private final ImageView bgImageView;
    private final ImageView fgImageView;

    private Image image = null;


    public LiftShaftView() {

        //stworzenie holdera na obrazek w tle (szyb)
        bgImageView = new ImageView();
        this.getChildren().add(bgImageView);
        setTopAnchor(bgImageView, 0.0);
        setBottomAnchor(bgImageView, 0.0);
        setRightAnchor(bgImageView, 0.0);
        setLeftAnchor(bgImageView, 0.0);


        //stworzenie holdera na przedni obrazek (windę)
        fgImageView = new ImageView();
        this.getChildren().add(fgImageView);
        setTopAnchor(fgImageView, 0.0);
        setBottomAnchor(fgImageView, 0.0);
        setLeftAnchor(fgImageView, 0.0);
        setRightAnchor(fgImageView, 0.0);

    }

    //ustawienie tła komórki w gridzie
    public void setBackground(Image regular) {
        this.image = regular;
        bgImageView.setImage(image);
    }

    //pokazanie obrazka windy
    public void makeVisible(){
        Image lift = new Image("/img/elevator.jpg",70,50,false,true);
        fgImageView.setImage(lift);

    }

    //ukrycie obrazka windy
    public void makeInvisible(){
        fgImageView.setImage(null);
    }
}
