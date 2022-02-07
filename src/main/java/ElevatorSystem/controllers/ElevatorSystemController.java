package ElevatorSystem.controllers;

import ElevatorSystem.containers.Building;
import ElevatorSystem.models.ElevatorSystemModel;
import ElevatorSystem.views.LiftShaftColumnView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystemController {

    @FXML
    Pane pane;

    @FXML
    GridPane gridPane;

    @FXML
    GridPane liftShafts;


    private Building building;
    private ElevatorSystemModel elevatorSystemModel;
    private List<LiftShaftColumnView> liftShaftColumnViews = new ArrayList<>();

    public void init(ElevatorSystemModel elevatorSystemModel) {
        this.elevatorSystemModel = elevatorSystemModel;
        this.building = elevatorSystemModel.getBuilding();

        Image shaftBg = new Image("/img/szyb.png", 100, 150, false, true);

        for(int i=0; i< building.getElevatorsNum(); i++){
            //tworzy nową kolumnę
            LiftShaftColumnView liftShaftColumnView = new LiftShaftColumnView();

            //ustawia tła szybów
            liftShaftColumnView.setShaftImage(shaftBg);
            //ustawia ilość pięter
            liftShaftColumnView.setLiftShaft(building.getFloors());
            //dodaje kolumnę do listy
            liftShaftColumnViews.add(i, liftShaftColumnView);
            //dodaje kolumnę do grida
            liftShafts.add(liftShaftColumnView, i, 0);
        }

        //klawiatura do przywoływania piętra
        for (int n = 1; n< this.building.getFloors()+1; n++) {
            Button button = createNumberButton(n-1);
            int row = (n-1) / 3;
            int col = (n-1) % 3 ;
            gridPane.add(button, col,  row);
        }

    }

    private Button createNumberButton(Integer number) {
        Button button = createButton(Integer.toString(number));
        button.setOnAction(e -> buttonHandler(e, number.doubleValue()));
        return button ;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(100.0, 100.0);
        button.setMinSize(50.0, 50.0);
        return button ;
    }

    private void buttonHandler(ActionEvent e, Double number){
        System.out.println(number);
        this.elevatorSystemModel.pickup(number);
    }

}
