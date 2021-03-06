package ElevatorSystem.views;

import ElevatorSystem.controllers.ElevatorSystemController;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class LiftShaftColumnView extends GridPane {

    private final VBox liftShaftContainer;
    private GridPane liftPickups;
    private int elevatorNum;

    private Image shaftImage;
    private  ElevatorSystemController elevatorSystemController;
    private ArrayList<LiftShaftView> shafts;

    public LiftShaftColumnView(int elevatorNum, ElevatorSystemController elevatorSystemController){
        this.elevatorNum =elevatorNum;
        this.elevatorSystemController =elevatorSystemController;
        liftShaftContainer = new VBox();
        this.getChildren().add(liftShaftContainer);
        GridPane.setRowIndex(liftShaftContainer, 0);
        GridPane.setColumnIndex(liftShaftContainer, 0);
        GridPane.setRowSpan(liftShaftContainer, 1);
    }

    //ustawienie obrazka w tle szybu windy
    public void setShaftImage(Image shaftImage){
        this.shaftImage = shaftImage;
        updateShafts();
    }

    //stworzenie całego szybu dla konkretnej windy
    public void setLiftShaft(int floors) {
        liftShaftContainer.getChildren().clear();

        shafts = new ArrayList<>();
        for (int i = 0; i < floors; i++) {
            LiftShaftView liftShaft = new LiftShaftView();
            if(i==floors-1)
                liftShaft.makeVisible();
            shafts.add(liftShaft);
            liftShaftContainer.getChildren().add(liftShaft);
        }

        this.liftPickups = new FloorPickupsView(floors, elevatorNum, elevatorSystemController);
        this.getChildren().add(liftPickups);
        GridPane.setRowIndex(liftPickups, 1);
        GridPane.setColumnIndex(liftPickups, 0);
        GridPane.setRowSpan(liftPickups, 1);

        updateShafts();
    }

    //uaktualnienie wyglądu szybu
    private void updateShafts() {
        if (shafts != null) {
            for (LiftShaftView shaft : shafts) {
                shaft.setBackground(shaftImage);
            }
        }
    }

    public ArrayList<LiftShaftView> getShafts() {
        return shafts;
    }
}
