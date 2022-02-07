package ElevatorSystem.views;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class LiftShaftColumnView extends GridPane {

    private final VBox liftShaftContainer;

    private Image shaftImage;

    private List<Button> shafts;
//    private List<LiftShaftView> shafts;


    public LiftShaftColumnView(){
        liftShaftContainer = new VBox();
        this.getChildren().add(liftShaftContainer);
        GridPane.setRowIndex(liftShaftContainer, 0);
        GridPane.setColumnIndex(liftShaftContainer, 0);
        GridPane.setRowSpan(liftShaftContainer, 1);

    }

    public void setShaftImage(Image shaftImage){
        this.shaftImage = shaftImage;
//        updateSlots();
    }

    public void setLiftShaft(int floors) {
        liftShaftContainer.getChildren().clear();

        shafts = new ArrayList<>();
        for (int i = 0; i < floors; i++) {
            Button liftShaft = new Button("next");
//            LiftShaftView liftShaft = new LiftShaftView();
            shafts.add(liftShaft);
            liftShaftContainer.getChildren().add(liftShaft);
        }
//        updateSlots();
    }
//
//    private void updateSlots() {
//        if (shafts != null) {
//            for (LiftShaftView shaft : shafts) {
//                shaft.setBackground(shaftImage);
//            }
//        }
//    }

}
